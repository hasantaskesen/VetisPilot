# 05 – Screen List

🇬🇧 **English** · 🇩🇪 [Deutsch](05-screens.de.md) · 🇹🇷 [Türkçe](05-screens.tr.md)

This document lists every screen of the seller panel, who can open it and which user stories it covers ([03 – User Stories](03-user-stories.md)). The wireframes are rough sketches of the layout, not the final design.

## 1. Layout on every screen
- **Top bar:** VetisPilot logo · current channel (HTGO Market or GK Market) with **Switch channel** · branch selector (seller and admin) · 🔔 notifications · ☀️ / 🌙 day and night mode (US-53) · language switch 🇬🇧 EN · 🇩🇪 DE · 🇹🇷 TR · user menu (profile, log out)
- **New-order alert:** sound per channel and a 3-second banner in the channel's colours on every screen (US-41)
- **Side menu:** only the entries the user's role may open (see section 3)
- **Live updates:** order counts, dashboard numbers and notifications update without reloading (US-06, US-20)
- **Channel colors:** after choosing a channel (S-17) the panel uses its colors – HTGO Market blue, GK Market brown

## 2. Screens
| ID | Screen | Purpose | Admin | Seller | Branch employee | Stories | Prio |
|---|---|---|---|---|---|---|---|
| S-01 | Login | Log in, lock after 5 failed attempts | ✔ | ✔ | ✔ | US-01, US-02 | M |
| S-02 | Set new password | Forced on first login with a temporary password | ✔ | ✔ | ✔ | US-22, US-23 | M |
| S-03 | Dashboard | Today's revenue, active orders, pending returns, stock alerts, store score | ✔ all | ✔ own | ✔ own branch | US-20, US-36 | M |
| S-04 | Orders | Order list with channel switch and status tabs | ✔ read | ✔ own | ✔ own branch | US-06, US-07 | M |
| S-05 | Order detail | Items, customer note, status history, status buttons, cancel, replace product, enter weight, number of bags, final amount, picked quantity (− / +), call log | ✔ read | ✔ | ✔ | US-08 – US-12, US-31 – US-35 | M |
| S-06 | Products | Product list with search, filters and in-stock switch | ✔ | ✔ | ✔ read + stock | US-16, US-17 | M |
| S-07 | Product form | Create / edit / archive a product, names in EN / DE / TR | ✔ | ✔ | – | US-13 – US-15 | M |
| S-08 | Returns | Return requests with tabs *Requested / Approved / Rejected*, approve or reject | ✔ read | ✔ | ✔ | US-18, US-19 | M |
| S-09 | Branches | Branch details, opening hours, open / closed, bag fee | ✔ | ✔ own | – | US-21, US-33 | M |
| S-10 | Users | Create, edit, deactivate branch employees | ✔ | ✔ own branches | – | US-22 | M |
| S-11 | My profile | Name, language, password | ✔ | ✔ | ✔ | US-03, US-23 | M |
| S-12 | Report: channel sales | Tabs day / week / month / year: orders, revenue, average order value, cancellations, returns, chart and detail table; in the daily view the boxes open the list behind the number | ✔ | ✔ | – | US-26, US-28, US-52 | S |
| S-13 | Report: products | Best and worst sellers, share per channel, out-of-stock count | ✔ | ✔ | – | US-27, US-28 | S |
| S-14 | Report: cancellation reasons | Cancellations per reason as a chart, per channel and branch | ✔ | ✔ | – | US-30, US-28 | S |
| S-15 | Admin: sellers | Create, edit, deactivate sellers and their branches | ✔ | – | – | US-24 | S |
| S-16 | Admin: audit log | Who changed what and when; read only | ✔ | – | – | US-25 | M |
| S-17 | Channel selection | Choose HTGO Market or GK Market after login; shows pending orders per channel | ✔ | ✔ | ✔ | US-39 | M |
| S-18 | Report: picking quality | Scans, manual counts and wrong scans per branch and employee | ✔ | ✔ | – | US-38 | S |
| S-19 | Sale status | One switch per product and channel (HTGO / GK); stock 0 closes automatically | ✔ | ✔ | ✔ | US-40 | M |
| S-20 | Notifications & WhatsApp | Reminder rules, recipients with WhatsApp consent, sent reminders, demo buttons | ✔ | ✔ | ✔ read | US-41 – US-43 | S |
| S-21 | Return handover | Tick returned products, create the QR code, see accepted returns and refunds | ✔ read | ✔ | ✔ | US-48, US-49 | M |
| S-22 | Courier confirmation | Opened by scanning the QR code, no login; courier confirms the handover | – | – | – (courier) | US-48 | M |
| S-23 | Reviews | Average, star distribution, service / taste / delivery, latest reviews | ✔ | ✔ | ✔ | US-51 | S |
| S-24 | Discounts | Per channel: new discount (products, percent / amount, duration), affected products and example price, active discounts with countdown and stop | ✔ | ✔ | ✔ | US-54 | S |

The channel simulator (US-29) has no screen in the panel; it runs as a separate service and is configured by a settings file.

