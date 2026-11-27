-- =====================================================================
-- VetisPilot – V2: rules found while testing the clickable prototype
-- Documentation: docs/analysis/06-prototype-decisions.md (PD-01 – PD-18)
--               docs/database/01-er-model.md (section 6)
--
-- V1 is never changed after it has run; every change comes as a new file.
-- Same conventions as V1 (snake_case, VARCHAR + CHECK, NUMERIC for money, TIMESTAMPTZ).
-- =====================================================================

-- ---------------------------------------------------------------------
-- 1. Products: volume for the courier limit (PD-08), best-before date (PD-18)
-- ---------------------------------------------------------------------
ALTER TABLE products
    ADD COLUMN volume_l NUMERIC(5,2) NOT NULL DEFAULT 1.00 CHECK (volume_l > 0);   -- litres incl. packaging

-- the best-before date belongs to the goods on the shelf of ONE branch, not to the product in general
ALTER TABLE branch_stock
    ADD COLUMN best_before DATE;

-- ---------------------------------------------------------------------
-- 2. Sale open / closed per branch and channel (PD-02, US-40)
--    No row = open. Stock 0 closes a product anyway (V1: stock_zero_means_out).
-- ---------------------------------------------------------------------
CREATE TABLE product_channel_status (
    branch_id   BIGINT      NOT NULL REFERENCES branches (id),
    product_id  BIGINT      NOT NULL REFERENCES products (id),
    channel_id  SMALLINT    NOT NULL REFERENCES channels (id),
    on_sale     BOOLEAN     NOT NULL DEFAULT TRUE,
    updated_by  BIGINT      REFERENCES users (id),
    updated_at  TIMESTAMPTZ NOT NULL DEFAULT now(),
    PRIMARY KEY (branch_id, product_id, channel_id)
);

-- ---------------------------------------------------------------------
-- 3. Split orders (PD-08, US-46)
--    One courier carries at most 20 L. A bigger order becomes several rows with the SAME channel number:
--      part 1 = the original row (split_parent_id NULL), parts 2, 3 ... point to part 1.
--    Reports count only rows with split_parent_id IS NULL, so a split order is ONE order.
-- ---------------------------------------------------------------------
ALTER TABLE orders
    ADD COLUMN split_parent_id BIGINT   REFERENCES orders (id),
    ADD COLUMN split_part      SMALLINT CHECK (split_part BETWEEN 1 AND 9),
    ADD CONSTRAINT orders_split_shape CHECK (
        (split_part IS NULL AND split_parent_id IS NULL) OR       -- normal order
        (split_part = 1     AND split_parent_id IS NULL) OR       -- first part
        (split_part >= 2    AND split_parent_id IS NOT NULL)      -- further parts
    );

-- idempotency (E2) stays: the channel order number is unique per channel, except for the parts 2, 3 ...
ALTER TABLE orders DROP CONSTRAINT orders_channel_order_unique;
CREATE UNIQUE INDEX orders_channel_order_unique
    ON orders (channel_id, external_order_no, COALESCE(split_part, 1));

-- ---------------------------------------------------------------------
-- 4. Amount protection (PD-06, PD-07, US-44, US-45)
--    +15 % is a hard limit (checked by the backend before READY).
--    More than −15 % or a missing line needs a logged call AND the tick "customer informed and agreed".
-- ---------------------------------------------------------------------
ALTER TABLE orders
    ADD COLUMN customer_ok_at TIMESTAMPTZ,
    ADD COLUMN customer_ok_by BIGINT REFERENCES users (id),
    ADD CONSTRAINT orders_customer_ok CHECK ((customer_ok_at IS NULL) = (customer_ok_by IS NULL));

ALTER TABLE customer_calls DROP CONSTRAINT customer_calls_purpose_check;
ALTER TABLE customer_calls ADD CONSTRAINT customer_calls_purpose_check
    CHECK (purpose IN ('MISSING_ITEM', 'WEIGHT', 'AMOUNT_DROP', 'CANCELLATION'));

