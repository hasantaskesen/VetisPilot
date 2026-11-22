-- =====================================================================
-- VetisPilot – database schema (PostgreSQL 16)
-- Documentation: docs/database/01-er-model.md (EN / DE / TR)
--
-- Conventions
--   * table and column names: English, snake_case, plural table names
--   * primary keys: id BIGINT GENERATED ALWAYS AS IDENTITY
--   * fixed value lists (status, role ...): VARCHAR + CHECK constraint
--   * money: NUMERIC(10,2) in EUR, never FLOAT
--   * time: TIMESTAMPTZ (stored in UTC, shown in Europe/Berlin)
-- =====================================================================

-- ---------------------------------------------------------------------
-- 1. Sellers and branches
-- ---------------------------------------------------------------------
CREATE TABLE sellers (
    id          BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    name        VARCHAR(120) NOT NULL,
    active      BOOLEAN      NOT NULL DEFAULT TRUE,
    created_at  TIMESTAMPTZ  NOT NULL DEFAULT now()
);

CREATE TABLE branches (
    id            BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    seller_id     BIGINT       NOT NULL REFERENCES sellers (id),
    name          VARCHAR(120) NOT NULL,
    street        VARCHAR(160) NOT NULL,
    postal_code   VARCHAR(10)  NOT NULL,
    city          VARCHAR(80)  NOT NULL,
    opening_hours VARCHAR(200),                          -- e.g. "Mon–Sat 08:00–22:00"
    is_open       BOOLEAN      NOT NULL DEFAULT TRUE,     -- US-21: closed branches reject orders
    bag_fee       NUMERIC(5,2) NOT NULL DEFAULT 0.25 CHECK (bag_fee >= 0),   -- A10 / US-33: fee per bag in EUR
    active        BOOLEAN      NOT NULL DEFAULT TRUE,
    created_at    TIMESTAMPTZ  NOT NULL DEFAULT now(),
    UNIQUE (seller_id, name)
);

-- ---------------------------------------------------------------------
-- 2. Channels (HTGO Market, GK Market)
--    The HMAC secret of each channel is NOT stored here; it lives in .env.
--    The colors theme the panel after the user chose a channel (US-39).
-- ---------------------------------------------------------------------
CREATE TABLE channels (
    id             SMALLINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    code           VARCHAR(10) NOT NULL UNIQUE,           -- 'HTGO', 'GK'
    name           VARCHAR(60) NOT NULL,
    primary_color  CHAR(7)     NOT NULL,                  -- HTGO '#1D4ED8', GK '#7C4A21'
    dark_color     CHAR(7)     NOT NULL,                  -- HTGO '#0B2A6F', GK '#3E2410'
    light_color    CHAR(7)     NOT NULL,                  -- HTGO '#DBE6FF', GK '#F3E6D3'
    active         BOOLEAN     NOT NULL DEFAULT TRUE
);

-- ---------------------------------------------------------------------
-- 3. Users and roles
-- ---------------------------------------------------------------------
CREATE TABLE users (
    id                   BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    email                VARCHAR(160) NOT NULL UNIQUE,
    password_hash        VARCHAR(100) NOT NULL,           -- BCrypt hash, never the password itself
    full_name            VARCHAR(120) NOT NULL,
    role                 VARCHAR(20)  NOT NULL
                         CHECK (role IN ('ADMIN', 'SELLER', 'BRANCH_EMPLOYEE')),
    seller_id            BIGINT REFERENCES sellers (id),
    branch_id            BIGINT REFERENCES branches (id),
    language             CHAR(2)      NOT NULL DEFAULT 'en'
                         CHECK (language IN ('en', 'de', 'tr')),
    last_channel_id      SMALLINT     REFERENCES channels (id),   -- US-39: last chosen channel
    must_change_password BOOLEAN      NOT NULL DEFAULT TRUE,  -- US-22: temporary password
    failed_login_count   INT          NOT NULL DEFAULT 0,     -- US-02: brute-force protection
    locked_until         TIMESTAMPTZ,
    active               BOOLEAN      NOT NULL DEFAULT TRUE,
    last_login_at        TIMESTAMPTZ,
    created_at           TIMESTAMPTZ  NOT NULL DEFAULT now(),
    -- every role is linked to exactly the right level (rules in 02-roles-permissions)
    CONSTRAINT users_role_scope CHECK (
        (role = 'ADMIN'           AND seller_id IS NULL     AND branch_id IS NULL) OR
        (role = 'SELLER'          AND seller_id IS NOT NULL AND branch_id IS NULL) OR
        (role = 'BRANCH_EMPLOYEE' AND seller_id IS NOT NULL AND branch_id IS NOT NULL)
    )
);

