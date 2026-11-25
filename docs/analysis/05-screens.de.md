# 05 – Bildschirmliste

🇬🇧 [English](05-screens.md) · 🇩🇪 **Deutsch** · 🇹🇷 [Türkçe](05-screens.tr.md)

Dieses Dokument listet alle Bildschirme des Händler-Panels auf, wer sie öffnen darf und welche User Stories sie abdecken ([03 – User Stories](03-user-stories.de.md)). Die Wireframes sind grobe Skizzen des Layouts, nicht das endgültige Design.

## 1. Aufbau jedes Bildschirms
- **Obere Leiste:** VetisPilot-Logo · aktueller Kanal (HTGO Market oder GK Market) mit **Kanal wechseln** · Filialauswahl (Händler und Admin) · 🔔 Benachrichtigungen · ☀️ / 🌙 Tag- und Nachtmodus (US-53) · Sprachumschalter 🇬🇧 EN · 🇩🇪 DE · 🇹🇷 TR · Benutzermenü (Profil, Abmelden)
- **Hinweis bei neuer Bestellung:** Ton pro Kanal und ein 3-Sekunden-Banner in den Kanalfarben auf jedem Bildschirm (US-41)
- **Seitenmenü:** nur die Einträge, die die Rolle des Benutzers öffnen darf (siehe Abschnitt 3)
- **Live-Aktualisierung:** Bestellzähler, Dashboard-Zahlen und Benachrichtigungen aktualisieren sich ohne Neuladen (US-06, US-20)
- **Kanalfarben:** nach der Kanalwahl (S-17) nutzt das Panel dessen Farben – HTGO Market blau, GK Market braun

## 2. Bildschirme
| ID | Bildschirm | Zweck | Admin | Händler | Filialmitarbeiter | Stories | Prio |
|---|---|---|---|---|---|---|---|
| S-01 | Anmeldung | Anmelden, Sperre nach 5 Fehlversuchen | ✔ | ✔ | ✔ | US-01, US-02 | M |
| S-02 | Neues Passwort festlegen | Pflicht bei der ersten Anmeldung mit temporärem Passwort | ✔ | ✔ | ✔ | US-22, US-23 | M |
| S-03 | Dashboard | Tagesumsatz, aktive Bestellungen, offene Retouren, Bestandswarnungen, Filialbewertung | ✔ alle | ✔ eigene | ✔ eigene Filiale | US-20, US-36 | M |
| S-04 | Bestellungen | Bestellliste mit Kanalauswahl und Status-Reitern | ✔ lesen | ✔ eigene | ✔ eigene Filiale | US-06, US-07 | M |
| S-05 | Bestelldetails | Artikel, Kundenhinweis, Statusverlauf, Status-Schaltflächen, Stornieren, Ersatz, Gewicht, Tütenanzahl, Endbetrag, kommissionierte Menge (− / +), Anrufprotokoll | ✔ lesen | ✔ | ✔ | US-08 – US-12, US-31 – US-35 | M |
| S-06 | Produkte | Produktliste mit Suche, Filtern und Vorrätig-Schalter | ✔ | ✔ | ✔ lesen + Bestand | US-16, US-17 | M |
| S-07 | Produktformular | Produkt anlegen / bearbeiten / archivieren, Namen auf EN / DE / TR | ✔ | ✔ | – | US-13 – US-15 | M |
| S-08 | Retouren | Retourenanfragen mit Reitern *Beantragt / Genehmigt / Abgelehnt*, genehmigen oder ablehnen | ✔ lesen | ✔ | ✔ | US-18, US-19 | M |
| S-09 | Filialen | Filialdaten, Öffnungszeiten, geöffnet / geschlossen, Tütengebühr | ✔ | ✔ eigene | – | US-21, US-33 | M |
| S-10 | Benutzer | Filialmitarbeiter anlegen, bearbeiten, deaktivieren | ✔ | ✔ eigene Filialen | – | US-22 | M |
| S-11 | Mein Profil | Name, Sprache, Passwort | ✔ | ✔ | ✔ | US-03, US-23 | M |
| S-12 | Bericht: Kanalumsatz | Reiter Tag / Woche / Monat / Jahr: Bestellungen, Umsatz, durchschnittlicher Bestellwert, Stornierungen, Retouren, Diagramm und Detailtabelle; in der Tagesansicht öffnen die Kästen die Liste hinter der Zahl | ✔ | ✔ | – | US-26, US-28, US-52 | S |
| S-13 | Bericht: Produkte | Top- und Flop-Produkte, Anteil pro Kanal, Anzahl nicht vorrätig | ✔ | ✔ | – | US-27, US-28 | S |
| S-14 | Bericht: Stornogründe | Stornierungen pro Grund als Diagramm, pro Kanal und Filiale | ✔ | ✔ | – | US-30, US-28 | S |
| S-15 | Admin: Händler | Händler und ihre Filialen anlegen, bearbeiten, deaktivieren | ✔ | – | – | US-24 | S |
| S-16 | Admin: Audit-Log | Wer hat wann was geändert; nur lesen | ✔ | – | – | US-25 | M |
| S-17 | Kanalauswahl | Nach der Anmeldung HTGO Market oder GK Market wählen; zeigt offene Bestellungen pro Kanal | ✔ | ✔ | ✔ | US-39 | M |
| S-18 | Bericht: Kommissionierqualität | Scans, manuelle Zählungen und Fehlscans pro Filiale und Mitarbeiter | ✔ | ✔ | – | US-38 | S |
| S-19 | Verkaufsstatus | Ein Schalter pro Produkt und Kanal (HTGO / GK); Bestand 0 sperrt automatisch | ✔ | ✔ | ✔ | US-40 | M |
| S-20 | Benachrichtigungen & WhatsApp | Erinnerungsregeln, Empfänger mit WhatsApp-Einwilligung, gesendete Erinnerungen, Demo-Buttons | ✔ | ✔ | ✔ lesen | US-41 – US-43 | S |
| S-21 | Retourenannahme | Zurückgebrachte Produkte abhaken, QR-Code erstellen, angenommene Retouren und Erstattungen sehen | ✔ lesen | ✔ | ✔ | US-48, US-49 | M |
| S-22 | Kurierbestätigung | Öffnet sich beim Scannen des QR-Codes, ohne Anmeldung; der Kurier bestätigt die Übergabe | – | – | – (Kurier) | US-48 | M |
| S-23 | Bewertungen | Durchschnitt, Sternverteilung, Service / Geschmack / Lieferung, neueste Bewertungen | ✔ | ✔ | ✔ | US-51 | S |

