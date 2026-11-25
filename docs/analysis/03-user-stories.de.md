# 03 – User Stories

🇬🇧 [English](03-user-stories.md) · 🇩🇪 **Deutsch** · 🇹🇷 [Türkçe](03-user-stories.tr.md)

## Lesehilfe
- **Format:** *Als* &lt;Rolle&gt; *möchte ich* &lt;Funktion&gt;, *damit* &lt;Nutzen&gt;.
- **Akzeptanzkriterien** im Format *Gegeben / Wenn / Dann*. Eine Story ist erst fertig, wenn alle Kriterien erfüllt sind.
- **Priorität (MoSCoW):** **M** = Must (MVP) · **S** = Should (nach dem MVP) · **C** = Could (wenn Zeit bleibt)
- **Ziel** verknüpft jede Story mit den Geschäftszielen aus [01 – Projektumfang](01-scope.de.md) (G1–G4). **–** = unterstützende Story (Sicherheit, Grundlagen).

## Übersicht
| ID | Epic | Story (kurz) | Rolle | Prio | Ziel |
|---|---|---|---|---|---|
| US-01 | Zugang | Anmelden | Alle Benutzer | M | – |
| US-02 | Zugang | Brute-Force-Anmeldeversuche blockieren | Alle Benutzer | M | – |
| US-03 | Zugang | Sprache EN / DE / TR wechseln | Alle Benutzer | M | – |
| US-04 | Zugang | Nur eigene Daten sehen | Händler, Filialmitarbeiter | M | – |
| US-05 | Bestellungen | Signierte Bestellung von einem Kanal empfangen | Kanal | M | G1 |
| US-06 | Bestellungen | Neue Bestellungen sofort sehen | Filialmitarbeiter | M | G1 |
| US-07 | Bestellungen | Bestellliste mit Status-Reitern und Kanalauswahl | Filialmitarbeiter, Händler | M | G1 |
| US-08 | Bestellungen | Bestelldetails öffnen | Filialmitarbeiter | M | G2 |
| US-09 | Bestellungen | Kommissionierung einer Bestellung starten | Filialmitarbeiter | M | G2 |
| US-10 | Bestellungen | Bestellung als bereit markieren | Filialmitarbeiter | M | G2 |
| US-11 | Bestellungen | *Unterwegs* / *Zugestellt* vom Kanal empfangen | Kanal | M | G2 |
| US-12 | Bestellungen | Bestellung stornieren | Filialmitarbeiter, Kanal | M | G1 |
| US-13 | Produkte | Produkt anlegen | Händler | M | G3 |
| US-14 | Produkte | Preis, Bild und Details ändern | Händler | M | G3 |
| US-15 | Produkte | Produkt entfernen | Händler | M | G3 |
| US-16 | Produkte | Produkt in meiner Filiale als nicht vorrätig markieren | Filialmitarbeiter | M | G3 |
| US-17 | Produkte | Produkte suchen und filtern | Händler, Filialmitarbeiter | M | G3 |
| US-18 | Retouren | Retourenanfrage von einem Kanal empfangen | Kanal | M | G4 |
| US-19 | Retouren | Retoure genehmigen oder ablehnen | Filialmitarbeiter, Händler | M | G4 |
| US-20 | Dashboard | Kennzahlen des Tages sehen | Alle Benutzer | M | G1, G4 |
| US-21 | Filialen & Benutzer | Filialdaten verwalten | Händler | M | – |
| US-22 | Filialen & Benutzer | Filialmitarbeiter verwalten | Händler | M | – |
| US-23 | Filialen & Benutzer | Eigenes Profil und Passwort ändern | Alle Benutzer | M | – |
| US-24 | Admin | Händler und Filialen verwalten | Admin | S | – |
| US-25 | Admin | Audit-Log einsehen | Admin | M | – |
| US-26 | Berichte | Umsatzbericht pro Kanal | Händler, Admin | S | G4 |
| US-27 | Berichte | Produktbericht | Händler, Admin | S | G4 |
| US-28 | Berichte | Bericht als CSV exportieren | Händler, Admin | C | G4 |
| US-29 | Simulator | Realistische Demo-Bestellungen erzeugen | Demo-Präsentator | M | – |
| US-30 | Berichte | Bericht zu Stornogründen | Händler, Admin | S | G4 |
| US-31 | Kommissionierung | Fehlenden Artikel ersetzen | Filialmitarbeiter | M | G1 |
| US-32 | Kommissionierung | Tatsächliches Gewicht eingeben | Filialmitarbeiter | M | G4 |
| US-33 | Kommissionierung | Tütengebühr berechnen | Filialmitarbeiter | M | G4 |
| US-34 | Kommissionierung | Jede Zeile beim Kommissionieren zählen | Filialmitarbeiter | M | G2 |
| US-35 | Kommissionierung | Jeden Anruf beim Kunden protokollieren | Filialmitarbeiter | M | G5 |
| US-36 | Dashboard | Filialbewertung sehen | Händler, Filialmitarbeiter | M | G5 |
| US-37 | Kommissionierung | Jeden gescannten Barcode prüfen | Filialmitarbeiter | M | G2 |
| US-38 | Berichte | Bericht zur Kommissionierqualität | Händler, Admin | S | G2 |
| US-39 | Zugang | Kanal beim Betreten des Panels wählen | Filialmitarbeiter, Händler | M | G1 |
| US-40 | Produkte | Produkt pro Kanal für den Verkauf freigeben oder sperren | Filialmitarbeiter | M | G3 |
| US-41 | Bestellungen | Hinweis mit Ton bei neuer Bestellung | Filialmitarbeiter | M | G1 |
| US-42 | Bestellungen | Sehen, wie lange Bestellungen warten | Filialmitarbeiter | M | G1 |
| US-43 | Bestellungen | WhatsApp-Erinnerung für wartende Bestellungen | Filialleitung | S | G1 |
| US-44 | Kommissionierung | Endbetrag schützen | Händler, Filialmitarbeiter | M | G4 |
| US-45 | Kommissionierung | Fehlende Produkte mit Zustimmung des Kunden entfernen | Filialmitarbeiter | M | G4 |
| US-46 | Kommissionierung | Bestellungen über der Kurierkapazität teilen | Filialmitarbeiter | M | G2 |
| US-47 | Retouren | Nur einige Produkte zurückgeben | Filialmitarbeiter | M | G4 |
| US-48 | Retouren | Retouren mit QR-Code annehmen | Filialmitarbeiter, Kurier | M | G4 |
| US-49 | Retouren | Erstattung nach Annahme der Retoure | Kunde (über den Kanal) | M | G4 |
| US-50 | Retouren | Gründe sehen und filtern | Filialmitarbeiter | S | G4 |
| US-51 | Dashboard | Kundenbewertungen sehen | Händler, Filialmitarbeiter | S | G5 |
| US-52 | Berichte | Verkaufsbericht pro Tag, Woche, Monat, Jahr | Händler | S | G4 |
| US-53 | Zugang | Tag- und Nachtmodus | Alle Nutzer | C | – |