-- ---------------------------------------------------------------------
-- 4. Catalog: categories, brands, products, stock per branch
-- ---------------------------------------------------------------------
CREATE TABLE categories (
    id          SMALLINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    code        VARCHAR(10) NOT NULL UNIQUE,              -- 'BEV', 'DRY' ... (same as in the SKU)
    name_en     VARCHAR(60) NOT NULL,
    name_de     VARCHAR(60) NOT NULL,
    name_tr     VARCHAR(60) NOT NULL,
    sort_order  SMALLINT    NOT NULL DEFAULT 0
);

CREATE TABLE brands (
    id             BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    code           VARCHAR(30)  NOT NULL UNIQUE,          -- 'milvara', 'brewmont' ...
    name           VARCHAR(60)  NOT NULL,                 -- never translated
    tagline_en     VARCHAR(60)  NOT NULL,
    tagline_de     VARCHAR(60)  NOT NULL,
    tagline_tr     VARCHAR(60)  NOT NULL,
    primary_color  CHAR(7)      NOT NULL,                 -- '#1D4ED8'
    light_color    CHAR(7)      NOT NULL
);

CREATE TABLE products (
    id           BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    seller_id    BIGINT        NOT NULL REFERENCES sellers (id),   -- A2: catalog belongs to the seller
    category_id  SMALLINT      NOT NULL REFERENCES categories (id),
    brand_id     BIGINT        NOT NULL REFERENCES brands (id),
    sku          VARCHAR(20)   NOT NULL,
    barcode      CHAR(13),                                         -- EAN-13
    name_en      VARCHAR(120)  NOT NULL,
    name_de      VARCHAR(120)  NOT NULL,
    name_tr      VARCHAR(120)  NOT NULL,
    size_en      VARCHAR(20)   NOT NULL,
    size_de      VARCHAR(20)   NOT NULL,
    size_tr      VARCHAR(20)   NOT NULL,
    price        NUMERIC(10,2) NOT NULL CHECK (price > 0),        -- A3: same price on both channels
    sold_by      VARCHAR(6)    NOT NULL DEFAULT 'PIECE'
                 CHECK (sold_by IN ('PIECE', 'WEIGHT')),           -- A9 / US-32
    weight_kg    NUMERIC(6,3),                                     -- nominal weight the price refers to, e.g. 1.000
    CONSTRAINT products_weight CHECK ((sold_by = 'WEIGHT') = (weight_kg IS NOT NULL AND weight_kg > 0)),
    image_path   VARCHAR(255),
    status       VARCHAR(10)   NOT NULL DEFAULT 'ACTIVE'
                 CHECK (status IN ('ACTIVE', 'ARCHIVED')),         -- US-15: archive, do not delete
    created_at   TIMESTAMPTZ   NOT NULL DEFAULT now(),
    updated_at   TIMESTAMPTZ   NOT NULL DEFAULT now(),
    UNIQUE (seller_id, sku),
    UNIQUE (seller_id, barcode)
);

