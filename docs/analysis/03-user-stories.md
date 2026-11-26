# 03 – User Stories

🇬🇧 **English** · 🇩🇪 [Deutsch](03-user-stories.de.md) · 🇹🇷 [Türkçe](03-user-stories.tr.md)

## How to read this document
- **Format:** *As a* &lt;role&gt; *I want* &lt;function&gt; *so that* &lt;benefit&gt;.
- **Acceptance criteria** use *Given / When / Then*. A story is done only when all its criteria pass.
- **Priority (MoSCoW):** **M** = Must (MVP) · **S** = Should (after MVP) · **C** = Could (if time allows)
- **Goal** links each story to the business goals in [01 – Scope](01-scope.md) (G1–G4). **–** = enabling story (security, setup).

## Overview
| ID | Epic | Story (short) | Role | Prio | Goal |
|---|---|---|---|---|---|
| US-01 | Access | Log in | All users | M | – |
| US-02 | Access | Block brute-force login attempts | All users | M | – |
| US-03 | Access | Switch language EN / DE / TR | All users | M | – |
| US-04 | Access | See only my own data | Seller, Branch employee | M | – |
| US-05 | Orders | Receive a signed order from a channel | Channel | M | G1 |
| US-06 | Orders | See new orders immediately | Branch employee | M | G1 |
| US-07 | Orders | Order list with status tabs and channel switch | Branch employee, Seller | M | G1 |
| US-08 | Orders | Open order details | Branch employee | M | G2 |
| US-09 | Orders | Start picking an order | Branch employee | M | G2 |
| US-10 | Orders | Mark an order as ready | Branch employee | M | G2 |
| US-11 | Orders | Receive *On the way* / *Delivered* from the channel | Channel | M | G2 |
| US-12 | Orders | Cancel an order | Branch employee, Channel | M | G1 |
| US-13 | Products | Create a product | Seller | M | G3 |
| US-14 | Products | Edit price, image and details | Seller | M | G3 |
| US-15 | Products | Remove a product | Seller | M | G3 |
| US-16 | Products | Mark a product out of stock in my branch | Branch employee | M | G3 |
| US-17 | Products | Search and filter products | Seller, Branch employee | M | G3 |
| US-18 | Returns | Receive a return request from a channel | Channel | M | G4 |
| US-19 | Returns | Approve or reject a return | Branch employee, Seller | M | G4 |
| US-20 | Dashboard | See today's key figures | All users | M | G1, G4 |
| US-21 | Branches & users | Manage branch details | Seller | M | – |
| US-22 | Branches & users | Manage branch employees | Seller | M | – |
| US-23 | Branches & users | Edit my profile and password | All users | M | – |
| US-24 | Admin | Manage sellers and branches | Admin | S | – |
| US-25 | Admin | View the audit log | Admin | M | – |
| US-26 | Reports | Sales report per channel | Seller, Admin | S | G4 |
| US-27 | Reports | Product report | Seller, Admin | S | G4 |
| US-28 | Reports | Export a report as CSV | Seller, Admin | C | G4 |
| US-29 | Simulator | Generate realistic demo orders | Demo presenter | M | – |
| US-30 | Reports | Cancellation reasons report | Seller, Admin | S | G4 |
| US-31 | Picking | Replace a missing product | Branch employee | M | G1 |
| US-32 | Picking | Enter the actual weight | Branch employee | M | G4 |
| US-33 | Picking | Charge a bag fee | Branch employee | M | G4 |
| US-34 | Picking | Count every line while picking | Branch employee | M | G2 |
| US-35 | Picking | Log every call to the customer | Branch employee | M | G5 |
| US-36 | Dashboard | See the store score | Seller, Branch employee | M | G5 |
| US-37 | Picking | Check every scanned barcode | Branch employee | M | G2 |
| US-38 | Reports | Picking quality report | Seller, Admin | S | G2 |
| US-39 | Access | Choose the channel when entering the panel | Branch employee, Seller | M | G1 |
| US-40 | Products | Open or close a product for sale per channel | Branch employee | M | G3 |
| US-41 | Orders | Get a new-order alert with sound | Branch employee | M | G1 |
| US-42 | Orders | See how long orders wait | Branch employee | M | G1 |
| US-43 | Orders | WhatsApp reminder for waiting orders | Branch manager | S | G1 |
| US-44 | Picking | Protect the final amount | Seller, Branch employee | M | G4 |
| US-45 | Picking | Remove missing products with the customer's approval | Branch employee | M | G4 |
| US-46 | Picking | Split orders over the courier capacity | Branch employee | M | G2 |
| US-47 | Returns | Return only some products | Branch employee | M | G4 |
| US-48 | Returns | Receive returned products with a QR code | Branch employee, Courier | M | G4 |
| US-49 | Returns | Refund after the return is accepted | Customer (via channel) | M | G4 |
| US-50 | Returns | See and filter reasons | Branch employee | S | G4 |
| US-51 | Dashboard | See customer ratings | Seller, Branch employee | S | G5 |
| US-52 | Reports | Sales report per day, week, month, year | Seller | S | G4 |
| US-53 | Access | Day and night mode | All users | C | – |

