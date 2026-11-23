# VetisPilot

🇬🇧 **English** · 🇩🇪 [Deutsch](README.de.md) · 🇹🇷 [Türkçe](README.tr.md)

**Multi-Channel Orders** – one seller panel for orders coming from two delivery channels.

VetisPilot is a multi-channel order management system. A grocery store or restaurant receives orders from two (fictional) delivery platforms, **HTGO Market** and **GK Market**, and manages all of them from a single panel: orders, products, branches, returns and sales reports.

> 🚧 Work in progress – Phase 1 (analysis) done, currently in **Phase 2: Database design**.

## Planned features
- **Dashboard:** daily revenue, active orders, pending returns
- **Orders:** filtered by channel (HTGO / GK), status flow: Pending → Picking → Ready → On the way → Delivered (incl. cancel / return)
- **Products:** create, edit, delete, price, photo, in stock / out of stock
- **Branches & user profile**
- **Reports:** HTGO sales, GK sales, product report
- **Admin panel:** manage sellers, branches and users
- **Roles:** Admin, Seller, Branch employee
- **Languages:** user interface in English, German and Turkish

## Documentation
- [01 – Scope](docs/analysis/01-scope.md)
- [02 – Roles and Permissions](docs/analysis/02-roles-permissions.md)
- [03 – User Stories](docs/analysis/03-user-stories.md)
- [04 – Order Process](docs/analysis/04-order-process.md)
- [05 – Screen List](docs/analysis/05-screens.md)
- [Database – Data Model (ER)](docs/database/01-er-model.md)
- [Clickable prototype of the panel](prototype/panel.html) – login, orders, picking, products, returns, reports (sample data; open the file in a browser)

## Tech stack
| Layer | Technology |
|---|---|
| Backend | Java, Spring Boot (REST API) |
| Database | PostgreSQL (Docker) |
| Frontend | React |
| Channel simulator | Small separate service that sends realistic orders |
| Infrastructure | Docker Compose |

## Roadmap
1. Analysis – scope, roles, user stories, order process, screen list
2. Database design – ER diagram, PostgreSQL in Docker
3. Backend – auth, products, branches, orders, returns
4. Channel simulator – HTGO and GK order generator
5. Frontend – login, dashboard, orders, products, reports
6. Reports and admin panel
7. Wrap-up – Docker Compose, screenshots, architecture, security

## Security
Planned: BCrypt password hashing, JWT login, role-based access, HMAC-signed channel orders, input validation, login rate limiting, secrets in `.env`, audit log. Details will follow.

---
*HTGO Market and GK Market are fictional brands created for this project.*
