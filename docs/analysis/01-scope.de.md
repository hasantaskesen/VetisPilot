# 01 – Projektumfang

🇬🇧 [English](01-scope.md) · 🇩🇪 **Deutsch** · 🇹🇷 [Türkçe](01-scope.tr.md)

## 1. Problem
Ein Lebensmittelgeschäft verkauft über zwei Lieferplattformen, **HTGO Market** und **GK Market**. Jede Plattform hat ein eigenes Händler-Panel. Deshalb müssen die Mitarbeitenden:
- den ganzen Tag zwischen zwei Bildschirmen wechseln und übersehen oder verzögern dabei Bestellungen,
- die Produktverfügbarkeit doppelt pflegen, wodurch die Kanäle auseinanderlaufen (ein Produkt ist in einem Kanal ausverkauft, im anderen aber noch bestellbar),
- auf eine gemeinsame Umsatzübersicht verzichten; das Management überträgt Zahlen aus zwei Berichten von Hand in Excel.

## 2. Ziel
**VetisPilot** ist ein Händler-Panel für beide Kanäle. Bestellungen von HTGO und GK landen in einer gemeinsamen Liste, Produkte werden nur einmal gepflegt und Berichte zeigen beide Kanäle nebeneinander.

| # | Geschäftsziel | Messgröße |
|---|---|---|
| G1 | Keine übersehenen Bestellungen | Jede neue Bestellung ist innerhalb von 5 Sekunden im Panel sichtbar |
| G2 | Schnellere Bearbeitung | Die Zeit von *Offen* bis *Bereit* wird pro Bestellung und pro Filiale angezeigt |
| G3 | Eine einzige Datenquelle für Produkte | Eine Produktänderung erfolgt einmal und gilt für beide Kanäle |
| G4 | Gemeinsames Reporting | Tagesumsatz pro Kanal, Filiale und Produkt ohne manuelle Excel-Arbeit |
| G5 | Zuverlässige Filialen | Der Anteil der von der Filiale verursachten Stornierungen wird pro Filiale angezeigt (Filialbewertung); Ziel ≤ 2 % |

## 3. Stakeholder
| Stakeholder | Interesse |
|---|---|
| **Admin** (Betreiber von VetisPilot) | Verwaltet Händler, Filialen und Benutzer; sieht alles |
| **Händler** (Geschäftsinhaber) | Umsatz, Sortiment und Leistung der eigenen Filialen |
| **Filialmitarbeiter** | Bearbeitet eingehende Bestellungen der eigenen Filiale schnell |
| **HTGO Market / GK Market** (externe Systeme) | Senden Bestellungen und Statusmeldungen; brauchen eine zuverlässige, sichere Schnittstelle |
| **Endkunde** (indirekt) | Erhält die richtige Bestellung pünktlich; nutzt VetisPilot nicht selbst |

## 4. Umfang
### Im Umfang – MVP
- Login mit Rollen (Admin, Händler, Filialmitarbeiter)
- Benutzeroberfläche auf **Englisch, Deutsch und Türkisch**, vom Benutzer umschaltbar
- Empfang von Bestellungen aus beiden Kanälen über eine gesicherte Schnittstelle (HMAC-Signatur)
- Bestellliste mit Filter nach Kanal, Status und Filiale; Statusablauf der Bestellung
- Produktverwaltung: anlegen, bearbeiten, löschen, Preis, Bild, vorrätig / nicht vorrätig
- Filialverwaltung und Benutzerprofil
- Dashboard: heutiger Umsatz, aktive Bestellungen, offene Retouren
- Retouren: erfassen, genehmigen oder ablehnen
- Kanal-Simulator, der realistische HTGO- und GK-Bestellungen erzeugt
- Audit-Log wichtiger Änderungen
- Kommissionierregeln: fehlenden Artikel mit Zustimmung des Kunden ersetzen, Gewichtsartikel wiegen, Tütengebühr berechnen – der Endbetrag wird an den Kanal gesendet
- Stornoschutz und Filialbewertung: Die Filiale kann erst nach Kontakt mit dem Kunden stornieren (2-Anrufe-Regel); von der Filiale verursachte Stornierungen werden als Bewertung angezeigt
- Barcode-Prüfung beim Kommissionieren (Handscanner oder Handykamera); manuelle Zählungen werden protokolliert
- Kanalwahl beim Betreten des Panels: Arbeit in HTGO Market oder GK Market, in den Farben des Kanals

### Im Umfang – nach dem MVP
- Berichte: HTGO-Umsatz, GK-Umsatz, Produktbericht (mit Export)
- Admin-Panel für Händler, Filialen und Benutzer
- Kanalspezifische Preise und Kanalprovision in den Berichten

### Nicht im Umfang
- Bestell-App oder Website für Kunden
- Zahlungen (die Kanäle kassieren; VetisPilot erfasst nur die Beträge)
- Kurierverwaltung und Live-Sendungsverfolgung
- Anbindung an echte Lieferplattformen