---

## Epic 1 – Access

### US-01 – Log in · M
*As a* user *I want* to log in with my email and password *so that* only I can work with my data.
- **Given** a valid email and password, **when** I log in, **then** I see the dashboard for my role.
- **Given** a wrong password, **when** I log in, **then** I see "Email or password is incorrect" (the message does not reveal which one was wrong).
- **Given** I was inactive for 8 hours, **when** I click anything, **then** I am logged out and sent to the login page.

### US-02 – Block brute-force login attempts · M
*As a* user *I want* my account to be protected against password guessing *so that* nobody can break in by trying many passwords.
- **Given** 5 failed logins for the same account within 15 minutes, **when** a 6th attempt is made, **then** the account is locked for 15 minutes and the attempt is written to the audit log.
- **Given** a locked account, **when** the lock time is over, **then** I can log in again normally.

### US-03 – Switch language EN / DE / TR · M
*As a* user *I want* to switch the interface between English, German and Turkish *so that* I can work in the language I know best.
- **Given** I am logged in, **when** I choose 🇬🇧 EN, 🇩🇪 DE or 🇹🇷 TR, **then** all screen texts, product names, categories, dates and prices change to that language and format.
- **Given** I chose a language, **when** I log in next time, **then** the panel opens in that language.
- Brand names are never translated.

### US-04 – See only my own data · M
*As a* seller or branch employee *I want* to see only the data I am responsible for *so that* business data of other sellers stays private.
- **Given** I am a seller, **when** I open any list, **then** I only see my own branches, products, orders and returns.
- **Given** I am a branch employee, **when** I open any list, **then** I only see data of my own branch.
- **Given** I change an ID in the URL to another seller's order, **when** the page loads, **then** I get "Not found" and the attempt is written to the audit log.

## Epic 2 – Orders

### US-05 – Receive a signed order from a channel · M
*As a* channel (HTGO / GK) *I want* to send new orders to VetisPilot *so that* the branch can prepare them.
- **Given** an order with a valid HMAC signature, **when** it is sent, **then** it is saved with status *Pending*, the channel and the branch, and the channel gets a confirmation.
- **Given** an order with a missing or wrong signature, **when** it is sent, **then** it is rejected and nothing is saved.
- **Given** the same order number is sent twice, **when** the second one arrives, **then** no duplicate is created.
- **Given** an order contains a product that is out of stock in that branch, **when** it arrives, **then** it is saved and the item is highlighted for the branch.

### US-06 – See new orders immediately · M
*As a* branch employee *I want* new orders to appear without reloading the page *so that* no order is missed.
- **Given** the order list is open, **when** a new order arrives, **then** it appears at the top within 5 seconds with a "New" badge and a sound.
- **Given** an order has been *Pending* for more than 3 minutes, **when** I look at the list, **then** it is highlighted in red.