---

## Epic 1 – Zugang

### US-01 – Anmelden · M
*Als* Benutzer *möchte ich* mich mit E-Mail und Passwort anmelden, *damit* nur ich mit meinen Daten arbeiten kann.
- **Gegeben** gültige E-Mail und Passwort, **wenn** ich mich anmelde, **dann** sehe ich das Dashboard für meine Rolle.
- **Gegeben** ein falsches Passwort, **wenn** ich mich anmelde, **dann** sehe ich „E-Mail oder Passwort ist falsch“ (die Meldung verrät nicht, was falsch war).
- **Gegeben** ich war 8 Stunden inaktiv, **wenn** ich etwas anklicke, **dann** werde ich abgemeldet und zur Anmeldeseite geleitet.

### US-02 – Brute-Force-Anmeldeversuche blockieren · M
*Als* Benutzer *möchte ich*, dass mein Konto vor dem Erraten von Passwörtern geschützt ist, *damit* niemand durch viele Versuche eindringen kann.
- **Gegeben** 5 fehlgeschlagene Anmeldungen für dasselbe Konto innerhalb von 15 Minuten, **wenn** ein 6. Versuch erfolgt, **dann** wird das Konto für 15 Minuten gesperrt und der Versuch im Audit-Log gespeichert.
- **Gegeben** ein gesperrtes Konto, **wenn** die Sperrzeit abgelaufen ist, **dann** kann ich mich wieder normal anmelden.

### US-03 – Sprache EN / DE / TR wechseln · M
*Als* Benutzer *möchte ich* die Oberfläche zwischen Englisch, Deutsch und Türkisch umschalten, *damit* ich in der Sprache arbeite, die ich am besten kann.
- **Gegeben** ich bin angemeldet, **wenn** ich 🇬🇧 EN, 🇩🇪 DE oder 🇹🇷 TR wähle, **dann** wechseln alle Bildschirmtexte, Produktnamen, Kategorien, Datums- und Preisformate in diese Sprache.
- **Gegeben** ich habe eine Sprache gewählt, **wenn** ich mich das nächste Mal anmelde, **dann** öffnet sich das Panel in dieser Sprache.
- Markennamen werden nie übersetzt.

### US-04 – Nur eigene Daten sehen · M
*Als* Händler oder Filialmitarbeiter *möchte ich* nur die Daten sehen, für die ich zuständig bin, *damit* Geschäftsdaten anderer Händler vertraulich bleiben.
- **Gegeben** ich bin Händler, **wenn** ich eine Liste öffne, **dann** sehe ich nur meine eigenen Filialen, Produkte, Bestellungen und Retouren.
- **Gegeben** ich bin Filialmitarbeiter, **wenn** ich eine Liste öffne, **dann** sehe ich nur Daten meiner eigenen Filiale.
- **Gegeben** ich ändere eine ID in der URL auf die Bestellung eines anderen Händlers, **wenn** die Seite lädt, **dann** erhalte ich „Nicht gefunden“ und der Versuch wird im Audit-Log gespeichert.

## Epic 2 – Bestellungen

### US-05 – Signierte Bestellung von einem Kanal empfangen · M
*Als* Kanal (HTGO / GK) *möchte ich* neue Bestellungen an VetisPilot senden, *damit* die Filiale sie vorbereiten kann.
- **Gegeben** eine Bestellung mit gültiger HMAC-Signatur, **wenn** sie gesendet wird, **dann** wird sie mit Status *Offen*, Kanal und Filiale gespeichert und der Kanal erhält eine Bestätigung.
- **Gegeben** eine Bestellung mit fehlender oder falscher Signatur, **wenn** sie gesendet wird, **dann** wird sie abgelehnt und nichts gespeichert.
- **Gegeben** dieselbe Bestellnummer wird zweimal gesendet, **wenn** die zweite ankommt, **dann** entsteht kein Duplikat.
- **Gegeben** eine Bestellung enthält ein Produkt, das in dieser Filiale nicht vorrätig ist, **wenn** sie ankommt, **dann** wird sie gespeichert und der Artikel für die Filiale hervorgehoben.

### US-06 – Neue Bestellungen sofort sehen · M
*Als* Filialmitarbeiter *möchte ich*, dass neue Bestellungen ohne Neuladen der Seite erscheinen, *damit* keine Bestellung übersehen wird.
- **Gegeben** die Bestellliste ist offen, **wenn** eine neue Bestellung eintrifft, **dann** erscheint sie innerhalb von 5 Sekunden oben mit dem Hinweis „Neu“ und einem Ton.
- **Gegeben** eine Bestellung ist länger als 3 Minuten im Status *Offen*, **wenn** ich auf die Liste schaue, **dann** ist sie rot hervorgehoben.

