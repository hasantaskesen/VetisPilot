# VetisPilot Demo Kataloğu

🇬🇧 [English](README.md) · 🇩🇪 [Deutsch](README.de.md) · 🇹🇷 **Türkçe**

VetisPilot satıcı paneli için başlangıç verisi: **100 kurgusal ürün**, **22 kurgusal marka**, **12 kategori** – İngilizce, Almanca ve Türkçe.

| Dosya | İçeriği |
|---|---|
| `products.csv` / `products.json` | 100 ürün: SKU, barkod, ad (EN / DE / TR), marka, kategori, boyut (EN / DE / TR), fiyat (EUR), adet ya da ağırlıkla satış (`sold_by`, `weight_kg`), stok |
| `categories.json` | 12 kategori ve EN / DE / TR adları |
| `brands.json` | 22 marka: renkler, EN / DE / TR sloganlar ve logo yolları |
| `logos/` | Her marka için: üç dilde geniş logo (`<marka>.svg`, `<marka>.de.svg`, `<marka>.tr.svg`) ve bir kare ikon (`<marka>-icon.svg`) |
| `catalog.html` | Tüm kataloğun EN / DE / TR dil seçicili görsel önizlemesi; önce HTGO Market ya da GK Market seçilir (sayfa o kanalın renklerini kullanır); bir markaya tıklayınca sadece onun ürünleri görünür. Otomatik üretilir, elle düzenlenmez. Veri değiştikten sonra proje ana klasöründe `java tools/BuildCatalogPage.java` ile yeniden üretilir |

Tüm marka isimleri ve logolar özgün ve kurgusaldır, bu portfolyo projesi için oluşturulmuştur.
Barkodlar mağaza içi kullanıma ayrılmış 200–299 önek aralığını kullanır, bu yüzden hiçbir gerçek ürünle çakışmaz.
Panelde gerçekçi veri görünsün diye 6 ürün bilerek stoksuz, 8 ürünün stoğu bilerek düşük tutulmuştur.

**Çeviri kuralı:** Marka isimleri asla çevrilmez (gerçek ambalajlarda olduğu gibi). Ürün adları, boyutlar, kategoriler ve logo sloganları çevrilir.