### US-07 – Order list with status tabs and channel switch · M
*As a* branch employee or seller *I want* the order list split into status tabs with live counts, for both channels, *so that* I see at a glance what needs to be done.
- **Given** I open "Orders", **then** I see the tabs **Pending (n)**, **Picking (n)**, **Ready (n)**, **All (n)** and **Cancelled (n)**; *n* is the number of orders in that tab. The *Pending* tab is open by default.
- **Given** the channel chosen when entering the panel (US-39), **then** the list and all tab counts show only that channel's orders. Every order row shows its channel badge.
- **Given** a new order or a status change, **then** the tab counts update within 5 seconds without reloading.
- **Given** the *All* tab, **then** it contains every order including *On the way* and *Delivered*; I can filter it by status, branch (seller only) and date.
- **Given** the *Cancelled* tab, **then** each row also shows the cancellation reason and who cancelled (branch or channel).
- **Given** I type an order number, **when** I search, **then** the matching order is shown, whatever tab is open.

### US-08 – Open order details · M
*As a* branch employee *I want* to see all details of an order *so that* I can pick the right items.
- **Given** an order, **when** I open it, **then** I see channel, order number, time, items (name, size, quantity, price), total, customer note and status history (who changed what, when).

### US-09 – Start picking an order · M
*As a* branch employee *I want* to change an order from *Pending* to *Picking* *so that* everyone sees it is being worked on.
- **Given** an order in status *Pending*, **when** I click "Start picking", **then** the status becomes *Picking*, my name and the time are saved, and the channel is informed.
- **Given** an order in any other status, **then** the button is not available.

### US-10 – Mark an order as ready · M
*As a* branch employee *I want* to mark an order as *Ready* *so that* the channel's courier can pick it up.
- **Given** an order in status *Picking*, **when** I click "Ready", **then** the status becomes *Ready*, the preparation time (*Pending* → *Ready*) is saved, and the channel is informed.

### US-11 – Receive *On the way* / *Delivered* from the channel · M
*As a* channel *I want* to report when the courier picked up and delivered an order *so that* the branch sees the full order life cycle.
- **Given** an order in status *Ready*, **when** the channel sends a signed "picked up" message, **then** the status becomes *On the way*.
- **Given** an order in status *On the way*, **when** the channel sends "delivered", **then** the status becomes *Delivered* and the order counts in the revenue.
- **Given** a status message that does not fit the current status (e.g. *Delivered* for a *Pending* order), **then** it is rejected and logged.

### US-12 – Cancel an order · M
*As a* branch employee or channel *I want* to cancel an order before it is ready *so that* customers are not charged for orders that cannot be delivered.
- **Given** an order in status *Pending* or *Picking*, **when** the branch cancels it, **then** a reason must be chosen (e.g. "item missing"), the status becomes *Cancelled* and the channel is informed.
- **Given** an order in status *Pending* or *Picking*, **when** the channel cancels it, **then** the status becomes *Cancelled* and the branch sees a notification.
- **Given** an order in status *Ready* or later, **then** it can no longer be cancelled.
- Orders are never deleted (rule R3).
- **Given** the branch wants to cancel, **then** a reason must be chosen first (nothing is pre-selected) and the button turns from grey to red. *Branch overloaded* is allowed at once; *customer unreachable* and *item out of stock* only after 2 logged calls (rule R6, updated – see [06 §4](06-prototype-decisions.md)).
- **Given** 2 calls got no answer, **then** the system cancels with reason *customer unreachable*, and the cancellation does **not** count against the store score (US-36). Cancellations for other reasons do count.

## Epic 3 – Products and stock

### US-13 – Create a product · M
*As a* seller *I want* to add a new product *so that* it can be sold on both channels.
- **Given** the product form, **when** I enter name (EN / DE / TR), brand, category, size, price and an optional image and save, **then** the product is available in all my branches.
- **Given** a required field is empty or the price is not a positive number, **when** I save, **then** I see an error next to the field and nothing is saved.
- **Given** an image larger than 2 MB or not JPG / PNG / WebP, **when** I upload it, **then** it is rejected with a message.

### US-14 – Edit price, image and details · M
*As a* seller *I want* to change a product *so that* both channels always show the correct information.
- **Given** a product, **when** I change its price and save, **then** the new price applies to new orders on both channels; existing orders keep their old price.
- **Given** any change, **then** the old and new values are written to the audit log.