### US-07 – Bestellliste mit Status-Reitern und Kanalauswahl · M
*Als* Filialmitarbeiter oder Händler *möchte ich*, dass die Bestellliste für beide Kanäle in Status-Reiter mit Live-Zählern aufgeteilt ist, *damit* ich auf einen Blick sehe, was zu tun ist.
- **Gegeben** ich öffne „Bestellungen“, **dann** sehe ich die Reiter **Offen (n)**, **In Kommissionierung (n)**, **Bereit (n)**, **Alle (n)** und **Storniert (n)**; *n* ist die Anzahl der Bestellungen im Reiter. Standardmäßig ist *Offen* geöffnet.
- **Gegeben** der beim Betreten des Panels gewählte Kanal (US-39), **dann** zeigen die Liste und alle Zähler nur Bestellungen dieses Kanals. Jede Zeile zeigt ein Kanal-Kennzeichen.
- **Gegeben** eine neue Bestellung oder ein Statuswechsel, **dann** aktualisieren sich die Zähler innerhalb von 5 Sekunden ohne Neuladen.
- **Gegeben** der Reiter *Alle*, **dann** enthält er alle Bestellungen einschließlich *Unterwegs* und *Zugestellt*; ich kann nach Status, Filiale (nur Händler) und Datum filtern.
- **Gegeben** der Reiter *Storniert*, **dann** zeigt jede Zeile zusätzlich den Stornogrund und wer storniert hat (Filiale oder Kanal).
- **Gegeben** ich gebe eine Bestellnummer ein, **wenn** ich suche, **dann** wird die passende Bestellung angezeigt, egal welcher Reiter offen ist.

### US-08 – Bestelldetails öffnen · M
*Als* Filialmitarbeiter *möchte ich* alle Details einer Bestellung sehen, *damit* ich die richtigen Artikel zusammenstelle.
- **Gegeben** eine Bestellung, **wenn** ich sie öffne, **dann** sehe ich Kanal, Bestellnummer, Uhrzeit, Artikel (Name, Größe, Menge, Preis), Summe, Kundenhinweis und Statusverlauf (wer hat wann was geändert).

### US-09 – Kommissionierung einer Bestellung starten · M
*Als* Filialmitarbeiter *möchte ich* eine Bestellung von *Offen* auf *In Kommissionierung* setzen, *damit* alle sehen, dass sie bearbeitet wird.
- **Gegeben** eine Bestellung im Status *Offen*, **wenn** ich auf „Kommissionierung starten“ klicke, **dann** wird der Status *In Kommissionierung*, mein Name und die Uhrzeit werden gespeichert und der Kanal wird informiert.
- **Gegeben** eine Bestellung in einem anderen Status, **dann** ist die Schaltfläche nicht verfügbar.

### US-10 – Bestellung als bereit markieren · M
*Als* Filialmitarbeiter *möchte ich* eine Bestellung als *Bereit* markieren, *damit* der Kurier des Kanals sie abholen kann.
- **Gegeben** eine Bestellung im Status *In Kommissionierung*, **wenn** ich auf „Bereit“ klicke, **dann** wird der Status *Bereit*, die Vorbereitungszeit (*Offen* → *Bereit*) wird gespeichert und der Kanal wird informiert.

### US-11 – *Unterwegs* / *Zugestellt* vom Kanal empfangen · M
*Als* Kanal *möchte ich* melden, wann der Kurier eine Bestellung abgeholt und zugestellt hat, *damit* die Filiale den gesamten Ablauf sieht.
- **Gegeben** eine Bestellung im Status *Bereit*, **wenn** der Kanal eine signierte Meldung „abgeholt“ sendet, **dann** wird der Status *Unterwegs*.
- **Gegeben** eine Bestellung im Status *Unterwegs*, **wenn** der Kanal „zugestellt“ sendet, **dann** wird der Status *Zugestellt* und die Bestellung zählt zum Umsatz.
- **Gegeben** eine Statusmeldung, die nicht zum aktuellen Status passt (z. B. *Zugestellt* für eine *offene* Bestellung), **dann** wird sie abgelehnt und protokolliert.

### US-12 – Bestellung stornieren · M
*Als* Filialmitarbeiter oder Kanal *möchte ich* eine Bestellung stornieren, bevor sie bereit ist, *damit* Kunden nicht für Bestellungen zahlen, die nicht geliefert werden können.
- **Gegeben** eine Bestellung im Status *Offen* oder *In Kommissionierung*, **wenn** die Filiale sie storniert, **dann** muss ein Grund gewählt werden (z. B. „Artikel fehlt“), der Status wird *Storniert* und der Kanal wird informiert.
- **Gegeben** eine Bestellung im Status *Offen* oder *In Kommissionierung*, **wenn** der Kanal sie storniert, **dann** wird der Status *Storniert* und die Filiale erhält eine Benachrichtigung.
- **Gegeben** eine Bestellung im Status *Bereit* oder später, **dann** kann sie nicht mehr storniert werden.
- Bestellungen werden nie gelöscht (Regel R3).
- **Gegeben** die Filiale möchte stornieren, **dann** muss zuerst ein Grund gewählt werden (nichts ist vorausgewählt), und die Schaltfläche wechselt von grau zu rot. *Filiale überlastet* ist sofort erlaubt; *Kunde nicht erreichbar* und *Artikel nicht vorrätig* erst nach 2 protokollierten Anrufen (Regel R6, aktualisiert – siehe [06 §4](06-prototype-decisions.de.md)).
- **Gegeben** 2 Anrufe blieben unbeantwortet, **dann** storniert das System mit dem Grund *Kunde nicht erreichbar*, und die Stornierung zählt **nicht** gegen die Filialbewertung (US-36). Stornierungen aus anderen Gründen zählen.

