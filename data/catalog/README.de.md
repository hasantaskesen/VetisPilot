# VetisPilot Demo-Katalog

🇬🇧 [English](README.md) · 🇩🇪 **Deutsch** · 🇹🇷 [Türkçe](README.tr.md)

Stammdaten für das VetisPilot-Händler-Panel: **100 fiktive Produkte**, **22 fiktive Marken**, **12 Kategorien** – auf Englisch, Deutsch und Türkisch.

| Datei | Inhalt |
|---|---|
| `products.csv` / `products.json` | Die 100 Produkte: SKU, Barcode, Name (EN / DE / TR), Marke, Kategorie, Größe (EN / DE / TR), Preis (EUR), Verkauf nach Stück oder Gewicht (`sold_by`, `weight_kg`), Bestand |
| `categories.json` | Die 12 Kategorien mit Namen auf EN / DE / TR |
| `brands.json` | Die 22 Marken mit Farben, Slogans auf EN / DE / TR und Logo-Pfaden |
| `logos/` | Pro Marke: breites Logo in drei Sprachen (`<marke>.svg`, `<marke>.de.svg`, `<marke>.tr.svg`) und ein quadratisches Icon (`<marke>-icon.svg`) |
| `catalog.html` | Visuelle Vorschau des gesamten Katalogs mit EN / DE / TR-Sprachumschalter; sie beginnt mit der Wahl von HTGO Market oder GK Market (die Seite nutzt dann die Farben des Kanals); ein Klick auf eine Marke zeigt nur ihre Produkte. Wird generiert – nicht von Hand bearbeiten. Nach Datenänderungen im Projekt-Hauptordner neu erzeugen mit `java tools/BuildCatalogPage.java` |

Alle Markennamen und Logos sind originell und fiktiv und wurden für dieses Portfolio-Projekt erstellt.
Die Barcodes nutzen den Präfixbereich 200–299 für den internen Gebrauch und kollidieren daher nie mit echten Handelsprodukten.
6 Produkte sind absichtlich nicht vorrätig und 8 haben einen niedrigen Bestand, damit das Panel realistische Daten anzeigen kann.

**Übersetzungsregel:** Markennamen werden nie übersetzt (wie auf echten Verpackungen). Produktnamen, Größen, Kategorien und Logo-Slogans werden übersetzt.