### US-15 – Remove a product · M
*As a* seller *I want* to remove a product I no longer sell *so that* it cannot be ordered anymore.
- **Given** a product that has never been ordered, **when** I delete it, **then** it is removed.
- **Given** a product that appears in past orders, **when** I delete it, **then** it is archived instead: it disappears from the product list and the channels, but old orders and reports still show it.

### US-16 – Mark a product out of stock in my branch · M
*As a* branch employee *I want* to switch a product between "in stock" and "out of stock" for my branch *so that* customers cannot order what we do not have.
- **Given** a product, **when** I switch it to "out of stock", **then** it is unavailable on both channels for my branch only, within 5 seconds.
- **Given** a product's stock quantity reaches 0, **then** it is set to "out of stock" automatically.
- **Given** a stock quantity below 10, **then** the product shows a "Low stock" badge.

### US-17 – Search and filter products · M
*As a* seller or branch employee *I want* to search and filter products *so that* I can find one product among many.
- **Given** the product list, **when** I search by name, SKU or barcode, or filter by category, brand or stock status, **then** only matching products are shown.

## Epic 4 – Returns

### US-18 – Receive a return request from a channel · M
*As a* channel *I want* to send a customer's return request *so that* the branch can decide on it.
- **Given** a delivered order, **when** the channel sends a signed return request with items and reason, **then** a return with status *Requested* is created and appears on the dashboard as "pending return".
- **Given** an order that is not *Delivered*, **then** the request is rejected.

### US-19 – Approve or reject a return · M
*As a* branch employee or seller *I want* to approve or reject a return *so that* the customer gets a clear answer.
- **Given** a return in status *Requested*, **when** I approve it, **then** the status becomes *Approved*, the amount is subtracted from revenue and the channel is informed.
- **Given** a return in status *Requested*, **when** I reject it, **then** a reason is required, the status becomes *Rejected* and the channel is informed.

## Epic 5 – Dashboard

### US-20 – See today's key figures · M
*As a* user *I want* a dashboard with today's most important numbers *so that* I see the situation at a glance.
- **Given** I open the dashboard, **then** I see for my scope (all / my branches / my branch): today's revenue (total and per channel), number of active orders per status, pending returns and products that are out of stock or low on stock.
- **Given** a new order or status change, **then** the numbers update without reloading the page.

## Epic 6 – Branches and users

### US-21 – Manage branch details · M
*As a* seller *I want* to edit my branch details (name, address, opening hours, open / closed) *so that* channels only send orders when a branch is open.
- **Given** I set a branch to "closed", **then** channels receive a rejection for new orders to that branch.

### US-22 – Manage branch employees · M
*As a* seller *I want* to create, edit and deactivate employee accounts for my branches *so that* my staff can use the panel.
- **Given** I create an employee with name, email and branch, **then** the account gets a temporary password that must be changed at first login.
- **Given** I deactivate an employee, **then** that person can no longer log in, but their past actions stay in the audit log.

### US-23 – Edit my profile and password · M
*As a* user *I want* to change my name, language and password *so that* my account is up to date and secure.
- **Given** I change my password, **then** the new password must have at least 10 characters with letters and numbers, and the old password is required.

## Epic 7 – Admin

### US-24 – Manage sellers and branches · S
*As an* admin *I want* to create, edit and deactivate sellers and their branches *so that* new businesses can be onboarded.
- **Given** I create a seller, **then** the seller's first user account is created with a temporary password.
- **Given** I deactivate a seller, **then** all of the seller's users are blocked and the branches stop accepting orders.

### US-25 – View the audit log · M
*As an* admin *I want* to see who changed what and when *so that* I can trace problems and misuse.
- **Given** the audit log, **when** I filter by user, date or area, **then** I see time, user, action, object, old value and new value.
- Audit entries cannot be edited or deleted by anyone.

## Epic 8 – Reports

### US-26 – Sales report per channel · S
*As a* seller or admin *I want* a sales report per channel (HTGO, GK) *so that* I can compare the channels.
- **Given** a date range, **then** I see per channel: number of orders, revenue, average order value, cancellations and returns, also per branch.

### US-27 – Product report · S
*As a* seller or admin *I want* a product report *so that* I know which products sell well or badly.
- **Given** a date range, **then** I see per product: quantity sold, revenue, share per channel and how often it was out of stock.

