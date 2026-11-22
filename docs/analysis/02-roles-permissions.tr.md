# 02 – Roller ve Yetkiler

🇬🇧 [English](02-roles-permissions.md) · 🇩🇪 [Deutsch](02-roles-permissions.de.md) · 🇹🇷 **Türkçe**

## 1. Roller
| Rol | Kim | Kimin verisini görür |
|---|---|---|
| **Admin** | VetisPilot'u işleten | Tüm satıcılar, tüm şubeler |
| **Satıcı** | İşletme sahibi | Sadece kendi şubeleri |
| **Şube çalışanı** | Bir şubenin personeli | Sadece kendi şubesi |
| **Kanal** (sistem, kişi değil) | HTGO Market, GK Market | Sadece sipariş ve durum bilgisi arayüzü |

Bir kullanıcının tam olarak bir rolü vardır. Bir şube çalışanı tam olarak bir şubeye bağlıdır.

## 2. Yetki matrisi
**C** = oluştur (create) · **R** = görüntüle (read) · **U** = güncelle (update) · **D** = sil (delete) · **–** = erişim yok
*kendi* = sadece kullanıcının kendi şubelerinin (Satıcı) ya da kendi şubesinin (Şube çalışanı) verisi

| Alan | Admin | Satıcı | Şube çalışanı | Kanal |
|---|---|---|---|---|
| Satıcılar | CRUD | R (kendi profili) | – | – |
| Şubeler | CRUD | R, U (kendi) | R (kendi) | – |
| Kullanıcılar | CRUD | Şube çalışanları için CRUD (kendi şubeleri) | R, U (kendi profili) | – |
| Ürün kataloğu | CRUD | CRUD (kendi) | R (kendi) | – |
| Stok / stokta var bilgisi | R, U | R, U (kendi) | R, U (kendi şubesi) | – |
| Siparişler – alma | – | – | – | C |
| Siparişler – görüntüleme | R | R (kendi) | R (kendi şubesi) | – |
| Siparişler – durum *Toplanıyor / Hazır* | U | U (kendi) | U (kendi şubesi) | – |
| Siparişler – toplama: ürün değişimi, ağırlık, poşet sayısı | U | U (kendi) | U (kendi şubesi) | – |
| Siparişler – durum *Yolda / Teslim edildi* | – | – | – | U |
| Siparişler – iptal (*Hazır*'a kadar, sadece müşteriyle iletişimden sonra – R6) | U | U (kendi) | U (kendi şubesi) | U |
| İadeler – talep | – | – | – | C |
| İadeler – onay / ret | U | U (kendi) | U (kendi şubesi) | – |
| Dashboard | R (tümü) | R (kendi) | R (kendi şubesi) | – |
| Raporlar | R (tümü) | R (kendi) | – | – |
| Audit log | R | – | – | – |

## 3. Kurallar
- **R1 – En az yetki (least privilege):** Her kullanıcı sadece işi için gereken erişimi alır.
- **R2 – Veri ayrımı:** Bir satıcı, adres çubuğundaki bir ID'yi değiştirse bile başka bir satıcının verisini asla göremez. Sunucu sahipliği her istekte kontrol eder.
- **R3 – Siparişler silinmez:** Siparişler silinmez, sadece iptal edilir. Böylece raporlar ve denetim izi doğru kalır.
- **R4 – Audit:** Ürünlerde, fiyatlarda, stokta, sipariş durumunda, iadelerde ve kullanıcılarda yapılan değişiklikler audit log'a yazılır (kim, ne zaman, ne, eski değer, yeni değer).
- **R5 – Kanal erişimi:** Kanallar şifreyle giriş yapmaz. Her istek gizli bir anahtarla imzalanır (HMAC); imzasız ya da yanlış imzalı istekler reddedilir.
- **R6 – Müşteri olmadan iptal yok:** Şube ancak müşterinin onay verdiği kayıtlı bir aramadan ya da 2 cevapsız aramadan sonra iptal edebilir. Bunu veritabanı kendisi kontrol eder (trigger), yani kodda hata olsa bile kural geçerlidir.