Der Kanal-Simulator (US-29) hat keinen Bildschirm im Panel; er läuft als separater Dienst und wird über eine Konfigurationsdatei eingestellt.

## 3. Seitenmenü pro Rolle
| Menüeintrag | Admin | Händler | Filialmitarbeiter |
|---|---|---|---|
| Dashboard | ✔ | ✔ | ✔ |
| Bestellungen | ✔ | ✔ | ✔ |
| Produkte | ✔ | ✔ | ✔ |
| Retouren | ✔ | ✔ | ✔ |
| Filialen | ✔ | ✔ | – |
| Benutzer | ✔ | ✔ | – |
| Berichte | ✔ | ✔ | – |
| Admin (Händler, Audit-Log) | ✔ | – | – |

Das Ausblenden eines Menüeintrags dient nur der Übersicht; der Server prüft trotzdem jede Anfrage (Regel R2).

## 4. Wireframes

### S-04 – Bestellungen
Die Liste **und** alle Reiter-Zähler zeigen nur den beim Betreten gewählten Kanal (S-17). Standardmäßig ist *Offen* geöffnet.
```
┌──────────────────────────────────────────────────────────────────────────
│ Bestellungen                                   🔍 Bestellnummer suchen
│
│ ◉ HTGO Market                                 [ Kanal wechseln ]
│
│ [ Offen 5 ] [ In Kommissionierung 3 ] [ Bereit 2 ] [ Alle 104 ] [ Storniert 4 ]
│ ────────────────────────────────────────────────────────────────────────
│ Kanal    Best.-Nr.   Zeit   Artikel  Summe    Wartezeit
│ HTGO     HT-10482    14:02    6      23,40 €  4 Min. ◀ rot, über 3 Min.
│ GK       GK-55120    14:05    2       4,18 €  1 Min.
│ HTGO     HT-10483    14:06   11      41,90 €  0 Min. NEU
│                              [ Kommissionierung starten ]  pro Zeile
└──────────────────────────────────────────────────────────────────────────
```
| Reiter | Enthält | Zusätzliche Spalten |
|---|---|---|
| Offen | Status *Offen* | Wartezeit, rot nach 3 Minuten |
| In Kommissionierung | Status *In Kommissionierung* | Mitarbeiter, der kommissioniert |
| Bereit | Status *Bereit* | Zeit seit Bereit |
| Alle | Alle Bestellungen, inkl. *Unterwegs* und *Zugestellt* | Status, Filter nach Status / Filiale / Datum |
| Storniert | Status *Storniert* | Stornogrund, storniert von (Filiale / Kanal) |