-- ---------------------------------------------------------------------
-- 5. New cancellation rules (PD-09, US-12) – replaces the V1 guard
--    BRANCH_OVERLOADED     at once, no call needed            -> counts against the store score
--    CUSTOMER_UNREACHABLE  2 unanswered calls, 2 min apart    -> does not count
--    ITEM_MISSING          2 logged calls, 2 min apart,
--                          or the customer agreed             -> counts
--    CUSTOMER_CANCELLED, WRONG_ADDRESS                         -> only from the channel, never from the branch
-- ---------------------------------------------------------------------
ALTER TABLE orders DROP CONSTRAINT orders_cancel_reason_check;
ALTER TABLE orders ADD CONSTRAINT orders_cancel_reason_check CHECK (cancel_reason IN
    ('ITEM_MISSING', 'BRANCH_OVERLOADED', 'CUSTOMER_UNREACHABLE', 'CUSTOMER_CANCELLED', 'WRONG_ADDRESS', 'OTHER'));

ALTER TABLE orders DROP CONSTRAINT orders_score_fair;
ALTER TABLE orders ADD CONSTRAINT orders_score_fair CHECK (
    NOT (affects_store_score AND (cancelled_by = 'CHANNEL'
         OR cancel_reason IN ('CUSTOMER_UNREACHABLE', 'CUSTOMER_CANCELLED', 'WRONG_ADDRESS')))
);

-- the branch may only use the reasons it is offered in the panel
ALTER TABLE orders ADD CONSTRAINT orders_branch_reasons CHECK (
    cancelled_by IS DISTINCT FROM 'BRANCH'
    OR cancel_reason IN ('BRANCH_OVERLOADED', 'CUSTOMER_UNREACHABLE', 'ITEM_MISSING')
);

CREATE OR REPLACE FUNCTION orders_cancel_guard() RETURNS trigger AS $$
DECLARE
    calls_all  INT;
    calls_none INT;
    span_all   INTERVAL;
    span_none  INTERVAL;
    agreed     BOOLEAN;
BEGIN
    IF NEW.status <> 'CANCELLED' OR OLD.status = 'CANCELLED' THEN
        RETURN NEW;
    END IF;
    IF NEW.cancelled_by = 'CHANNEL' THEN
        NEW.affects_store_score := FALSE;
        RETURN NEW;
    END IF;

    SELECT count(*), max(called_at) - min(called_at) INTO calls_all, span_all
      FROM customer_calls WHERE order_id = NEW.id;
    SELECT count(*), max(called_at) - min(called_at) INTO calls_none, span_none
      FROM customer_calls WHERE order_id = NEW.id AND result = 'NO_ANSWER';
    SELECT EXISTS (SELECT 1 FROM customer_calls WHERE order_id = NEW.id
                   AND result = 'REACHED' AND customer_answer = 'AGREED_CANCELLATION') INTO agreed;

    IF NEW.cancel_reason = 'BRANCH_OVERLOADED' THEN
        NEW.affects_store_score := TRUE;
    ELSIF NEW.cancel_reason = 'CUSTOMER_UNREACHABLE' THEN
        IF calls_none < 2 OR span_none < INTERVAL '2 minutes' THEN
            RAISE EXCEPTION 'Order %: 2 unanswered calls at least 2 minutes apart are required (% logged)', NEW.id, calls_none;
        END IF;
        NEW.affects_store_score := FALSE;
    ELSIF NEW.cancel_reason = 'ITEM_MISSING' THEN
        IF NOT agreed AND (calls_all < 2 OR span_all < INTERVAL '2 minutes') THEN
            RAISE EXCEPTION 'Order %: 2 logged calls at least 2 minutes apart or the customer''s consent are required', NEW.id;
        END IF;
        NEW.affects_store_score := TRUE;
    ELSE
        RAISE EXCEPTION 'Order %: reason % can only come from the channel', NEW.id, NEW.cancel_reason;
    END IF;
    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