## Epic 3 – Produkte und Bestand

### US-13 – Produkt anlegen · M
*Als* Händler *möchte ich* ein neues Produkt hinzufügen, *damit* es in beiden Kanälen verkauft werden kann.
- **Gegeben** das Produktformular, **wenn** ich Name (EN / DE / TR), Marke, Kategorie, Größe, Preis und optional ein Bild eingebe und speichere, **dann** ist das Produkt in allen meinen Filialen verfügbar.
- **Gegeben** ein Pflichtfeld ist leer oder der Preis ist keine positive Zahl, **wenn** ich speichere, **dann** sehe ich einen Fehler neben dem Feld und nichts wird gespeichert.
- **Gegeben** ein Bild größer als 2 MB oder nicht JPG / PNG / WebP, **wenn** ich es hochlade, **dann** wird es mit einer Meldung abgelehnt.

### US-14 – Preis, Bild und Details ändern · M
*Als* Händler *möchte ich* ein Produkt ändern, *damit* beide Kanäle immer korrekte Informationen zeigen.
- **Gegeben** ein Produkt, **wenn** ich den Preis ändere und speichere, **dann** gilt der neue Preis für neue Bestellungen in beiden Kanälen; bestehende Bestellungen behalten den alten Preis.
- **Gegeben** jede Änderung, **dann** werden alter und neuer Wert im Audit-Log gespeichert.

### US-15 – Produkt entfernen · M
*Als* Händler *möchte ich* ein Produkt entfernen, das ich nicht mehr verkaufe, *damit* es nicht mehr bestellt werden kann.
- **Gegeben** ein Produkt, das nie bestellt wurde, **wenn** ich es lösche, **dann** wird es entfernt.
- **Gegeben** ein Produkt, das in früheren Bestellungen vorkommt, **wenn** ich es lösche, **dann** wird es stattdessen archiviert: Es verschwindet aus der Produktliste und den Kanälen, aber alte Bestellungen und Berichte zeigen es weiterhin.

### US-16 – Produkt in meiner Filiale als nicht vorrätig markieren · M
*Als* Filialmitarbeiter *möchte ich* ein Produkt für meine Filiale zwischen „vorrätig“ und „nicht vorrätig“ umschalten, *damit* Kunden nichts bestellen, was wir nicht haben.
- **Gegeben** ein Produkt, **wenn** ich es auf „nicht vorrätig“ setze, **dann** ist es innerhalb von 5 Sekunden in beiden Kanälen nur für meine Filiale nicht verfügbar.
- **Gegeben** der Bestand eines Produkts erreicht 0, **dann** wird es automatisch auf „nicht vorrätig“ gesetzt.
- **Gegeben** ein Bestand unter 10, **dann** zeigt das Produkt den Hinweis „Geringer Bestand“.

### US-17 – Produkte suchen und filtern · M
*Als* Händler oder Filialmitarbeiter *möchte ich* Produkte suchen und filtern, *damit* ich ein Produkt unter vielen finde.
- **Gegeben** die Produktliste, **wenn** ich nach Name, SKU oder Barcode suche oder nach Kategorie, Marke oder Bestandsstatus filtere, **dann** werden nur passende Produkte angezeigt.

## Epic 4 – Retouren

### US-18 – Retourenanfrage von einem Kanal empfangen · M
*Als* Kanal *möchte ich* die Retourenanfrage eines Kunden senden, *damit* die Filiale darüber entscheiden kann.
- **Gegeben** eine zugestellte Bestellung, **wenn** der Kanal eine signierte Retourenanfrage mit Artikeln und Grund sendet, **dann** wird eine Retoure mit Status *Beantragt* angelegt und erscheint im Dashboard als „offene Retoure“.
- **Gegeben** eine Bestellung, die nicht *Zugestellt* ist, **dann** wird die Anfrage abgelehnt.

### US-19 – Retoure genehmigen oder ablehnen · M
*Als* Filialmitarbeiter oder Händler *möchte ich* eine Retoure genehmigen oder ablehnen, *damit* der Kunde eine klare Antwort erhält.
- **Gegeben** eine Retoure im Status *Beantragt*, **wenn** ich sie genehmige, **dann** wird der Status *Genehmigt*, der Betrag wird vom Umsatz abgezogen und der Kanal wird informiert.
- **Gegeben** eine Retoure im Status *Beantragt*, **wenn** ich sie ablehne, **dann** ist ein Grund Pflicht, der Status wird *Abgelehnt* und der Kanal wird informiert.

## Epic 5 – Dashboard

### US-20 – Kennzahlen des Tages sehen · M
*Als* Benutzer *möchte ich* ein Dashboard mit den wichtigsten Zahlen des Tages, *damit* ich die Lage auf einen Blick erfasse.
- **Gegeben** ich öffne das Dashboard, **dann** sehe ich für meinen Bereich (alle / meine Filialen / meine Filiale): heutigen Umsatz (gesamt und pro Kanal), aktive Bestellungen pro Status, offene Retouren sowie nicht vorrätige Produkte und Produkte mit geringem Bestand.
- **Gegeben** eine neue Bestellung oder Statusänderung, **dann** aktualisieren sich die Zahlen ohne Neuladen der Seite.

## Epic 6 – Filialen und Benutzer

### US-21 – Filialdaten verwalten · M
*Als* Händler *möchte ich* meine Filialdaten (Name, Adresse, Öffnungszeiten, geöffnet / geschlossen) bearbeiten, *damit* Kanäle nur dann Bestellungen senden, wenn eine Filiale geöffnet ist.
- **Gegeben** ich setze eine Filiale auf „geschlossen“, **dann** erhalten Kanäle für neue Bestellungen an diese Filiale eine Ablehnung.