### S-05 – Bestelldetails
```
┌──────────────────────────────────────────────────────────────────────────
│ ← Bestellungen   HT-10482 · HTGO Market · Filiale Mitte    Status: Offen
│
│ Artikel                                     Menge  Preis    Summe
│ Milvara Vollmilch 3,5 % · 1 L                 2    1,29 €   2,58 €
│ Crumbwell Buttercroissants · 4 Stk.           1    2,49 €   2,49 € ⚠
│ ...
│                                                    Summe:  23,40 €
│ Kundenhinweis: „Bitte zweimal klingeln“
│
│ Statusverlauf
│ 14:02  Offen   eingegangen von HTGO Market
│
│ [ Kommissionierung starten ]               [ Bestellung stornieren … ]
└──────────────────────────────────────────────────────────────────────────
```
⚠ = in dieser Filiale nicht vorrätig. Die Schaltflächen wechseln mit dem Status: *Offen* → „Kommissionierung starten“, *In Kommissionierung* → „Als bereit markieren“. Ab *Bereit* gibt es keine Schaltflächen mehr; die nächsten Status setzt der Kanal.
Während der Kommissionierung hat jede Zeile die Aktionen **Ersetzen …** und **Entfernen**; Gewichtsartikel haben zusätzlich ein **Gewichtsfeld** (kg). „Als bereit markieren“ öffnet einen kleinen Dialog für die **Anzahl der Tüten** und zeigt bestellten Betrag, Endbetrag und Differenz, bevor alles an den Kanal gesendet wird (US-31 – US-33).
Jede Zeile zeigt **kommissioniert / bestellt** mit **−** / **+** (ein Barcode-Scan zählt 1 dazu). Die Schaltfläche **Kunden anrufen** protokolliert jeden Versuch („Versuch 1 von 2“) und sein Ergebnis. **Bestellung stornieren …** bleibt deaktiviert, bis die Anrufregel erfüllt ist (R6).
Jeder Scan wird mit der Bestellung abgeglichen (US-37): Ein Produkt, das nicht in der Bestellung ist, löst einen Fehlerton und eine rote Meldung aus und wird nicht gezählt; **+** ohne Scan wird als *manuelle* Zählung gespeichert.

### S-03 – Dashboard
```
┌──────────────────────────────────────────────────────────────────────────
│ Dashboard · heute
│ ┌──────────────┐ ┌──────────────┐ ┌──────────────┐ ┌──────────────┐
│ │ Umsatz       │ │ HTGO         │ │ GK           │ │ Offene       │
│ │ 1.284,50 €   │ │ 812,10 €     │ │ 472,40 €     │ │ Retouren  3  │
│ └──────────────┘ └──────────────┘ └──────────────┘ └──────────────┘
│ Aktive Bestellungen:  Offen 5 · In Kommissionierung 3 · Bereit 2
│ Durchschnittliche Vorbereitungszeit heute: 7 Min.
│ Filialbewertung (30 Tage): 1,4 % von der Filiale verursachte Stornierungen · Ziel ≤ 2 %
│ Bestandswarnungen: 6 nicht vorrätig · 8 geringer Bestand  [ anzeigen ]
└──────────────────────────────────────────────────────────────────────────
```
Jede Kachel ist anklickbar und öffnet die passende gefilterte Liste.

### S-17 – Kanalauswahl
Erscheint direkt nach der Anmeldung (US-39). Jede Karte nutzt die Farben ihres Kanals; die Wahl wird gespeichert und kann über **Kanal wechseln** in der oberen Leiste geändert werden.
```
┌──────────────────────────────────────────────────────────────────────────
│ Kanal wählen                                            🇬🇧 EN · 🇩🇪 DE · 🇹🇷 TR
│
│ ┌───────────────────────────────┐   ┌───────────────────────────────┐
│ │  [Tüte]  HTGO Market          │   │  [Korb]  GK Market            │
│ │          blau                 │   │          braun                │
│ │  Offene Bestellungen: 5       │   │  Offene Bestellungen: 3       │
│ │            [ Öffnen ]         │   │            [ Öffnen ]         │
│ └───────────────────────────────┘   └───────────────────────────────┘
└──────────────────────────────────────────────────────────────────────────
```
