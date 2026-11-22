# 02 – Rollen und Berechtigungen

🇬🇧 [English](02-roles-permissions.md) · 🇩🇪 **Deutsch** · 🇹🇷 [Türkçe](02-roles-permissions.tr.md)

## 1. Rollen
| Rolle | Wer | Sieht Daten von |
|---|---|---|
| **Admin** | Betreiber von VetisPilot | Allen Händlern, allen Filialen |
| **Händler** | Inhaber eines Geschäfts | Nur den eigenen Filialen |
| **Filialmitarbeiter** | Personal einer Filiale | Nur der eigenen Filiale |
| **Kanal** (System, keine Person) | HTGO Market, GK Market | Nur der Schnittstelle für Bestellungen und Statusmeldungen |

Ein Benutzer hat genau eine Rolle. Ein Filialmitarbeiter gehört zu genau einer Filiale.

## 2. Berechtigungsmatrix
**C** = anlegen (create) · **R** = lesen (read) · **U** = ändern (update) · **D** = löschen (delete) · **–** = kein Zugriff
*eigene* = nur Daten der eigenen Filialen (Händler) bzw. der eigenen Filiale (Filialmitarbeiter)

| Bereich | Admin | Händler | Filialmitarbeiter | Kanal |
|---|---|---|---|---|
| Händler | CRUD | R (eigenes Profil) | – | – |
| Filialen | CRUD | R, U (eigene) | R (eigene) | – |
| Benutzer | CRUD | CRUD Filialmitarbeiter (eigene Filialen) | R, U (eigenes Profil) | – |
| Produktkatalog | CRUD | CRUD (eigene) | R (eigene) | – |
| Bestand / Vorrätig-Kennzeichen | R, U | R, U (eigene) | R, U (eigene Filiale) | – |
| Bestellungen – empfangen | – | – | – | C |
| Bestellungen – ansehen | R | R (eigene) | R (eigene Filiale) | – |
| Bestellungen – Status *In Kommissionierung / Bereit* | U | U (eigene) | U (eigene Filiale) | – |
| Bestellungen – Kommissionierung: Ersatz, Gewicht, Tütenanzahl | U | U (eigene) | U (eigene Filiale) | – |
| Bestellungen – Status *Unterwegs / Zugestellt* | – | – | – | U |
| Bestellungen – stornieren (bis *Bereit*, nur nach Kontakt mit dem Kunden – R6) | U | U (eigene) | U (eigene Filiale) | U |
| Retouren – beantragen | – | – | – | C |
| Retouren – genehmigen / ablehnen | U | U (eigene) | U (eigene Filiale) | – |
| Dashboard | R (alle) | R (eigene) | R (eigene Filiale) | – |
| Berichte | R (alle) | R (eigene) | – | – |
| Audit-Log | R | – | – | – |

## 3. Regeln
- **R1 – Minimalprinzip (Least Privilege):** Jeder Benutzer erhält nur die Rechte, die er für seine Aufgabe braucht.
- **R2 – Datentrennung:** Ein Händler sieht niemals Daten eines anderen Händlers, auch nicht durch Ändern einer ID in der URL. Der Server prüft die Zugehörigkeit bei jeder Anfrage.
- **R3 – Bestellungen werden nie gelöscht:** Bestellungen werden nur storniert, nicht gelöscht. So bleiben Berichte und Audit-Trail korrekt.
- **R4 – Audit:** Änderungen an Produkten, Preisen, Bestand, Bestellstatus, Retouren und Benutzern werden im Audit-Log gespeichert (wer, wann, was, alter Wert, neuer Wert).
- **R5 – Kanalzugriff:** Kanäle melden sich nicht mit Passwort an. Jede Anfrage wird mit einem geheimen Schlüssel signiert (HMAC); unsignierte oder falsch signierte Anfragen werden abgelehnt.
- **R6 – Keine Stornierung ohne den Kunden:** Die Filiale kann nur nach einem protokollierten Anruf mit Zustimmung des Kunden oder nach 2 unbeantworteten Anrufen stornieren. Die Datenbank prüft das selbst (Trigger), die Regel gilt also auch bei einem Fehler im Code.