-- ---------------------------------------------------------------------
-- 6. Returns: reason, handover with a QR code, refund (PD-10 – PD-13, US-47 – US-50)
-- ---------------------------------------------------------------------
ALTER TABLE returns
    ADD COLUMN reason_code VARCHAR(20) NOT NULL DEFAULT 'DAMAGED'
               CHECK (reason_code IN ('DAMAGED', 'SPOILED', 'EXPIRED', 'WRONG_PRODUCT', 'MISSING_PRODUCT', 'COLD_CHAIN')),
    ADD COLUMN refunded_at TIMESTAMPTZ,
    ADD CONSTRAINT returns_refund_after_approval CHECK (refunded_at IS NULL OR status = 'APPROVED');
ALTER TABLE returns ALTER COLUMN reason_code DROP DEFAULT;   -- the default only filled rows that already existed

-- An approved return is brought back by a courier. The QR code can only be created while the courier
-- is AT the store; the employee can also refuse the goods (broken, different, missing) with a reason.
CREATE TABLE return_handovers (
    id               BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    return_id        BIGINT      NOT NULL UNIQUE REFERENCES returns (id),
    status           VARCHAR(12) NOT NULL DEFAULT 'ASSIGNING'
                     CHECK (status IN ('ASSIGNING', 'ON_WAY', 'AT_STORE', 'QR_CREATED', 'RECEIVED', 'REJECTED')),
    courier_name     VARCHAR(80),
    token_hash       CHAR(64),                                -- SHA-256 of the one-time code, never the code itself
    qr_created_at    TIMESTAMPTZ,
    qr_created_by    BIGINT      REFERENCES users (id),
    received_at      TIMESTAMPTZ,                             -- the courier scanned the code
    rejected_at      TIMESTAMPTZ,
    rejected_by      BIGINT      REFERENCES users (id),
    rejection_reason VARCHAR(300),
    updated_at       TIMESTAMPTZ NOT NULL DEFAULT now(),
    CONSTRAINT handover_qr      CHECK ((status IN ('QR_CREATED', 'RECEIVED')) = (qr_created_at IS NOT NULL AND token_hash IS NOT NULL)),
    CONSTRAINT handover_receive CHECK ((status = 'RECEIVED') = (received_at IS NOT NULL)),
    CONSTRAINT handover_reject  CHECK ((status = 'REJECTED') = (rejected_at IS NOT NULL AND rejection_reason IS NOT NULL)),
    CONSTRAINT handover_no_qr_on_reject CHECK (status <> 'REJECTED' OR qr_created_at IS NULL)
);

-- the courier must be at the store before a QR code is created (state machine of PD-11)
CREATE FUNCTION return_handover_steps() RETURNS trigger AS $$
BEGIN
    IF NEW.status = OLD.status THEN
        RETURN NEW;
    END IF;
    IF NOT (
        (OLD.status = 'ASSIGNING'  AND NEW.status = 'ON_WAY')     OR
        (OLD.status = 'ON_WAY'     AND NEW.status = 'AT_STORE')   OR
        (OLD.status = 'AT_STORE'   AND NEW.status IN ('QR_CREATED', 'REJECTED')) OR
        (OLD.status = 'QR_CREATED' AND NEW.status = 'RECEIVED')
    ) THEN
        RAISE EXCEPTION 'Return handover %: % -> % is not allowed', NEW.id, OLD.status, NEW.status;
    END IF;
    NEW.updated_at := now();
    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER return_handover_steps
    BEFORE UPDATE OF status ON return_handovers
    FOR EACH ROW EXECUTE FUNCTION return_handover_steps();

-- ---------------------------------------------------------------------
-- 7. WhatsApp reminders and consent (PD-05, US-43) – GDPR: only with the employee's consent
-- ---------------------------------------------------------------------
ALTER TABLE users
    ADD COLUMN phone              VARCHAR(20),
    ADD COLUMN whatsapp_opt_in    BOOLEAN NOT NULL DEFAULT FALSE,
    ADD COLUMN whatsapp_opt_in_at TIMESTAMPTZ,
    ADD CONSTRAINT users_whatsapp_consent CHECK (
        NOT whatsapp_opt_in OR (phone IS NOT NULL AND whatsapp_opt_in_at IS NOT NULL)
    );