-- A2: the same product can be in stock in branch A and out of stock in branch B
CREATE TABLE branch_stock (
    branch_id   BIGINT      NOT NULL REFERENCES branches (id),
    product_id  BIGINT      NOT NULL REFERENCES products (id),
    quantity    INT         NOT NULL DEFAULT 0 CHECK (quantity >= 0),
    in_stock    BOOLEAN     NOT NULL DEFAULT TRUE,
    updated_at  TIMESTAMPTZ NOT NULL DEFAULT now(),
    PRIMARY KEY (branch_id, product_id),
    CONSTRAINT stock_zero_means_out CHECK (quantity > 0 OR in_stock = FALSE)   -- US-16
);

-- ---------------------------------------------------------------------
-- 5. Orders
-- ---------------------------------------------------------------------
CREATE TABLE orders (
    id                 BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    channel_id         SMALLINT      NOT NULL REFERENCES channels (id),
    external_order_no  VARCHAR(40)   NOT NULL,             -- order number of the channel, e.g. 'HT-10482'
    branch_id          BIGINT        NOT NULL REFERENCES branches (id),
    status             VARCHAR(12)   NOT NULL DEFAULT 'PENDING'
                       CHECK (status IN ('PENDING', 'PICKING', 'READY', 'ON_THE_WAY', 'DELIVERED', 'CANCELLED')),
    total_amount       NUMERIC(10,2) NOT NULL CHECK (total_amount >= 0),   -- amount as ordered by the customer
    bag_count          SMALLINT      CHECK (bag_count BETWEEN 0 AND 20),    -- US-33, entered at READY
    bag_fee            NUMERIC(5,2)  CHECK (bag_fee >= 0),                  -- copy of the branch fee at READY
    final_amount       NUMERIC(10,2) CHECK (final_amount >= 0),             -- after replacements, weights and bags
    customer_note      VARCHAR(500),
    cancelled_by       VARCHAR(10)   CHECK (cancelled_by IN ('BRANCH', 'CHANNEL')),
    cancel_reason      VARCHAR(30)   CHECK (cancel_reason IN
                       ('ITEM_MISSING', 'BRANCH_OVERLOADED', 'CUSTOMER_UNREACHABLE', 'CUSTOMER_CANCELLED', 'OTHER')),   -- US-30
    affects_store_score BOOLEAN,                                        -- A11 / US-36: set by trigger on cancellation
    received_at        TIMESTAMPTZ   NOT NULL DEFAULT now(),
    updated_at         TIMESTAMPTZ   NOT NULL DEFAULT now(),
    -- E2 / idempotency: the same channel order can never be saved twice
    CONSTRAINT orders_channel_order_unique UNIQUE (channel_id, external_order_no),
    -- a cancelled order always has "who" and "why"; other orders have neither
    CONSTRAINT orders_cancel_info CHECK (
        (status = 'CANCELLED'  AND cancelled_by IS NOT NULL AND cancel_reason IS NOT NULL) OR
        (status <> 'CANCELLED' AND cancelled_by IS NULL     AND cancel_reason IS NULL)
    ),
    -- every cancellation is rated: does it count against the store score or not?
    CONSTRAINT orders_score_flag CHECK ((status = 'CANCELLED') = (affects_store_score IS NOT NULL)),
    CONSTRAINT orders_score_fair CHECK (
        NOT (affects_store_score AND (cancelled_by = 'CHANNEL'
             OR cancel_reason IN ('CUSTOMER_UNREACHABLE', 'CUSTOMER_CANCELLED')))
    ),
    -- from READY on, the final amount and the bags are known (section 8 of the order process)
    CONSTRAINT orders_final_amount CHECK (
        (status IN ('READY', 'ON_THE_WAY', 'DELIVERED')) =
        (final_amount IS NOT NULL AND bag_count IS NOT NULL AND bag_fee IS NOT NULL)
    )
);