### US-28 – Export a report as CSV · C
*As a* seller or admin *I want* to download a report as CSV *so that* I can analyse it further in Excel.
- **Given** a report on screen, **when** I click "Export", **then** a CSV file with exactly the shown data is downloaded.

### US-30 – Cancellation reasons report · S
*As a* seller or admin *I want* to see why orders were cancelled *so that* I can fix the cause (e.g. poor stock management).
- **Given** a date range, **then** I see the number of cancellations per reason (e.g. item missing, branch overloaded, customer cancelled) as a chart, per channel and per branch.
- **Given** I click a reason, **then** I see the list of those cancelled orders.

## Epic 9 – Channel simulator

### US-29 – Generate realistic demo orders · M
*As a* demo presenter *I want* a simulator that behaves like HTGO and GK *so that* I can show the panel working live.
- **Given** the simulator is running, **then** it sends signed orders with products from the demo catalog to random open branches at a configurable rate (e.g. one order every 20 seconds).
- **Given** an order is *Ready*, **then** the simulator sends *On the way* and later *Delivered*, and occasionally a cancellation or return request.

## Epic 10 – Picking and final amount

### US-31 – Replace a missing product · M
*As a* branch employee *I want* to offer the customer a replacement when a product is missing *so that* the customer still gets what they need and pays the right price.
- **Given** an item is not available while picking, **when** I click "Replace…", **then** I see in-stock products of the same category, sorted by how close their price is, and the customer's phone number provided by the channel.
- **Given** I called the customer and they agreed, **when** I choose the replacement, **then** the line shows the original and the new product, the new price is used and the difference is shown (e.g. Semi-Skimmed Milk €1.19 → Whole Milk €1.29 = **+€0.10**).
- **Given** the customer declines or cannot be reached after 2 calls (US-35), **then** the item is removed and not charged.
- The final amount drops by the price of the removed item (e.g. a missing pack of croissants for €2.49 lowers the total by €2.49).
- Every replacement and the customer's answer are saved (who, when) and shown in the status history.

### US-32 – Enter the actual weight · M
*As a* branch employee *I want* to enter the real weight of products sold by weight *so that* the customer pays exactly for what they get.
- **Given** a product sold by weight (e.g. Bananas, €1.69 / kg, ordered 1 kg), **when** I enter 1.10 kg, **then** the line amount becomes **€1.86** (1.10 × 1.69, rounded to the cent).
- **Given** a weight outside ±10 % of the ordered weight (e.g. 1.20 kg instead of 1 kg), **then** the system asks me to re-weigh or to get the customer's approval first.
- Products not sold by weight have no weight field.

### US-33 – Charge a bag fee · M
*As a* branch employee *I want* to enter how many bags I used *so that* the bag fee is charged correctly.
- **Given** an order in status *Picking*, **when** I click "Mark as ready", **then** I must enter the number of bags (0–20).
- **Then** the bag fee (bags × fee of the branch, default **€0.25**) is added; the **final amount** = all final line amounts + bag fee is saved and sent to the channel together with the status *Ready*.
- **Given** the customer asked for no bag (channel note), **then** 0 bags is suggested.
- The seller can change the bag fee per branch in the branch settings (S-09).

### US-34 – Count every line while picking · M
*As a* branch employee *I want* to count how many pieces of each product I picked *so that* the customer gets exactly the ordered quantity.
- **Given** an order in status *Picking*, **then** every line shows "picked / ordered" (e.g. *Fizzbay Classic Cola · 1 L — 0 / 3*) with **−** and **+** buttons; scanning the barcode adds 1.
- **Given** picked = ordered, **then** the line turns green. The counter cannot go above the ordered quantity.
- **Given** picked < ordered (e.g. 2 / 3), **then** the missing part is handled like a missing item (US-31): call the customer, then replace or remove it. The final amount only counts what was really picked.
- **Given** at least one line is not resolved, **then** "Mark as ready" is disabled.

