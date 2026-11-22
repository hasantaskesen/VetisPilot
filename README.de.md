# VetisPilot

🇬🇧 [English](README.md) · 🇩🇪 **Deutsch** · 🇹🇷 [Türkçe](README.tr.md)

**Multi-Channel Orders** – ein Händler-Panel für Bestellungen aus zwei Lieferkanälen.

VetisPilot ist ein Multi-Channel-Bestellmanagementsystem. Ein Lebensmittelgeschäft oder Restaurant erhält Bestellungen über zwei (fiktive) Lieferplattformen, **HTGO Market** und **GK Market**, und verwaltet sie alle in einem einzigen Panel: Bestellungen, Produkte, Filialen, Retouren und Verkaufsberichte.

> 🚧 In Arbeit – Phase 1 (Analyse) abgeschlossen, aktuell in **Phase 2: Datenbankdesign**.

## Geplante Funktionen
- **Dashboard:** Tagesumsatz, aktive Bestellungen, offene Retouren
- **Bestellungen:** nach Kanal gefiltert (HTGO / GK), Statusablauf: Offen → In Kommissionierung → Bereit → Unterwegs → Zugestellt (inkl. Stornierung / Retoure)
- **Produkte:** anlegen, bearbeiten, löschen, Preis, Foto, vorrätig / nicht vorrätig
- **Filialen & Benutzerprofil**
- **Berichte:** HTGO-Umsatz, GK-Umsatz, Produktbericht
- **Admin-Panel:** Händler, Filialen und Benutzer verwalten
- **Rollen:** Admin, Händler, Filialmitarbeiter
- **Sprachen:** Benutzeroberfläche auf Englisch, Deutsch und Türkisch

## Dokumentation
- [01 – Projektumfang](docs/analysis/01-scope.de.md)
- [02 – Rollen und Berechtigungen](docs/analysis/02-roles-permissions.de.md)
- [03 – User Stories](docs/analysis/03-user-stories.de.md)
- [04 – Bestellprozess](docs/analysis/04-order-process.de.md)
- [05 – Bildschirmliste](docs/analysis/05-screens.de.md)
- [Datenbank – Datenmodell (ER)](docs/database/01-er-model.de.md)

## Technologie
| Schicht | Technologie |
|---|---|
| Backend | Java, Spring Boot (REST API) |
| Datenbank | PostgreSQL (Docker) |
| Frontend | React |
| Kanal-Simulator | Kleiner separater Dienst, der realistische Bestellungen sendet |
| Infrastruktur | Docker Compose |

## Roadmap
1. Analyse – Umfang, Rollen, User Stories, Bestellprozess, Bildschirmliste
2. Datenbankdesign – ER-Diagramm, PostgreSQL in Docker
3. Backend – Authentifizierung, Produkte, Filialen, Bestellungen, Retouren
4. Kanal-Simulator – Bestellgenerator für HTGO und GK
5. Frontend – Login, Dashboard, Bestellungen, Produkte, Berichte
6. Berichte und Admin-Panel
7. Abschluss – Docker Compose, Screenshots, Architektur, Sicherheit

## Sicherheit
Geplant: Passwort-Hashing mit BCrypt, Login per JWT, rollenbasierte Zugriffskontrolle, HMAC-signierte Kanalbestellungen, Eingabevalidierung, Begrenzung von Login-Versuchen, Geheimnisse in `.env`, Audit-Log. Details folgen.

---
*HTGO Market und GK Market sind fiktive Marken, die für dieses Projekt erstellt wurden.*