CREATE TABLE order_items (
    id          BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    order_id    BIGINT        NOT NULL REFERENCES orders (id),
    product_id  BIGINT        NOT NULL REFERENCES products (id),
    quantity    INT           NOT NULL CHECK (quantity > 0),
    unit_price  NUMERIC(10,2) NOT NULL CHECK (unit_price >= 0),   -- US-14: price at order time (snapshot)
    -- picking result (section 8 of the order process)
    line_status            VARCHAR(10)   NOT NULL DEFAULT 'OPEN'
                           CHECK (line_status IN ('OPEN', 'PICKED', 'PARTIAL', 'REPLACED', 'REMOVED')),
    picked_quantity        INT           CHECK (picked_quantity >= 0 AND picked_quantity <= quantity),   -- US-34
    picked_weight_kg       NUMERIC(6,3)  CHECK (picked_weight_kg > 0),          -- US-32: only for WEIGHT products
    replacement_product_id BIGINT        REFERENCES products (id),              -- US-31
    replacement_quantity   INT           CHECK (replacement_quantity > 0),
    replacement_unit_price NUMERIC(10,2) CHECK (replacement_unit_price >= 0),
    customer_decision      VARCHAR(12)   CHECK (customer_decision IN ('APPROVED', 'DECLINED', 'NOT_REACHED')),
    final_amount           NUMERIC(10,2) CHECK (final_amount >= 0),             -- amount actually charged for this line
    UNIQUE (order_id, product_id),
    -- a replacement always needs the new product, its quantity, its price and the customer's "yes"
    CONSTRAINT items_replacement CHECK (
        (line_status = 'REPLACED' AND replacement_product_id IS NOT NULL AND replacement_quantity IS NOT NULL
                                  AND replacement_unit_price IS NOT NULL AND customer_decision = 'APPROVED') OR
        (line_status <> 'REPLACED' AND replacement_product_id IS NULL AND replacement_quantity IS NULL
                                   AND replacement_unit_price IS NULL)
    ),
    -- the line status must match the counted quantity (US-34)
    CONSTRAINT items_quantity_status CHECK (
        (line_status = 'OPEN') OR
        (line_status = 'PICKED'   AND picked_quantity = quantity) OR
        (line_status = 'PARTIAL'  AND picked_quantity > 0 AND picked_quantity < quantity) OR
        (line_status = 'REPLACED' AND picked_quantity < quantity) OR
        (line_status = 'REMOVED'  AND picked_quantity = 0)
    ),
    -- a missing part is only accepted after talking to the customer, a removed line is never charged
    CONSTRAINT items_missing_needs_customer CHECK (
        line_status NOT IN ('PARTIAL', 'REMOVED') OR customer_decision IS NOT NULL
    ),
    CONSTRAINT items_removed_free CHECK (line_status <> 'REMOVED' OR final_amount = 0)
);

-- Every call to a customer is logged (A11, US-35). It is the evidence for removals and cancellations.
CREATE TABLE customer_calls (
    id               BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    order_id         BIGINT      NOT NULL REFERENCES orders (id),
    order_item_id    BIGINT      REFERENCES order_items (id),          -- set when the call is about one line
    purpose          VARCHAR(20) NOT NULL CHECK (purpose IN ('MISSING_ITEM', 'WEIGHT', 'CANCELLATION')),
    result           VARCHAR(10) NOT NULL CHECK (result IN ('NO_ANSWER', 'REACHED')),
    customer_answer  VARCHAR(25) CHECK (customer_answer IN
                     ('AGREED_REPLACEMENT', 'AGREED_REMOVAL', 'AGREED_CANCELLATION', 'DECLINED')),
    called_by        BIGINT      NOT NULL REFERENCES users (id),
    called_at        TIMESTAMPTZ NOT NULL DEFAULT now(),
    note             VARCHAR(300),
    CONSTRAINT calls_answer_when_reached CHECK ((result = 'REACHED') = (customer_answer IS NOT NULL))
);