### US-22 – Filialmitarbeiter verwalten · M
*Als* Händler *möchte ich* Mitarbeiterkonten für meine Filialen anlegen, bearbeiten und deaktivieren, *damit* mein Personal das Panel nutzen kann.
- **Gegeben** ich lege einen Mitarbeiter mit Name, E-Mail und Filiale an, **dann** erhält das Konto ein temporäres Passwort, das bei der ersten Anmeldung geändert werden muss.
- **Gegeben** ich deaktiviere einen Mitarbeiter, **dann** kann sich diese Person nicht mehr anmelden, ihre früheren Aktionen bleiben aber im Audit-Log.

### US-23 – Eigenes Profil und Passwort ändern · M
*Als* Benutzer *möchte ich* meinen Namen, meine Sprache und mein Passwort ändern, *damit* mein Konto aktuell und sicher ist.
- **Gegeben** ich ändere mein Passwort, **dann** muss das neue Passwort mindestens 10 Zeichen mit Buchstaben und Ziffern haben und das alte Passwort ist erforderlich.

## Epic 7 – Admin

### US-24 – Händler und Filialen verwalten · S
*Als* Admin *möchte ich* Händler und ihre Filialen anlegen, bearbeiten und deaktivieren, *damit* neue Geschäfte aufgenommen werden können.
- **Gegeben** ich lege einen Händler an, **dann** wird sein erstes Benutzerkonto mit temporärem Passwort erstellt.
- **Gegeben** ich deaktiviere einen Händler, **dann** werden alle seine Benutzer gesperrt und seine Filialen nehmen keine Bestellungen mehr an.

### US-25 – Audit-Log einsehen · M
*Als* Admin *möchte ich* sehen, wer wann was geändert hat, *damit* ich Probleme und Missbrauch nachvollziehen kann.
- **Gegeben** das Audit-Log, **wenn** ich nach Benutzer, Datum oder Bereich filtere, **dann** sehe ich Zeit, Benutzer, Aktion, Objekt, alten und neuen Wert.
- Audit-Einträge können von niemandem bearbeitet oder gelöscht werden.

## Epic 8 – Berichte

### US-26 – Umsatzbericht pro Kanal · S
*Als* Händler oder Admin *möchte ich* einen Umsatzbericht pro Kanal (HTGO, GK), *damit* ich die Kanäle vergleichen kann.
- **Gegeben** ein Zeitraum, **dann** sehe ich pro Kanal: Anzahl Bestellungen, Umsatz, durchschnittlichen Bestellwert, Stornierungen und Retouren, auch pro Filiale.

### US-27 – Produktbericht · S
*Als* Händler oder Admin *möchte ich* einen Produktbericht, *damit* ich weiß, welche Produkte sich gut oder schlecht verkaufen.
- **Gegeben** ein Zeitraum, **dann** sehe ich pro Produkt: verkaufte Menge, Umsatz, Anteil pro Kanal und wie oft es nicht vorrätig war.

### US-28 – Bericht als CSV exportieren · C
*Als* Händler oder Admin *möchte ich* einen Bericht als CSV herunterladen, *damit* ich ihn in Excel weiter analysieren kann.
- **Gegeben** ein Bericht auf dem Bildschirm, **wenn** ich auf „Exportieren“ klicke, **dann** wird eine CSV-Datei mit genau den angezeigten Daten heruntergeladen.

### US-30 – Bericht zu Stornogründen · S
*Als* Händler oder Admin *möchte ich* sehen, warum Bestellungen storniert wurden, *damit* ich die Ursache beheben kann (z. B. schlechte Bestandsführung).
- **Gegeben** ein Zeitraum, **dann** sehe ich die Anzahl der Stornierungen pro Grund (z. B. Artikel fehlt, Filiale überlastet, Kunde hat storniert) als Diagramm, pro Kanal und pro Filiale.
- **Gegeben** ich klicke auf einen Grund, **dann** sehe ich die Liste dieser stornierten Bestellungen.

## Epic 9 – Kanal-Simulator

### US-29 – Realistische Demo-Bestellungen erzeugen · M
*Als* Demo-Präsentator *möchte ich* einen Simulator, der sich wie HTGO und GK verhält, *damit* ich das Panel live in Aktion zeigen kann.
- **Gegeben** der Simulator läuft, **dann** sendet er signierte Bestellungen mit Produkten aus dem Demo-Katalog an zufällige geöffnete Filialen in einstellbarer Frequenz (z. B. alle 20 Sekunden eine Bestellung).
- **Gegeben** eine Bestellung ist *Bereit*, **dann** sendet der Simulator *Unterwegs* und später *Zugestellt* und gelegentlich eine Stornierung oder Retourenanfrage.

## Epic 10 – Kommissionierung und Endbetrag

### US-31 – Fehlenden Artikel ersetzen · M
*Als* Filialmitarbeiter *möchte ich* dem Kunden einen Ersatz anbieten, wenn ein Artikel fehlt, *damit* der Kunde trotzdem bekommt, was er braucht, und den richtigen Preis zahlt.
- **Gegeben** ein Artikel ist bei der Kommissionierung nicht verfügbar, **wenn** ich auf „Ersetzen …“ klicke, **dann** sehe ich vorrätige Produkte derselben Kategorie, sortiert nach Preisnähe, und die vom Kanal übermittelte Telefonnummer des Kunden.
- **Gegeben** ich habe den Kunden angerufen und er hat zugestimmt, **wenn** ich den Ersatz wähle, **dann** zeigt die Zeile das ursprüngliche und das neue Produkt, der neue Preis gilt und die Differenz wird angezeigt (z. B. Fettarme Milch 1,19 € → Vollmilch 1,29 € = **+0,10 €**).
- **Gegeben** der Kunde lehnt ab oder ist nach 2 Anrufen nicht erreichbar (US-35), **dann** wird der Artikel entfernt und nicht berechnet.
- Der Endbetrag sinkt um den Preis des entfernten Artikels (z. B. eine fehlende Packung Croissants für 2,49 € senkt die Summe um 2,49 €).
- Jeder Ersatz und die Antwort des Kunden werden gespeichert (wer, wann) und im Statusverlauf angezeigt.

