# API 01 – Anmeldung

🇬🇧 [English](01-login.md) · 🇩🇪 **Deutsch** · 🇹🇷 [Türkçe](01-login.tr.md)

Der erste Teil des Backends, der mit der echten Datenbank arbeitet. Er deckt [US-01 (Anmelden), US-02 (Schutz vor Passwort-Raten) und US-22 (deaktivierte Konten)](../analysis/03-user-stories.de.md) ab. Code: [`backend/src/main/java/com/vetispilot/auth`](../../backend/src/main/java/com/vetispilot/auth).

## 1. Endpunkte
| Methode | Pfad | Anmeldung nötig | Was passiert |
|---|---|---|---|
| POST | `/api/v1/auth/login` | nein | E-Mail + Passwort rein → Token + Benutzer raus |
| GET | `/api/v1/auth/me` | ja | liefert den angemeldeten Benutzer |
| GET | `/api/v1/system/info` | nein | Name, Version, Sprachen, Kanäle |
| GET | `/actuator/health` | nein | `{"status":"UP"}`, wenn das Backend läuft |

Jeder andere Pfad antwortet ohne gültiges Token mit **401**. Das Token steht im Header `Authorization: Bearer <token>`.

**Beispiel**
```http
POST /api/v1/auth/login
{"email": "nadia.brenner@lindenkorb.example", "password": "Nadia#2026"}

200 OK
{"token": "eyJ…", "expiresAt": "2026-11-28T06:08:07Z",
 "user": {"id": 2, "fullName": "Nadia Brenner", "role": "BRANCH_EMPLOYEE", "branchId": 2, "language": "tr", "mustChangePassword": false}}
```

## 2. Regeln
| Situation | Antwort | Audit-Log |
|---|---|---|
| richtige E-Mail und richtiges Passwort | 200 + Token, **8 Stunden** gültig | `LOGIN_SUCCESS` |
| falsches Passwort **oder** unbekannte E-Mail | 401 *„Email or password is incorrect.“* – derselbe Text, damit niemand erfährt, welche Konten es gibt | `LOGIN_FAILED` |
| 5. falsches Passwort in Folge | 429 *„Too many failed attempts …“*, Konto **15 Minuten gesperrt** | `ACCOUNT_LOCKED` |
| Anmeldung während der Sperre (auch mit richtigem Passwort) | 429 | `LOGIN_WHILE_LOCKED` |
| deaktiviertes Konto | 401, derselbe Text wie bei falschem Passwort | `LOGIN_FAILED` |
| leeres Feld | 400 *„Please fill in all required fields.“* | – |

- Eine erfolgreiche Anmeldung setzt den Zähler der falschen Passwörter zurück.
- Bei unbekannter E-Mail prüft der Server trotzdem ein Dummy-Passwort, damit die Antwort genauso lange dauert wie bei einem echten Konto.
- Vereinfachung: Der Zähler zählt falsche Passwörter **in Folge**, nicht nur innerhalb von 15 Minuten (US-02). Ein Zeitfenster kann später ergänzt werden.

## 3. Sicherheitsentscheidungen
| Entscheidung | Warum |
|---|---|
| Passwörter nur als **BCrypt-Hash** | selbst mit einer Kopie der Datenbank kann niemand die Passwörter lesen |
| **JWT** (signiertes Token) statt Server-Session | das Backend bleibt zustandslos; ohne Schlüssel lässt sich das Token nicht ändern |
| Der Signaturschlüssel `JWT_SECRET` steht in `.env` | Geheimnisse kommen nie zu GitHub; mit einem Schlüssel unter 32 Zeichen startet das Backend nicht |
| Anmeldeversuche landen in `audit_log` | die Tabelle nimmt nur INSERT an (Trigger aus V1), Versuche lassen sich also nicht verstecken |

## 4. Demo-Konten
Die Migration [`db/demo/V3__demo_data.sql`](../../backend/src/main/resources/db/demo/V3__demo_data.sql) legt die Filiale *Lindenkorb · Berlin-Spandau*, die Kanäle HTGO Market und GK Market und die vier Mitarbeitenden des Prototyps an (Nadia, Lukas, Mira, Tobias – Passwörter wie auf der Anmeldeseite des Prototyps). Sie liegt in `db/demo`, nicht in `db/migration`: Eine echte Installation setzt `FLYWAY_LOCATIONS=classpath:db/migration` und startet ohne Demodaten.

## 5. Tests
- `AuthServiceTest` – 8 Tests der Regeln oben, ohne Datenbank (feste Uhr).
- `AuthControllerTest` – 7 Tests der Web-Schicht: Statuscodes, JSON, `/me` mit und ohne Token.
- Von Hand mit der echten PostgreSQL-Datenbank ausprobiert: richtiges Passwort, unbekannte E-Mail, `/me`, 5 falsche Passwörter → Sperre, Zeilen im Audit-Log.
