# 01 – Scope

🇬🇧 **English** · 🇩🇪 [Deutsch](01-scope.de.md) · 🇹🇷 [Türkçe](01-scope.tr.md)

## 1. Problem
A grocery store sells through two delivery platforms, **HTGO Market** and **GK Market**. Each platform has its own seller panel, so the staff:
- switch between two screens all day and sometimes miss or delay orders,
- update product availability twice, and the two channels drift apart (a product is sold out on one channel but still orderable on the other),
- have no combined view of revenue, so management copies numbers from two reports into Excel by hand.

## 2. Goal
**VetisPilot** is one seller panel for both channels. Orders from HTGO and GK arrive in a single list, products are managed once, and reports show both channels side by side.

| # | Business goal | How we measure it |
|---|---|---|
| G1 | No missed orders | Every new order is visible in the panel within 5 seconds |
| G2 | Faster order handling | Time from *Pending* to *Ready* is shown per order and per branch |
| G3 | One source of truth for products | A product change is made once and applies to both channels |
| G4 | Combined reporting | Daily revenue per channel, per branch and per product without manual Excel work |
| G5 | Reliable stores | The share of cancellations caused by the store is shown per branch (store score); target ≤ 2 % |

## 3. Stakeholders
| Stakeholder | Interest |
|---|---|
| **Admin** (operator of VetisPilot) | Manages sellers, branches and users; sees everything |
| **Seller** (business owner) | Revenue, product range and performance of their own branches |
| **Branch employee** | Handles incoming orders of their branch quickly |
| **HTGO Market / GK Market** (external systems) | Send orders and status updates; need a reliable, secure interface |
| **End customer** (indirect) | Gets the right order on time; not a user of VetisPilot |

## 4. Scope
### In scope – MVP
- Login with roles (Admin, Seller, Branch employee)
- User interface in **English, German and Turkish**, switchable by the user
- Receive orders from both channels through a secured interface (HMAC signature)
- Order list filtered by channel, status and branch; order status flow
- Product management: create, edit, delete, price, image, in stock / out of stock
- Branch management and user profile
- Dashboard: today's revenue, active orders, pending returns
- Returns: record, approve or reject
- Channel simulator that generates realistic HTGO and GK orders
- Audit log of important changes
- Picking rules: replace a missing product with the customer's approval, weigh products sold by weight, charge a bag fee – the final amount is sent to the channel
- Cancellation guard and store score: the branch can cancel only after contacting the customer (2-call rule); store-caused cancellations are shown as a score
- Barcode check while picking (handheld scanner or phone camera); manual counts are logged
- Channel selection when entering the panel: work in HTGO Market or GK Market, in that channel's colors

### In scope – after MVP
- Reports: HTGO sales, GK sales, product report (with export)
- Admin panel for sellers, branches and users
- Channel-specific prices and channel commission in reports

### Out of scope
- Customer ordering app or website
- Payments (the channels collect the money; VetisPilot only records amounts)
- Courier management and live delivery tracking
- Connection to real delivery platforms

## 5. Assumptions (confirmed on 22 Nov 2026)
| # | Assumption | Why it matters |
|---|---|---|
| A1 | A seller owns one or more branches. Every branch receives orders from both channels. | Defines what a seller and a branch employee can see |
| A2 | The product catalog belongs to the seller. Stock (quantity, in stock / out of stock) is kept **per branch**. | The same milk can be sold out in branch A and available in branch B |
| A3 | In the MVP a product has the same price on both channels. | Keeps pricing simple; channel prices come later |
| A4 | The branch sets *Pending → Picking → Ready*. The channel's courier picks the order up, so *On the way* and *Delivered* are sent by the channel. | Splits responsibility between our system and the channel |
| A5 | An order can be cancelled until it is *Ready*: by the branch (e.g. item missing) or by the channel (customer cancels). | Defines the cancel rules in the status flow |
| A6 | Returns are requested by the customer through the channel; the branch or seller approves or rejects them. | Defines who works on returns |
| A7 | Currency EUR, time zone Europe/Berlin, user interface in English, German and Turkish. Brand names are never translated; product names, sizes and categories are. | Affects reports, date/number formats and all screen texts |
| A8 | A missing product may be replaced only after the customer agreed by phone. The customer pays the price of the replacement; the difference (e.g. +€0.10) changes the final amount. If the customer cannot be reached after 2 calls, the item is removed and not charged. | Added on 22 Nov 2026; no surprise for the customer |
| A9 | Products sold by weight may deviate from the ordered weight by ±10 % (e.g. 1.10 kg instead of 1 kg). The customer pays the actual weight. Outside this range the customer must agree first. | Added on 22 Nov 2026; fruit, vegetables and fresh meat cannot be cut exactly |
| A10 | Every bag costs €0.25 (setting per branch). The number of bags is entered when the order is ready and added to the final amount. | Added on 22 Nov 2026; encourages fewer bags |
| A11 | The branch cannot cancel an order on its own. It must call the customer first: if the customer agrees, the order is cancelled and this counts against the store score; after 2 unanswered calls the system cancels it as *customer unreachable* without affecting the store score. Every call is logged. | Added on 22 Nov 2026; protects customers and makes every cancellation traceable |
| A12 | The employee counts every line (picked / ordered, e.g. 2 of 3). A missing part is handled like a missing item (call, replace or remove). An order can only be marked ready when every line is resolved. | Added on 22 Nov 2026; no wrong quantity leaves the store |
| A13 | After login the user chooses one channel (HTGO Market or GK Market); the panel then works only in that channel and in its colors. A manual count (+ without a scan) is allowed but always logged as *manual*. | Added on 22 Nov 2026; clear focus per channel, traceable picking quality |

## 6. Constraints
- Solo project, first working version (MVP) before reports and admin; everything finished by **end of January 2027**
- All brands (channels, products) are fictional; no real brand names, logos or colour identities
- Runs locally with one command (Docker Compose); secrets stay in `.env` and never go to GitHub