### US-32 – Tatsächliches Gewicht eingeben · M
*Als* Filialmitarbeiter *möchte ich* das echte Gewicht von Gewichtsartikeln eingeben, *damit* der Kunde genau das bezahlt, was er bekommt.
- **Gegeben** ein Gewichtsartikel (z. B. Bananen, 1,69 € / kg, bestellt 1 kg), **wenn** ich 1,10 kg eingebe, **dann** beträgt der Zeilenbetrag **1,86 €** (1,10 × 1,69, auf den Cent gerundet).
- **Gegeben** ein Gewicht außerhalb von ±10 % des bestellten Gewichts (z. B. 1,20 kg statt 1 kg), **dann** fordert mich das System auf, neu zu wiegen oder vorher die Zustimmung des Kunden einzuholen.
- Artikel, die nicht nach Gewicht verkauft werden, haben kein Gewichtsfeld.

### US-33 – Tütengebühr berechnen · M
*Als* Filialmitarbeiter *möchte ich* eingeben, wie viele Tüten ich verwendet habe, *damit* die Tütengebühr korrekt berechnet wird.
- **Gegeben** eine Bestellung im Status *In Kommissionierung*, **wenn** ich auf „Als bereit markieren“ klicke, **dann** muss ich die Anzahl der Tüten eingeben (0–20).
- **Dann** wird die Tütengebühr (Tüten × Gebühr der Filiale, Standard **0,25 €**) addiert; der **Endbetrag** = alle endgültigen Zeilenbeträge + Tütengebühr wird gespeichert und zusammen mit dem Status *Bereit* an den Kanal gesendet.
- **Gegeben** der Kunde möchte keine Tüte (Hinweis des Kanals), **dann** werden 0 Tüten vorgeschlagen.
- Der Händler kann die Tütengebühr pro Filiale in den Filialeinstellungen ändern (S-09).

### US-34 – Jede Zeile beim Kommissionieren zählen · M
*Als* Filialmitarbeiter *möchte ich* zählen, wie viele Stück jedes Produkts ich kommissioniert habe, *damit* der Kunde genau die bestellte Menge erhält.
- **Gegeben** eine Bestellung im Status *In Kommissionierung*, **dann** zeigt jede Zeile „kommissioniert / bestellt“ (z. B. *Fizzbay Cola Classic · 1 L — 0 / 3*) mit den Schaltflächen **−** und **+**; das Scannen des Barcodes zählt 1 dazu.
- **Gegeben** kommissioniert = bestellt, **dann** wird die Zeile grün. Der Zähler kann nicht über die bestellte Menge steigen.
- **Gegeben** kommissioniert < bestellt (z. B. 2 / 3), **dann** wird die fehlende Menge wie ein fehlender Artikel behandelt (US-31): Kunden anrufen, dann ersetzen oder entfernen. Der Endbetrag zählt nur, was wirklich kommissioniert wurde.
- **Gegeben** mindestens eine Zeile ist nicht geklärt, **dann** ist „Als bereit markieren“ deaktiviert.

### US-35 – Jeden Anruf beim Kunden protokollieren · M
*Als* Filialmitarbeiter *möchte ich*, dass jeder Anruf beim Kunden gespeichert wird, *damit* Entfernungen und Stornierungen nachvollziehbar sind.
- **Gegeben** ich rufe den Kunden aus der Bestellung an, **dann** erfasse ich das Ergebnis: *keine Antwort* oder *erreicht* zusammen mit der Antwort des Kunden (stimmt Ersatz / Entfernung / Stornierung zu oder lehnt ab).
- **Gegeben** ein Anruf blieb unbeantwortet, **dann** ist der nächste Versuch frühestens nach 2 Minuten möglich; das Panel zeigt „Versuch 1 von 2“.
- Das Anrufprotokoll wird in den Bestelldetails angezeigt und kann nachträglich nicht geändert werden.

## Epic 11 – Filialbewertung

### US-36 – Filialbewertung sehen · M
*Als* Händler oder Filialmitarbeiter *möchte ich* sehen, wie viele Stornierungen meine Filiale verursacht hat, *damit* wir uns verbessern und bei den Kanälen gut bewertet bleiben.
- **Gegeben** das Dashboard, **dann** sehe ich die Filialbewertung: von der Filiale verursachte Stornierungen ÷ alle Bestellungen der letzten 30 Tage (Ziel ≤ 2 %), pro Filiale und pro Kanal.
- Von der Filiale verursacht = von der Filiale storniert mit dem Grund *Artikel fehlt*, *Filiale überlastet* oder *Sonstiges*. **Nicht** gezählt: *Kunde nicht erreichbar* nach 2 protokollierten Anrufen, der Kunde wollte stornieren oder der Kanal hat storniert.
- **Gegeben** die Filiale versucht ohne protokollierten Anruf zu stornieren, **dann** lehnt das System ab (Regel R6).

## Epic 12 – Barcode-Prüfung und Kanalwahl

