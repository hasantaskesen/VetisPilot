# VetisPilot demo catalog

🇬🇧 **English** · 🇩🇪 [Deutsch](README.de.md) · 🇹🇷 [Türkçe](README.tr.md)

Seed data for the VetisPilot seller panel: **100 fictional products**, **22 fictional brands**, **12 categories** – in English, German and Turkish.

| File | What it is |
|---|---|
| `products.csv` / `products.json` | The 100 products: sku, barcode, name (EN / DE / TR), brand, category, size (EN / DE / TR), price (EUR), sold by piece or weight (`sold_by`, `weight_kg`), stock |
| `categories.json` | The 12 categories with their names in EN / DE / TR |
| `brands.json` | The 22 brands with colors, taglines in EN / DE / TR and logo paths |
| `logos/` | Per brand: wide logo in three languages (`<brand>.svg`, `<brand>.de.svg`, `<brand>.tr.svg`) and one square icon (`<brand>-icon.svg`) |
| `catalog.html` | Visual preview of the whole catalog with an EN / DE / TR language switch; it starts with choosing HTGO Market or GK Market (the page then uses that channel's colors); click a brand to see only its products. Generated – do not edit by hand. After changing the data, rebuild it from the project root with `java tools/BuildCatalogPage.java` |

All brand names and logos are original and fictional, created for this portfolio project.
Barcodes use the 200–299 in-store prefix range, so they never collide with real retail products.
6 products are out of stock and 8 are low on stock on purpose, so the panel has realistic data to show.

**Translation rule:** brand names are never translated (like on real packaging). Product names, sizes, categories and logo taglines are translated.