### US-35 – Log every call to the customer · M
*As a* branch employee *I want* every call to the customer to be recorded *so that* removals and cancellations are traceable.
- **Given** I call the customer from the order, **then** I record the result: *no answer*, or *reached* together with the customer's answer (agrees to replacement / removal / cancellation, or declines).
- **Given** a call got no answer, **then** the next attempt is possible after 2 minutes at the earliest; the panel shows "attempt 1 of 2".
- The call log is shown in the order detail and cannot be changed afterwards.

## Epic 11 – Store score

### US-36 – See the store score · M
*As a* seller or branch employee *I want* to see how many cancellations my store caused *so that* we can improve and keep a good rating with the channels.
- **Given** the dashboard, **then** I see the store score: store-caused cancellations ÷ all orders of the last 30 days (target ≤ 2 %), per branch and per channel.
- Store-caused = cancelled by the branch with reason *item missing*, *branch overloaded* or *other*. **Not** counted: *customer unreachable* after 2 logged calls, the customer asked to cancel, or the channel cancelled.
- **Given** the branch tries to cancel without a logged call, **then** the system refuses (rule R6).

## Epic 12 – Barcode check and channel choice

### US-37 – Check every scanned barcode · M
*As a* branch employee *I want* the system to check every scanned barcode against the order *so that* no wrong product is packed.
- It works with a handheld scanner (the barcode arrives like typed text) and with the phone camera in the browser.
- **Given** the barcode belongs to a line of the order, **then** that line gets +1 and a short confirmation sound.
- **Given** the line is already complete, **then** the warning "already complete" appears and nothing is counted.
- **Given** the product exists but is **not in this order** (e.g. Semi-Skimmed instead of Whole Milk), **then** an error sound and the red message "This product is not in the order" appear and nothing is counted.
- **Given** a replacement product is scanned, **then** it only counts if the customer approved this replacement (US-31).
- **Given** the barcode is unknown, **then** "Unknown barcode" appears and nothing is counted.
- **Given** a product sold by weight is scanned, **then** its line is selected and the weight must be entered (US-32).
- **Given** a barcode is damaged, **then** I can count with **+** by hand; every manual count is saved as *manual*.
- Every scan and every manual count is saved (who, when, result).

### US-38 – Picking quality report · S
*As a* seller or admin *I want* to see manual counts and wrong scans *so that* I find training needs and damaged barcodes.
- **Given** a date range, **then** I see per branch and per employee: scans, manual counts (number and share in %) and wrong scans (not in the order / unknown).
- The list is sorted by the most manual counts. Clicking a row shows the products counted by hand most often – a hint for damaged barcodes.

### US-39 – Choose the channel when entering the panel · M
*As a* branch employee or seller *I want* to choose HTGO Market or GK Market when I enter the panel *so that* I work in one channel at a time and always know where an order comes from.
- **Given** I logged in, **then** I see two cards side by side: **HTGO Market** in blue and **GK Market** in brown, each with its logo and its number of pending orders.
- **Given** I choose a channel, **then** the panel uses that channel's colors and logo, and orders, tabs and dashboard show only that channel.
- **Given** I want the other channel, **then** "Switch channel" in the top bar brings me back to the selection; the last choice is remembered.
- **Given** a new order arrives in the channel I am not viewing, **then** a notification with that channel's name and color appears, so it is not missed (G1).
- Reports (S-12 – S-14, S-18) can still compare both channels side by side.

## Epic 13 – Findings from the clickable prototype
These stories come from testing the prototype (v1.0.1). Background, examples and reasons: [06 – Prototype decisions](06-prototype-decisions.md).

### US-40 – Open or close a product for sale per channel · M
*As a* branch employee *I want* to open or close a product for sale in HTGO Market and GK Market separately *so that* customers only see what we can really deliver.
- **Given** a product, **then** I see one switch per channel; closing it hides the product from that channel's customers.
- **Given** the stock is 0, **then** the product is closed in both channels automatically and the switch cannot be turned on.

### US-41 – Get a new-order alert with sound · M
*As a* branch employee *I want* a sound and a banner when an order arrives *so that* I notice it even when I am not looking at the screen.
- **Given** a new order, **then** a 3-second banner shows *"New order · channel"*, the order number and the number of waiting orders, in the channel's colours and logo.
- **Given** HTGO or GK, **then** each channel has its own sound. In the mobile app this is a push notification, also on a locked phone.