-- Every barcode scan and every manual count while picking (A13, US-37, US-38)
CREATE TABLE picking_scans (
    id             BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    order_id       BIGINT      NOT NULL REFERENCES orders (id),
    order_item_id  BIGINT      REFERENCES order_items (id),         -- the line that was counted, if any
    method         VARCHAR(6)  NOT NULL CHECK (method IN ('SCAN', 'MANUAL')),
    scanned_code   VARCHAR(20),                                     -- the barcode read; NULL for MANUAL
    result         VARCHAR(25) NOT NULL CHECK (result IN
                   ('COUNTED', 'ALREADY_COMPLETE', 'NOT_IN_ORDER', 'REPLACEMENT_NOT_APPROVED', 'UNKNOWN_BARCODE')),
    user_id        BIGINT      NOT NULL REFERENCES users (id),
    scanned_at     TIMESTAMPTZ NOT NULL DEFAULT now(),
    CONSTRAINT scans_code_when_scanned CHECK ((method = 'SCAN') = (scanned_code IS NOT NULL)),
    CONSTRAINT scans_manual_counts      CHECK (method = 'SCAN' OR (result = 'COUNTED' AND order_item_id IS NOT NULL)),
    CONSTRAINT scans_counted_has_line   CHECK (result <> 'COUNTED' OR order_item_id IS NOT NULL)
);

-- Cancellation guard (A11): a branch may only cancel after contacting the customer.
--   * reason CUSTOMER_UNREACHABLE needs at least 2 unanswered calls -> does NOT count against the store score
--   * any other branch cancellation needs 2 unanswered calls or the customer's "yes" -> counts against the score
--   * cancellations by the channel (the customer cancelled in the app) never count
CREATE FUNCTION orders_cancel_guard() RETURNS trigger AS $$
DECLARE
    no_answer INT;
    agreed    BOOLEAN;
BEGIN
    IF NEW.status <> 'CANCELLED' OR OLD.status = 'CANCELLED' THEN
        RETURN NEW;
    END IF;
    IF NEW.cancelled_by = 'CHANNEL' THEN
        NEW.affects_store_score := FALSE;
        RETURN NEW;
    END IF;
    SELECT count(*) INTO no_answer FROM customer_calls WHERE order_id = NEW.id AND result = 'NO_ANSWER';
    SELECT EXISTS (SELECT 1 FROM customer_calls WHERE order_id = NEW.id
                   AND result = 'REACHED' AND customer_answer = 'AGREED_CANCELLATION') INTO agreed;
    IF NEW.cancel_reason = 'CUSTOMER_UNREACHABLE' THEN
        IF no_answer < 2 THEN
            RAISE EXCEPTION 'Order %: % unanswered calls logged, 2 are required', NEW.id, no_answer;
        END IF;
        NEW.affects_store_score := FALSE;
    ELSIF NEW.cancel_reason = 'CUSTOMER_CANCELLED' THEN
        IF NOT agreed THEN
            RAISE EXCEPTION 'Order %: no logged call in which the customer asked to cancel', NEW.id;
        END IF;
        NEW.affects_store_score := FALSE;
    ELSE
        IF NOT agreed AND no_answer < 2 THEN
            RAISE EXCEPTION 'Order %: the customer must be contacted before the branch cancels', NEW.id;
        END IF;
        NEW.affects_store_score := TRUE;
    END IF;
    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER orders_cancel_guard
    BEFORE UPDATE OF status ON orders
    FOR EACH ROW EXECUTE FUNCTION orders_cancel_guard();

-- Every status change is one row: who, when, from what, to what (US-08, timings for G2)
CREATE TABLE order_status_history (
    id           BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    order_id     BIGINT      NOT NULL REFERENCES orders (id),
    from_status  VARCHAR(12),                                  -- NULL for the first entry
    to_status    VARCHAR(12) NOT NULL,
    source       VARCHAR(10) NOT NULL CHECK (source IN ('USER', 'CHANNEL', 'SYSTEM')),
    user_id      BIGINT      REFERENCES users (id),            -- set when source = 'USER'
    changed_at   TIMESTAMPTZ NOT NULL DEFAULT now(),
    note         VARCHAR(300),
    CONSTRAINT history_user_when_user CHECK ((source = 'USER') = (user_id IS NOT NULL))
);

