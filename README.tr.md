# VetisPilot

🇬🇧 [English](README.md) · 🇩🇪 [Deutsch](README.de.md) · 🇹🇷 **Türkçe**

**Multi-Channel Orders** – iki teslimat kanalından gelen siparişler için tek bir satıcı paneli.

VetisPilot çok kanallı bir sipariş yönetim sistemidir. Bir market ya da restoran, iki (kurgusal) teslimat platformundan, **HTGO Market** ve **GK Market**'ten sipariş alır ve hepsini tek panelden yönetir: siparişler, ürünler, şubeler, iadeler ve satış raporları.

> 🚧 Geliştirme sürüyor – 1. aşama (analiz) tamamlandı, tıklanabilir prototip **v1.0.1** denendi, şu an **2. aşama: Veritabanı tasarımı**.

## Planlanan özellikler
- **Dashboard:** günlük ciro, aktif siparişler, bekleyen iadeler
- **Siparişler:** kanala göre filtre (HTGO / GK), durum akışı: Bekliyor → Toplanıyor → Hazır → Yolda → Teslim edildi (iptal / iade dahil)
- **Ürünler:** ekle, düzenle, sil, fiyat, fotoğraf, stokta var / yok
- **Şubeler ve kullanıcı profili**
- **Raporlar:** HTGO satış raporu, GK satış raporu, ürün raporu
- **Admin paneli:** satıcıları, şubeleri ve kullanıcıları yönetir
- **Roller:** Admin, Satıcı, Şube çalışanı
- **Diller:** arayüz İngilizce, Almanca ve Türkçe

## Dokümantasyon
- [01 – Kapsam](docs/analysis/01-scope.tr.md)
- [02 – Roller ve Yetkiler](docs/analysis/02-roles-permissions.tr.md)
- [03 – User Story'ler](docs/analysis/03-user-stories.tr.md)
- [04 – Sipariş Süreci](docs/analysis/04-order-process.tr.md)
- [05 – Ekran Listesi](docs/analysis/05-screens.tr.md)
- [06 – Tıklanabilir prototipten çıkan kararlar](docs/analysis/06-prototype-decisions.tr.md)
- [Veritabanı – Veri Modeli (ER)](docs/database/01-er-model.tr.md)
- [API 01 – Giriş](docs/api/01-login.tr.md)
- [Panelin tıklanabilir prototipi](prototype/panel.html) (v1.0.1) – giriş, uyarılı ve bölünebilen siparişler, tutar korumalı toplama, ürünler ve satış durumu, QR ile iade teslim alma, raporlar, değerlendirmeler, gündüz / gece modu (örnek veriler; dosyayı tarayıcıda aç)

## Paneldeki renklerin dili
Renkler her yerde aynı anlama gelir; böylece şube çalışanı bir listeyi tek bakışta anlar – gündüz ve gece modunda.

| Renk | Anlamı | Örnek |
|---|---|---|
| 🔵 Mavi / 🟤 kahverengi | Kanal: **HTGO Market** / **GK Market** (başlıklar, bildirimler, raporlar) | Kanalın renginde yeni sipariş bildirimi |
| 🟢 Yeşil | İyi / şimdi devam edilebilir | Aktif siparişler, satıştaki ürün, *kurye siparişi teslim almak için mağazada*, iade teslim alındı |
| 🟣 Mor | Kurye hareket halinde | *Kurye mağazaya geliyor*, *kurye teslimat adresine gidiyor* |
| 🟠 Turuncu | Yakında dikkat gerekiyor | Sipariş *gecikmede* (6+ dk), *kurye iadeyi mağazaya getiriyor* |
| 🔴 Kırmızı | Hemen harekete geç / engellendi | Sipariş *çok gecikmiş* (12+ dk), bekleyen iadeler, tutar sınırı aşıldı, iptal |
| ⚪ Gri | Henüz mümkün değil | Neden seçilmeden *İptal et*, kapalı anahtar |

## Teknoloji
| Katman | Teknoloji |
|---|---|
| Backend | Java, Spring Boot (REST API) |
| Veritabanı | PostgreSQL (Docker) |
| Frontend | React |
| Kanal simülatörü | Gerçekçi siparişler gönderen küçük, ayrı bir servis |
| Altyapı | Docker Compose |

## Yol haritası
1. Analiz – kapsam, roller, user story'ler, sipariş süreci, ekran listesi
2. Veritabanı tasarımı – ER diyagramı, Docker'da PostgreSQL
3. Backend – giriş, ürünler, şubeler, siparişler, iadeler
4. Kanal simülatörü – HTGO ve GK sipariş üreticisi
5. Frontend – giriş, dashboard, siparişler, ürünler, raporlar
6. Raporlar ve admin paneli
7. Toparlama – Docker Compose, ekran görüntüleri, mimari, güvenlik

## Güvenlik
Planlanan: BCrypt ile şifre hash'leme, JWT ile giriş, rol bazlı erişim, HMAC imzalı kanal siparişleri, girdi doğrulama, giriş denemesi sınırı, gizli bilgiler `.env` dosyasında, audit log. Ayrıntılar eklenecek.

---
*HTGO Market ve GK Market bu proje için oluşturulmuş kurgusal markalardır.*
