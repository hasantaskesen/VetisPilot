# API 01 – Login

🇬🇧 **English** · 🇩🇪 [Deutsch](01-login.de.md) · 🇹🇷 [Türkçe](01-login.tr.md)

The first part of the backend that works with the real database. It covers [US-01 (log in), US-02 (brute-force protection) and US-22 (deactivated accounts)](../analysis/03-user-stories.md). Code: [`backend/src/main/java/com/vetispilot/auth`](../../backend/src/main/java/com/vetispilot/auth).

## 1. Endpoints
| Method | Path | Login needed | What it does |
|---|---|---|---|
| POST | `/api/v1/auth/login` | no | email + password in → token + user out |
| GET | `/api/v1/auth/me` | yes | returns the logged-in user |
| GET | `/api/v1/system/info` | no | name, version, languages, channels |
| GET | `/actuator/health` | no | `{"status":"UP"}` when the backend runs |

Every other path answers **401** without a valid token. The token is sent in the header `Authorization: Bearer <token>`.

**Example**
```http
POST /api/v1/auth/login
{"email": "nadia.brenner@lindenkorb.example", "password": "Nadia#2026"}

200 OK
{"token": "eyJ…", "expiresAt": "2026-11-28T06:08:07Z",
 "user": {"id": 2, "fullName": "Nadia Brenner", "role": "BRANCH_EMPLOYEE", "branchId": 2, "language": "tr", "mustChangePassword": false}}
```

## 2. Rules
| Situation | Answer | Audit log |
|---|---|---|
| right email and password | 200 + token, valid for **8 hours** | `LOGIN_SUCCESS` |
| wrong password **or** unknown email | 401 *"Email or password is incorrect."* – the same text, so nobody learns which accounts exist | `LOGIN_FAILED` |
| 5th wrong password in a row | 429 *"Too many failed attempts …"*, account **locked for 15 minutes** | `ACCOUNT_LOCKED` |
| login while locked (even with the right password) | 429 | `LOGIN_WHILE_LOCKED` |
| deactivated account | 401, same text as a wrong password | `LOGIN_FAILED` |
| empty field | 400 *"Please fill in all required fields."* | – |

- A successful login resets the counter of wrong passwords.
- For an unknown email the server still checks a dummy password, so the answer takes as long as for a real account.
- Simplification: the counter counts wrong passwords **in a row**, not only those within 15 minutes (US-02). A time window can be added later.

## 3. Security decisions
| Decision | Why |
|---|---|
| Passwords only as **BCrypt hashes** | even with a copy of the database nobody can read the passwords |
| **JWT** (signed token) instead of a server session | the backend stays stateless; the token cannot be changed without the key |
| The signing key `JWT_SECRET` lives in `.env` | secrets never go to GitHub; the backend refuses to start with a key shorter than 32 characters |
| Login attempts are written to `audit_log` | the table only accepts INSERT (V1 trigger), so attempts cannot be hidden |

## 4. Demo accounts
The migration [`db/demo/V3__demo_data.sql`](../../backend/src/main/resources/db/demo/V3__demo_data.sql) adds the branch *Lindenkorb · Berlin-Spandau*, the channels HTGO Market and GK Market and the four employees of the prototype (Nadia, Lukas, Mira, Tobias – passwords as on the prototype's login page). It lives in `db/demo`, not in `db/migration`: a real installation sets `FLYWAY_LOCATIONS=classpath:db/migration` and starts without demo data.

## 5. Tests
- `AuthServiceTest` – 8 tests of the rules above without a database (fixed clock).
- `AuthControllerTest` – 7 tests of the web layer: status codes, JSON, `/me` with and without a token.
- Tried by hand on the real PostgreSQL database: right password, unknown email, `/me`, 5 wrong passwords → lock, audit log rows.