### US-37 – Jeden gescannten Barcode prüfen · M
*Als* Filialmitarbeiter *möchte ich*, dass das System jeden gescannten Barcode mit der Bestellung abgleicht, *damit* kein falsches Produkt eingepackt wird.
- Es funktioniert mit einem Handscanner (der Barcode kommt wie getippter Text an) und mit der Handykamera im Browser.
- **Gegeben** der Barcode gehört zu einer Zeile der Bestellung, **dann** erhält diese Zeile +1 und einen kurzen Bestätigungston.
- **Gegeben** die Zeile ist bereits vollständig, **dann** erscheint der Hinweis „bereits vollständig“ und nichts wird gezählt.
- **Gegeben** das Produkt existiert, ist aber **nicht in dieser Bestellung** (z. B. fettarme statt Vollmilch), **dann** erscheinen ein Fehlerton und die rote Meldung „Dieses Produkt ist nicht in der Bestellung“ und nichts wird gezählt.
- **Gegeben** ein Ersatzprodukt wird gescannt, **dann** zählt es nur, wenn der Kunde diesem Ersatz zugestimmt hat (US-31).
- **Gegeben** der Barcode ist unbekannt, **dann** erscheint „Unbekannter Barcode“ und nichts wird gezählt.
- **Gegeben** ein Gewichtsartikel wird gescannt, **dann** wird seine Zeile ausgewählt und das Gewicht muss eingegeben werden (US-32).
- **Gegeben** ein Barcode ist beschädigt, **dann** kann ich mit **+** von Hand zählen; jede manuelle Zählung wird als *manuell* gespeichert.
- Jeder Scan und jede manuelle Zählung wird gespeichert (wer, wann, Ergebnis).

### US-38 – Bericht zur Kommissionierqualität · S
*Als* Händler oder Admin *möchte ich* manuelle Zählungen und Fehlscans sehen, *damit* ich Schulungsbedarf und beschädigte Barcodes finde.
- **Gegeben** ein Zeitraum, **dann** sehe ich pro Filiale und pro Mitarbeiter: Scans, manuelle Zählungen (Anzahl und Anteil in %) und Fehlscans (nicht in der Bestellung / unbekannt).
- Die Liste ist nach den meisten manuellen Zählungen sortiert. Ein Klick auf eine Zeile zeigt die Produkte, die am häufigsten von Hand gezählt wurden – ein Hinweis auf beschädigte Barcodes.

### US-39 – Kanal beim Betreten des Panels wählen · M
*Als* Filialmitarbeiter oder Händler *möchte ich* beim Betreten des Panels HTGO Market oder GK Market wählen, *damit* ich jeweils in einem Kanal arbeite und immer weiß, woher eine Bestellung kommt.
- **Gegeben** ich habe mich angemeldet, **dann** sehe ich zwei Karten nebeneinander: **HTGO Market** in Blau und **GK Market** in Braun, jeweils mit Logo und Anzahl offener Bestellungen.
- **Gegeben** ich wähle einen Kanal, **dann** nutzt das Panel die Farben und das Logo dieses Kanals, und Bestellungen, Reiter und Dashboard zeigen nur diesen Kanal.
- **Gegeben** ich möchte zum anderen Kanal, **dann** bringt mich „Kanal wechseln“ in der oberen Leiste zurück zur Auswahl; die letzte Wahl wird gespeichert.
- **Gegeben** eine neue Bestellung kommt im Kanal an, den ich gerade nicht ansehe, **dann** erscheint eine Benachrichtigung mit Name und Farbe dieses Kanals, damit sie nicht übersehen wird (G1).
- Berichte (S-12 – S-14, S-18) können weiterhin beide Kanäle nebeneinander vergleichen.

## Epic 13 – Erkenntnisse aus dem klickbaren Prototyp
Diese Stories entstanden beim Testen des Prototyps (v1.0.1). Hintergrund, Beispiele und Gründe: [06 – Prototyp-Entscheidungen](06-prototype-decisions.de.md).

### US-40 – Produkt pro Kanal für den Verkauf freigeben oder sperren · M
*Als* Filialmitarbeiter *möchte ich* ein Produkt in HTGO Market und GK Market getrennt freigeben oder sperren, *damit* Kunden nur sehen, was wir wirklich liefern können.
- **Gegeben** ein Produkt, **dann** sehe ich einen Schalter pro Kanal; gesperrt sehen es die Kunden dieses Kanals nicht.
- **Gegeben** der Bestand ist 0, **dann** ist das Produkt in beiden Kanälen automatisch gesperrt und der Schalter lässt sich nicht einschalten.

### US-41 – Hinweis mit Ton bei neuer Bestellung · M
*Als* Filialmitarbeiter *möchte ich* einen Ton und ein Banner, wenn eine Bestellung eingeht, *damit* ich sie auch bemerke, wenn ich nicht auf den Bildschirm schaue.
- **Gegeben** eine neue Bestellung, **dann** zeigt ein 3-Sekunden-Banner *„Neue Bestellung · Kanal“*, die Bestellnummer und die Zahl der wartenden Bestellungen in den Farben und mit dem Logo des Kanals.
- **Gegeben** HTGO oder GK, **dann** hat jeder Kanal seinen eigenen Ton. In der mobilen App ist das eine Push-Benachrichtigung, auch bei gesperrtem Handy.

### US-42 – Sehen, wie lange Bestellungen warten · M
*Als* Filialmitarbeiter *möchte ich* wartende Bestellungen nach Wartezeit markiert sehen, *damit* ich die älteste zuerst annehme.
- **Gegeben** der Reiter *Offen*, **dann** zeigt jede Bestellung die Minuten und neben der Uhrzeit eine Stufe: *Neu* (< 2 Min.), *Wartet* (2 – 5), *Verspätet* (6 – 11), *Stark verspätet* (≥ 12).

### US-43 – WhatsApp-Erinnerung für wartende Bestellungen · S
*Als* Filialleitung *möchte ich*, dass Mitarbeitende im Dienst eine WhatsApp-Erinnerung bekommen, *damit* keine Bestellung zu lange wartet.
- **Gegeben** Bestellungen warten 2 Minuten oder länger, **dann** geht alle 2 Minuten eine Nachricht raus; ab 6 Minuten heißt es *verspätet*, ab 12 Minuten *stark verspätet*.
- **Gegeben** eine Person hat nicht eingewilligt oder ist nicht im Dienst, **dann** bekommt sie keine Nachricht (DSGVO, siehe 06 §8).

