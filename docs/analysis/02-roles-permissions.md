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
| Orders – cancel (until *Ready*, only after contacting the customer – R6) | U | U (own) | U (own branch) | U |
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
- **R6 – No cancellation without the customer:** the branch can cancel only after a logged call in which the customer agreed, or after 2 unanswered calls. The database checks this itself (trigger), so the rule holds even if the code has a bug.