## 5. Annahmen (bestätigt am 22.11.2026)
| # | Annahme | Warum sie wichtig ist |
|---|---|---|
| A1 | Ein Händler besitzt eine oder mehrere Filialen. Jede Filiale erhält Bestellungen aus beiden Kanälen. | Legt fest, was Händler und Filialmitarbeiter sehen dürfen |
| A2 | Der Produktkatalog gehört dem Händler. Der Bestand (Menge, vorrätig / nicht vorrätig) wird **pro Filiale** geführt. | Dieselbe Milch kann in Filiale A ausverkauft und in Filiale B vorrätig sein |
| A3 | Im MVP hat ein Produkt in beiden Kanälen denselben Preis. | Hält die Preisgestaltung einfach; Kanalpreise folgen später |
| A4 | Die Filiale setzt *Offen → In Kommissionierung → Bereit*. Der Kurier des Kanals holt die Bestellung ab, daher meldet der Kanal *Unterwegs* und *Zugestellt*. | Teilt die Verantwortung zwischen unserem System und dem Kanal auf |
| A5 | Eine Bestellung kann bis zum Status *Bereit* storniert werden: von der Filiale (z. B. Artikel fehlt) oder vom Kanal (Kunde storniert). | Definiert die Stornoregeln im Statusablauf |
| A6 | Retouren beantragt der Kunde über den Kanal; Filiale oder Händler genehmigen oder lehnen sie ab. | Legt fest, wer Retouren bearbeitet |
| A7 | Währung EUR, Zeitzone Europe/Berlin, Benutzeroberfläche auf Englisch, Deutsch und Türkisch. Markennamen werden nie übersetzt; Produktnamen, Größen und Kategorien schon. | Beeinflusst Berichte, Datums-/Zahlenformate und alle Bildschirmtexte |
| A8 | Ein fehlender Artikel darf nur ersetzt werden, wenn der Kunde telefonisch zugestimmt hat. Der Kunde zahlt den Preis des Ersatzartikels; die Differenz (z. B. +0,10 €) ändert den Endbetrag. Ist der Kunde nach 2 Anrufen nicht erreichbar, wird der Artikel entfernt und nicht berechnet. | Ergänzt am 22.11.2026; keine Überraschung für den Kunden |
| A9 | Gewichtsartikel dürfen um ±10 % vom bestellten Gewicht abweichen (z. B. 1,10 kg statt 1 kg). Der Kunde zahlt das tatsächliche Gewicht. Außerhalb dieses Bereichs muss der Kunde vorher zustimmen. | Ergänzt am 22.11.2026; Obst, Gemüse und Frischfleisch lassen sich nicht exakt abwiegen |
| A10 | Jede Tüte kostet 0,25 € (Einstellung pro Filiale). Die Anzahl der Tüten wird eingegeben, wenn die Bestellung bereit ist, und zum Endbetrag addiert. | Ergänzt am 22.11.2026; fördert weniger Tüten |
| A11 | Die Filiale kann eine Bestellung nicht eigenmächtig stornieren. Sie muss zuerst den Kunden anrufen: Stimmt der Kunde zu, wird storniert und das zählt gegen die Filialbewertung; nach 2 unbeantworteten Anrufen storniert das System mit dem Grund *Kunde nicht erreichbar*, ohne die Filialbewertung zu beeinflussen. Jeder Anruf wird protokolliert. | Ergänzt am 22.11.2026; schützt Kunden und macht jede Stornierung nachvollziehbar |
| A12 | Der Mitarbeiter zählt jede Zeile (kommissioniert / bestellt, z. B. 2 von 3). Eine fehlende Menge wird wie ein fehlender Artikel behandelt (anrufen, ersetzen oder entfernen). Eine Bestellung kann erst als bereit markiert werden, wenn jede Zeile geklärt ist. | Ergänzt am 22.11.2026; keine falsche Menge verlässt die Filiale |
| A13 | Nach der Anmeldung wählt der Benutzer einen Kanal (HTGO Market oder GK Market); das Panel arbeitet dann nur in diesem Kanal und in seinen Farben. Eine manuelle Zählung (+ ohne Scan) ist erlaubt, wird aber immer als *manuell* protokolliert. | Ergänzt am 22.11.2026; klarer Fokus pro Kanal, nachvollziehbare Kommissionierqualität |

## 6. Rahmenbedingungen
- Einzelprojekt; zuerst eine lauffähige Version (MVP), danach Berichte und Admin; Fertigstellung bis **Ende Januar 2027**
- Alle Marken (Kanäle, Produkte) sind fiktiv; keine echten Markennamen, Logos oder Farbidentitäten
- Läuft lokal mit einem Befehl (Docker Compose); Geheimnisse bleiben in `.env` und gelangen nie auf GitHub