### US-44 – Endbetrag schützen · M
*Als* Händler *möchte ich*, dass falsche Gewichte und Beträge gesperrt werden, *damit* einem Kunden nie zu viel abgebucht wird.
- **Gegeben** eine gewogene Zeile, **wenn** das Gewicht außerhalb von ±10 % des bestellten Gewichts liegt, **dann** wird das Feld rot und *Bereit* ist gesperrt.
- **Gegeben** der Warenwert läge über +15 % der Bestellung, **dann** ist *Bereit* gesperrt – auch mit Zustimmung des Kunden.

### US-45 – Fehlende Produkte mit Zustimmung des Kunden entfernen · M
*Als* Filialmitarbeiter *möchte ich* eine Bestellung ohne fehlende Produkte abschließen, nachdem ich mit dem Kunden gesprochen habe, *damit* er den Rest trotzdem bekommt.
- **Gegeben** ein Produkt fehlt oder der Betrag sinkt um mehr als 15 %, **dann** braucht *Bereit* den Haken *„Kunde informiert und einverstanden“*; die Uhrzeit wird mit der Bestellung gespeichert.

### US-46 – Bestellungen über der Kurierkapazität teilen · M
*Als* Filialmitarbeiter *möchte ich*, dass Bestellungen über 20 Liter automatisch geteilt werden, *damit* jeder Kurier seinen Teil tragen kann.
- **Gegeben** eine Bestellung über 20 L, **dann** wird sie in möglichst wenige Teile geteilt, jeder Teil ≤ 20 L, Liter gleichmäßig verteilt, jedes Produkt nur in einem Teil.
- **Gegeben** eine geteilte Bestellung, **dann** behält jeder Teil die Bestellnummer mit *„(automatisch geteilte Bestellung · Teil n)“*, wartet als eigene Zeile und wird separat kommissioniert; Berichte zählen sie als eine Bestellung.

### US-47 – Nur einige Produkte zurückgeben · M
*Als* Filialmitarbeiter *möchte ich* genau sehen, welche Produkte in welcher Menge zurückkommen, *damit* ich richtig prüfe und erstatte.
- **Gegeben** eine Retoure, **dann** ist jedes zurückgeschickte Produkt mit Foto und Menge aufgeführt; die Erstattung ist Menge × Preis der Bestellzeile.

### US-48 – Retouren mit QR-Code annehmen · M
*Als* Filialmitarbeiter *möchte ich* die Übergabe mit einem QR-Code bestätigen, *damit* Kurier und Filiale sich einig sind, was zurückgebracht wurde.
- **Gegeben** eine genehmigte Retoure, **wenn** der Kurier sie bringt, **dann** hake ich jedes Produkt ab und erstelle einen Einmal-QR-Code.
- **Gegeben** der Kurier scannt den Code (Kurier-App; vorerst jedes Handy), **dann** wird die Retoure *angenommen*.

### US-49 – Erstattung nach Annahme der Retoure · M
*Als* Kunde *möchte ich* mein Geld zurück, sobald die Filiale meine Produkte hat, *damit* ich nicht warten muss.
- **Gegeben** eine Retoure ist angenommen, **dann** geht die Erstattung über den Kanal auf die Karte des Kunden und wird mit Uhrzeit an der Retoure angezeigt.

### US-50 – Gründe sehen und filtern · S
*Als* Filialmitarbeiter *möchte ich* den Grund jeder Retoure und Stornierung in der Liste sehen, *damit* ich Probleme schnell verstehe.
- **Gegeben** die Retourenliste, **dann** zeigt jede Retoure ihren Grund und die Notiz des Kunden; ein Tipp auf den Grund öffnet die Bestellung, die Grund-Chips filtern die Liste.
- **Gegeben** der Reiter *Storniert*, **dann** zeigt jede Bestellung ihren Grund und ob sie für die Filialbewertung zählt.

### US-51 – Kundenbewertungen sehen · S
*Als* Händler *möchte ich* die Bewertungen der Kanäle sehen, *damit* ich weiß, wie Kunden Service, Geschmack und Lieferung beurteilen.
- **Gegeben** die Startseite, **dann** zeigt jeder Kanal den Durchschnitt und die Zahl der Bewertungen; ein Tipp öffnet die Bewertungen.
- **Gegeben** die Bewertungsseite, **dann** sehe ich Durchschnitt, Sternverteilung, die drei Kriterien und die neuesten Bewertungen mit gefüllten Sternkästen.

### US-52 – Verkaufsbericht pro Tag, Woche, Monat, Jahr · S
*Als* Händler *möchte ich* den Verkaufsbericht zwischen Zeiträumen wechseln, *damit* ich kurz- und langfristige Trends sehe.
- **Gegeben** ein Zeitraum-Reiter, **dann** zeigen Kästen, Diagramm, Detailtabelle und Meistverkauftes diesen Zeitraum; ein Datums-Chip benennt ihn.
- **Gegeben** die Tagesansicht, **dann** öffnen die Kästen *Bestellungen, Umsatz, Stornierungen, Retouren* die Liste hinter der Zahl; die Zahlen von heute entsprechen der Startseite.

### US-53 – Tag- und Nachtmodus · C
*Als* Nutzer *möchte ich* zwischen heller und dunkler Darstellung wechseln, *damit* der Bildschirm in Nachtschichten angenehm ist.
- **Gegeben** der Sonne-/Mond-Button, **dann** wechseln alle Seiten; Statusfarben behalten ihre Bedeutung, und die Wahl wird auf dem Gerät gespeichert.