### US-42 – See how long orders wait · M
*As a* branch employee *I want* waiting orders labelled by waiting time *so that* I accept the oldest first.
- **Given** the *Pending* tab, **then** each order shows the minutes and a label next to its time: *New* (< 2 min), *Waiting* (2 – 5), *Late* (6 – 11), *Very late* (≥ 12).

### US-43 – WhatsApp reminder for waiting orders · S
*As a* branch manager *I want* staff on shift to get a WhatsApp reminder *so that* no order waits too long.
- **Given** orders wait 2 minutes or more, **then** every 2 minutes a message is sent; from 6 minutes it says *late*, from 12 minutes *very late*.
- **Given** an employee did not agree or is not on shift, **then** no message is sent to them (GDPR, see 06 §8).

### US-44 – Protect the final amount · M
*As a* seller *I want* wrong weights and totals to be blocked *so that* a customer is never charged too much.
- **Given** a weighed line, **when** the weight is outside ±10 % of the ordered weight, **then** the field turns red and *Ready* is blocked.
- **Given** the products would cost more than +15 % of the order, **then** *Ready* is blocked – also with the customer's approval.

### US-45 – Remove missing products with the customer's approval · M
*As a* branch employee *I want* to finish an order without missing products after talking to the customer *so that* the customer still gets the rest.
- **Given** a product is missing or the total drops by more than 15 %, **then** the customer must be called first (at least 1 logged call); only then can the employee tick *"customer informed and agreed"*, and *Ready* is possible; the time is stored with the order.

### US-46 – Split orders over the courier capacity · M
*As a* branch employee *I want* orders over 20 litres to be split automatically *so that* every courier can carry their part.
- **Given** an order above 20 L, **then** it is split into as few parts as possible, each part ≤ 20 L, litres shared evenly, each product in only one part.
- **Given** a split order, **then** every part keeps the order number with *"(automatically split order · part n)"*, waits as its own row and is picked separately; reports count it as one order.

### US-47 – Return only some products · M
*As a* branch employee *I want* to see exactly which products and how many are returned *so that* I check and refund the right amount.
- **Given** a return, **then** every returned product is listed with photo and quantity; the refund is quantity × price on the order line.

### US-48 – Receive returned products with a QR code · M
*As a* branch employee *I want* to confirm the handover with a QR code *so that* courier and store agree on what was brought back.
- **Given** an approved return, **when** the courier brings it, **then** I tick every product and create a one-time QR code.
- **Given** the courier scans the code (courier app; for now any phone), **then** the return becomes *Return accepted*.

### US-49 – Refund after the return is accepted · M
*As a* customer *I want* my money back as soon as the store has my products *so that* I do not have to wait.
- **Given** a return is accepted, **then** the refund is sent to the customer's card through the channel and shown with time on the return.

### US-50 – See and filter reasons · S
*As a* branch employee *I want* the reason of every return and cancellation in the list *so that* I understand problems quickly.
- **Given** the returns list, **then** each return shows its reason and the customer's note; tapping the reason opens the order, reason chips filter the list.
- **Given** the *Cancelled* tab, **then** each order shows its reason and whether it counts against the store score.

### US-51 – See customer ratings · S
*As a* seller *I want* to see the channel ratings *so that* I know how customers judge service, taste and delivery.
- **Given** the home page, **then** each channel shows its average and number of reviews; tapping it opens the reviews.
- **Given** the reviews page, **then** I see the average, the star distribution, the three criteria and the latest reviews with filled star boxes.

### US-52 – Sales report per day, week, month, year · S
*As a* seller *I want* to switch the sales report between periods *so that* I see short- and long-term trends.
- **Given** a period tab, **then** the boxes, the chart, the detail table and the best sellers show that period; a date chip names it.
- **Given** the daily view, **then** the boxes *Orders, Revenue, Cancellations, Returns* open the list behind the number; today's numbers equal the home page.

### US-53 – Day and night mode · C
*As a* user *I want* to switch between a light and a dark look *so that* the screen is comfortable on night shifts.
- **Given** the sun / moon button, **then** all pages switch; status colours keep their meaning, and the choice is remembered on the device.