## 3. Side menu per role
| Menu entry | Admin | Seller | Branch employee |
|---|---|---|---|
| Dashboard | ✔ | ✔ | ✔ |
| Orders | ✔ | ✔ | ✔ |
| Products | ✔ | ✔ | ✔ |
| Returns | ✔ | ✔ | ✔ |
| Branches | ✔ | ✔ | – |
| Users | ✔ | ✔ | – |
| Reports | ✔ | ✔ | – |
| Admin (sellers, audit log) | ✔ | – | – |

Hiding a menu entry is only for convenience; the server still checks every request (rule R2).

## 4. Wireframes

### S-04 – Orders
The list **and** every tab count show only the channel chosen at entry (S-17). *Pending* is open by default.
```
┌──────────────────────────────────────────────────────────────────────────
│ Orders                                         🔍 Search order number
│
│ ◉ HTGO Market                                 [ Switch channel ]
│
│ [ Pending 5 ]  [ Picking 3 ]  [ Ready 2 ]  [ All 104 ]  [ Cancelled 4 ]
│ ────────────────────────────────────────────────────────────────────────
│ Channel  Order no.   Time   Items  Total    Waiting
│ HTGO     HT-10482    14:02    6    €23.40   4 min  ◀ red, over 3 min
│ GK       GK-55120    14:05    2    €4.18    1 min
│ HTGO     HT-10483    14:06   11    €41.90   0 min  NEW
│                                          [ Start picking ]  per row
└──────────────────────────────────────────────────────────────────────────
```
| Tab | Contains | Extra columns |
|---|---|---|
| Pending | Status *Pending* | Waiting time, red after 3 minutes |
| Picking | Status *Picking* | Employee who is picking |
| Ready | Status *Ready* | Time since ready |
| All | Every order, incl. *On the way* and *Delivered* | Status, filters for status / branch / date |
| Cancelled | Status *Cancelled* | Cancellation reason, cancelled by (branch / channel) |

### S-05 – Order detail
```
┌──────────────────────────────────────────────────────────────────────────
│ ← Orders     HT-10482 · HTGO Market · Branch Mitte        Status: Pending
│
│ Items                                   Qty   Price    Sum
│ Milvara Whole Milk 3.5% · 1 L            2    €1.29    €2.58
│ Crumbwell Butter Croissants · 4 pcs      1    €2.49    €2.49  ⚠
│ ...
│                                               Total:   €23.40
│ Customer note: "Please ring twice"
│
│ Status history
│ 14:02  Pending   received from HTGO Market
│
│ [ Start picking ]                          [ Cancel order … ]
└──────────────────────────────────────────────────────────────────────────
```
⚠ = out of stock in this branch. Buttons change with the status: *Pending* → "Start picking", *Picking* → "Mark as ready". From *Ready* on, there are no buttons; the channel sets the next statuses.
While picking, every line has the actions **Replace…** and **Remove**; products sold by weight also get a **weight field** (kg). "Mark as ready" opens a small dialog for the **number of bags** and shows ordered amount, final amount and the difference before it is sent to the channel (US-31 – US-33).
Every line shows **picked / ordered** with **−** / **+** (a barcode scan adds 1). A **Call customer** button logs each attempt ("attempt 1 of 2") and its result. **Cancel order …** stays disabled until the call rule is met (R6).
Each scan is checked against the order (US-37): a product that is not in the order gives an error sound and a red message and is not counted; **+** without a scan is saved as a *manual* count.

### S-03 – Dashboard
```
┌──────────────────────────────────────────────────────────────────────────
│ Dashboard · today
│ ┌──────────────┐ ┌──────────────┐ ┌──────────────┐ ┌──────────────┐
│ │ Revenue      │ │ HTGO         │ │ GK           │ │ Pending      │
│ │ €1,284.50    │ │ €812.10      │ │ €472.40      │ │ returns  3   │
│ └──────────────┘ └──────────────┘ └──────────────┘ └──────────────┘
│ Active orders:  Pending 5 · Picking 3 · Ready 2
│ Average preparation time today: 7 min
│ Store score (30 days): 1.4 % store-caused cancellations · target ≤ 2 %
│ Stock alerts:   6 out of stock · 8 low stock          [ show products ]
└──────────────────────────────────────────────────────────────────────────
```
Each tile is clickable and opens the matching filtered list.

### S-17 – Channel selection
Shown right after login (US-39). Each card uses the channel's own colors; the choice is remembered and can be changed with **Switch channel** in the top bar.
```
┌──────────────────────────────────────────────────────────────────────────
│ Choose a channel                                        🇬🇧 EN · 🇩🇪 DE · 🇹🇷 TR
│
│ ┌───────────────────────────────┐   ┌───────────────────────────────┐
│ │  [bag]  HTGO Market           │   │  [basket]  GK Market          │
│ │         blue                  │   │            brown              │
│ │  Pending orders: 5            │   │  Pending orders: 3            │
│ │            [ Enter ]          │   │            [ Enter ]          │
│ └───────────────────────────────┘   └───────────────────────────────┘
└──────────────────────────────────────────────────────────────────────────
```