-- ---------------------------------------------------------------------
-- 6. Returns
-- ---------------------------------------------------------------------
CREATE TABLE returns (
    id                  BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    order_id            BIGINT        NOT NULL REFERENCES orders (id),
    external_return_no  VARCHAR(40)   NOT NULL,
    status              VARCHAR(10)   NOT NULL DEFAULT 'REQUESTED'
                        CHECK (status IN ('REQUESTED', 'APPROVED', 'REJECTED')),
    customer_reason     VARCHAR(500)  NOT NULL,
    refund_amount       NUMERIC(10,2) NOT NULL CHECK (refund_amount >= 0),
    requested_at        TIMESTAMPTZ   NOT NULL DEFAULT now(),
    decided_at          TIMESTAMPTZ,
    decided_by          BIGINT        REFERENCES users (id),
    rejection_reason    VARCHAR(300),
    UNIQUE (order_id, external_return_no),
    CONSTRAINT returns_rejection_reason CHECK (status <> 'REJECTED' OR rejection_reason IS NOT NULL)  -- US-19
);

CREATE TABLE return_items (
    id             BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    return_id      BIGINT NOT NULL REFERENCES returns (id),
    order_item_id  BIGINT NOT NULL REFERENCES order_items (id),
    quantity       INT    NOT NULL CHECK (quantity > 0),
    UNIQUE (return_id, order_item_id)
);

-- ---------------------------------------------------------------------
-- 7. Audit log (rule R4) – rows are only ever inserted, never changed
-- ---------------------------------------------------------------------
CREATE TABLE audit_log (
    id           BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    occurred_at  TIMESTAMPTZ NOT NULL DEFAULT now(),
    user_id      BIGINT      REFERENCES users (id),            -- NULL for channel / system actions
    action       VARCHAR(40) NOT NULL,                         -- e.g. 'PRODUCT_PRICE_CHANGED', 'LOGIN_FAILED'
    entity_type  VARCHAR(40) NOT NULL,                         -- e.g. 'PRODUCT', 'ORDER', 'USER'
    entity_id    BIGINT,
    old_value    JSONB,
    new_value    JSONB,
    ip_address   VARCHAR(45)
);

CREATE FUNCTION audit_log_is_append_only() RETURNS trigger AS $$
BEGIN
    RAISE EXCEPTION 'audit_log is append-only: % is not allowed', TG_OP;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER audit_log_no_update_delete
    BEFORE UPDATE OR DELETE ON audit_log
    FOR EACH ROW EXECUTE FUNCTION audit_log_is_append_only();

-- ---------------------------------------------------------------------
-- 8. Indexes for the most frequent screens
-- ---------------------------------------------------------------------
CREATE INDEX idx_orders_branch_status   ON orders (branch_id, status, received_at DESC);   -- S-04 tabs
CREATE INDEX idx_orders_channel         ON orders (channel_id, received_at DESC);           -- channel switch, reports
CREATE INDEX idx_order_items_product    ON order_items (product_id);                        -- product report
CREATE INDEX idx_history_order          ON order_status_history (order_id, changed_at);
CREATE INDEX idx_calls_order            ON customer_calls (order_id, called_at);
CREATE INDEX idx_scans_user_time        ON picking_scans (user_id, scanned_at);              -- US-38 per employee
CREATE INDEX idx_scans_order            ON picking_scans (order_id);
CREATE INDEX idx_orders_score           ON orders (branch_id, received_at) WHERE affects_store_score;   -- US-36
CREATE INDEX idx_returns_status         ON returns (status);
CREATE INDEX idx_products_seller_status ON products (seller_id, status);
CREATE INDEX idx_audit_entity           ON audit_log (entity_type, entity_id);
CREATE INDEX idx_audit_time             ON audit_log (occurred_at DESC);
