# VetisPilot

🇬🇧 [English](README.md) · 🇩🇪 **Deutsch** · 🇹🇷 [Türkçe](README.tr.md)

**Multi-Channel Orders** – ein Händler-Panel für Bestellungen aus zwei Lieferkanälen.

VetisPilot ist ein Multi-Channel-Bestellmanagementsystem. Ein Lebensmittelgeschäft oder Restaurant erhält Bestellungen über zwei (fiktive) Lieferplattformen, **HTGO Market** und **GK Market**, und verwaltet sie alle in einem einzigen Panel: Bestellungen, Produkte, Filialen, Retouren und Verkaufsberichte.

> 🚧 In Arbeit – Phase 1 (Analyse) abgeschlossen, klickbarer Prototyp **v1.0.1** getestet, aktuell in **Phase 2: Datenbankdesign**.

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
- [06 – Entscheidungen aus dem klickbaren Prototyp](docs/analysis/06-prototype-decisions.de.md)
- [Datenbank – Datenmodell (ER)](docs/database/01-er-model.de.md)
- [Klickbarer Prototyp des Panels](prototype/panel.html) (v1.0.1) – Anmeldung, Bestellungen mit Hinweisen und geteilten Bestellungen, Kommissionierung mit Betragsschutz, Produkte und Verkaufsstatus, Retouren mit QR-Übergabe, Berichte, Bewertungen, Tag-/Nachtmodus (Beispieldaten; Datei im Browser öffnen)

## Farbsprache des Panels
Farben bedeuten immer dasselbe, damit Filialmitarbeitende eine Liste auf einen Blick verstehen – im Tag- und im Nachtmodus.

| Farbe | Bedeutung | Beispiel |
|---|---|---|
| 🔵 Blau / 🟤 Braun | Der Kanal: **HTGO Market** / **GK Market** (Kopfzeilen, Hinweise, Berichte) | Banner für neue Bestellungen in der Kanalfarbe |
| 🟢 Grün | Gut / kann jetzt weitergehen | Aktive Bestellungen, Produkt im Verkauf, *Kurier ist in der Filiale, um die Bestellung abzuholen*, Retoure angenommen |
| 🟣 Lila | Kurier ist unterwegs | *Kurier auf dem Weg zur Filiale*, *Kurier unterwegs zur Lieferadresse* |
| 🟠 Orange | Bald Aufmerksamkeit nötig | Bestellung *verspätet* (ab 6 Min.), *Kurier bringt eine Retoure zur Filiale* |
| 🔴 Rot | Jetzt handeln / gesperrt | Bestellung *stark verspätet* (ab 12 Min.), offene Retouren, Betragsgrenze überschritten, Stornierung |
| ⚪ Grau | Noch nicht möglich | *Stornieren* bevor ein Grund gewählt ist, Schalter aus |

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
