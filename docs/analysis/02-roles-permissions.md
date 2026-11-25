# 02 – Roles and Permissions

🇬🇧 **English** · 🇩🇪 [Deutsch](02-roles-permissions.de.md) · 🇹🇷 [Türkçe](02-roles-permissions.tr.md)

## 1. Roles
| Role | Who | Sees data of |
|---|---|---|
| **Admin** | Operator of VetisPilot | All sellers, all branches |
| **Seller** | Owner of a business | Own branches only |
| **Branch employee** | Staff in one branch | Own branch only |
| **Channel** (system, not a person) | HTGO Market, GK Market | Only the interface for orders and status updates |

A user has exactly one role. A branch employee belongs to exactly one branch.

## 2. Permission matrix
**C** = create · **R** = read · **U** = update · **D** = delete · **–** = no access
*own* = only data of the user's own branches (Seller) or own branch (Branch employee)

| Area | Admin | Seller | Branch employee | Channel |
|---|---|---|---|---|
| Sellers | CRUD | R (own profile) | – | – |
| Branches | CRUD | R, U (own) | R (own) | – |
| Users | CRUD | CRUD branch employees (own branches) | R, U (own profile) | – |
| Product catalog | CRUD | CRUD (own) | R (own) | – |
| Stock / in stock flag | R, U | R, U (own) | R, U (own branch) | – |
| Orders – receive | – | – | – | C |
| Orders – view | R | R (own) | R (own branch) | – |
| Orders – status *Picking / Ready* | U | U (own) | U (own branch) | – |
| Orders – picking: replace product, enter weight, number of bags | U | U (own) | U (own branch) | – |
| Orders – status *On the way / Delivered* | – | – | – | U |
| Orders – cancel (until *Ready*, reason required – R6) | U | U (own) | U (own branch) | U |
| Returns – request | – | – | – | C |
| Returns – approve / reject | U | U (own) | U (own branch) | – |
| Dashboard | R (all) | R (own) | R (own branch) | – |
| Reports | R (all) | R (own) | – | – |
| Audit log | R | – | – | – |

## 3. Rules
- **R1 – Least privilege:** every user gets only the access the job needs.
- **R2 – Data separation:** a seller never sees another seller's data, even by changing an ID in the URL. The server checks ownership on every request.
- **R3 – No deletion of orders:** orders are never deleted, only cancelled. This keeps reports and the audit trail correct.
- **R4 – Audit:** changes to products, prices, stock, order status, returns and users are written to the audit log (who, when, what, old value, new value).
- **R5 – Channel access:** channels do not log in with a password. Each request is signed with a secret key (HMAC); unsigned or wrongly signed requests are rejected.
- **R6 – No cancellation without a reason and a check:** the branch must choose a reason before it can cancel. *Customer unreachable* and *item out of stock* are allowed only after 2 logged calls (2 minutes apart); *branch overloaded* is allowed at once but counts against the store score; *customer cancelled* and *wrong address* come only from the channel. The database checks this itself (trigger), so the rule holds even if the code has a bug. Details: [06 §4](06-prototype-decisions.md).