CREATE TABLE notification_log (
    id          BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    branch_id   BIGINT      NOT NULL REFERENCES branches (id),
    user_id     BIGINT      NOT NULL REFERENCES users (id),
    via         VARCHAR(10) NOT NULL CHECK (via IN ('WHATSAPP', 'PUSH')),
    level       VARCHAR(10) NOT NULL CHECK (level IN ('WAITING', 'LATE', 'VERY_LATE')),
    order_count SMALLINT    NOT NULL CHECK (order_count > 0),
    status      VARCHAR(10) NOT NULL DEFAULT 'SENT' CHECK (status IN ('SENT', 'FAILED')),
    sent_at     TIMESTAMPTZ NOT NULL DEFAULT now()
);

-- ---------------------------------------------------------------------
-- 8. Customer reviews (PD-14, US-51) – the panel only shows them
-- ---------------------------------------------------------------------
CREATE TABLE reviews (
    id          BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    order_id    BIGINT      NOT NULL UNIQUE REFERENCES orders (id),
    service     SMALLINT    NOT NULL CHECK (service  BETWEEN 1 AND 5),
    taste       SMALLINT    NOT NULL CHECK (taste    BETWEEN 1 AND 5),
    delivery    SMALLINT    NOT NULL CHECK (delivery BETWEEN 1 AND 5),
    comment     VARCHAR(500),
    created_at  TIMESTAMPTZ NOT NULL DEFAULT now()
);

-- ---------------------------------------------------------------------
-- 9. Time-limited discounts per channel (PD-18, US-54)
--    At most 70 %; the price never goes below 0.01 € and the highest discount wins (backend).
--    A new order stores the discounted price in order_items.unit_price (US-14).
-- ---------------------------------------------------------------------
CREATE TABLE discounts (
    id           BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    branch_id    BIGINT        NOT NULL REFERENCES branches (id),
    channel_id   SMALLINT      NOT NULL REFERENCES channels (id),
    scope        VARCHAR(12)   NOT NULL CHECK (scope IN ('ALL', 'CATEGORY', 'PRODUCTS', 'BEST_BEFORE')),
    category_id  SMALLINT      REFERENCES categories (id),
    type         VARCHAR(7)    NOT NULL CHECK (type IN ('PERCENT', 'AMOUNT')),
    value        NUMERIC(10,2) NOT NULL CHECK (value > 0),
    starts_at    TIMESTAMPTZ   NOT NULL DEFAULT now(),
    ends_at      TIMESTAMPTZ   NOT NULL,
    stopped_at   TIMESTAMPTZ,
    created_by   BIGINT        NOT NULL REFERENCES users (id),
    created_at   TIMESTAMPTZ   NOT NULL DEFAULT now(),
    CONSTRAINT discounts_max_percent CHECK (type <> 'PERCENT' OR value <= 70),
    CONSTRAINT discounts_period      CHECK (ends_at > starts_at),
    CONSTRAINT discounts_category    CHECK ((scope = 'CATEGORY') = (category_id IS NOT NULL))
);

-- products chosen by hand (scope PRODUCTS) or the ticked part of the best-before list (scope BEST_BEFORE)
CREATE TABLE discount_products (
    discount_id BIGINT NOT NULL REFERENCES discounts (id),
    product_id  BIGINT NOT NULL REFERENCES products (id),
    PRIMARY KEY (discount_id, product_id)
);

-- which discount gave the price of an order line (NULL = list price)
ALTER TABLE order_items
    ADD COLUMN discount_id BIGINT REFERENCES discounts (id);

-- ---------------------------------------------------------------------
-- 10. Indexes
-- ---------------------------------------------------------------------
CREATE INDEX idx_orders_split_parent  ON orders (split_parent_id) WHERE split_parent_id IS NOT NULL;
CREATE INDEX idx_channel_status_open  ON product_channel_status (branch_id, channel_id) WHERE NOT on_sale;
CREATE INDEX idx_stock_best_before    ON branch_stock (branch_id, best_before);
CREATE INDEX idx_handovers_status     ON return_handovers (status);
CREATE INDEX idx_notifications_branch ON notification_log (branch_id, sent_at DESC);
CREATE INDEX idx_discounts_running    ON discounts (branch_id, channel_id, ends_at) WHERE stopped_at IS NULL;
CREATE INDEX idx_returns_reason       ON returns (reason_code);
