# 05 – Ekran Listesi

🇬🇧 [English](05-screens.md) · 🇩🇪 [Deutsch](05-screens.de.md) · 🇹🇷 **Türkçe**

Bu belge, satıcı panelindeki tüm ekranları, kimin açabileceğini ve hangi user story'leri karşıladıklarını listeler ([03 – User Story'ler](03-user-stories.tr.md)). Wireframe'ler yerleşimin kaba taslağıdır, son tasarım değildir.

## 1. Her ekrandaki ortak yapı
- **Üst çubuk:** VetisPilot logosu · seçili kanal (HTGO Market ya da GK Market) ve **Kanal değiştir** · şube seçici (satıcı ve admin) · 🔔 bildirimler · ☀️ / 🌙 gündüz ve gece modu (US-53) · dil seçici 🇬🇧 EN · 🇩🇪 DE · 🇹🇷 TR · kullanıcı menüsü (profil, çıkış)
- **Yeni sipariş uyarısı:** her ekranda kanalın kendi sesi ve kanal renginde 3 saniyelik üst bildirim (US-41)
- **Yan menü:** sadece kullanıcının rolünün açabileceği bölümler (bkz. bölüm 3)
- **Canlı güncelleme:** sipariş sayaçları, dashboard rakamları ve bildirimler sayfa yenilenmeden güncellenir (US-06, US-20)
- **Kanal renkleri:** kanal seçildikten sonra (S-17) panel o kanalın renklerini kullanır – HTGO Market mavi, GK Market kahverengi

## 2. Ekranlar
| ID | Ekran | Amacı | Admin | Satıcı | Şube çalışanı | Story'ler | Öncelik |
|---|---|---|---|---|---|---|---|
| S-01 | Giriş | Giriş yapma, 5 hatalı denemede kilit | ✔ | ✔ | ✔ | US-01, US-02 | M |
| S-02 | Yeni şifre belirleme | Geçici şifreyle ilk girişte zorunlu | ✔ | ✔ | ✔ | US-22, US-23 | M |
| S-03 | Dashboard | Bugünkü ciro, aktif siparişler, bekleyen iadeler, stok uyarıları, mağaza puanı | ✔ tümü | ✔ kendi | ✔ kendi şubesi | US-20, US-36 | M |
| S-04 | Siparişler | Kanal seçimli ve durum sekmeli sipariş listesi | ✔ okuma | ✔ kendi | ✔ kendi şubesi | US-06, US-07 | M |
| S-05 | Sipariş detayı | Ürünler, müşteri notu, durum geçmişi, durum düğmeleri, iptal, ürün değişimi, ağırlık, poşet sayısı, son tutar, toplanan adet (− / +), arama kaydı | ✔ okuma | ✔ | ✔ | US-08 – US-12, US-31 – US-35 | M |
| S-06 | Ürünler | Arama, filtre ve stokta var/yok düğmeli ürün listesi | ✔ | ✔ | ✔ okuma + stok | US-16, US-17 | M |
| S-07 | Ürün formu | Ürün ekleme / düzenleme / arşivleme, EN / DE / TR adlarıyla | ✔ | ✔ | – | US-13 – US-15 | M |
| S-08 | İadeler | *Talep edildi / Onaylandı / Reddedildi* sekmeli iade talepleri, onay ya da ret | ✔ okuma | ✔ | ✔ | US-18, US-19 | M |
| S-09 | Şubeler | Şube bilgileri, çalışma saatleri, açık / kapalı, poşet ücreti | ✔ | ✔ kendi | – | US-21, US-33 | M |
| S-10 | Kullanıcılar | Şube çalışanı oluşturma, düzenleme, pasifleştirme | ✔ | ✔ kendi şubeleri | – | US-22 | M |
| S-11 | Profilim | Ad, dil, şifre | ✔ | ✔ | ✔ | US-03, US-23 | M |
| S-12 | Rapor: kanal satışları | Günlük / haftalık / aylık / yıllık sekmeler: sipariş, ciro, ortalama sepet, iptal, iade, grafik ve ayrıntı tablosu; günlük görünümde kutular rakamın arkasındaki listeyi açar | ✔ | ✔ | – | US-26, US-28, US-52 | S |
| S-13 | Rapor: ürünler | En çok ve en az satanlar, kanal payı, stoksuz kalma sayısı | ✔ | ✔ | – | US-27, US-28 | S |
| S-14 | Rapor: iptal sebepleri | Sebep başına iptaller grafikte, kanal ve şube bazında | ✔ | ✔ | – | US-30, US-28 | S |
| S-15 | Admin: satıcılar | Satıcıları ve şubelerini oluşturma, düzenleme, pasifleştirme | ✔ | – | – | US-24 | S |
| S-16 | Admin: audit log | Kim neyi ne zaman değiştirdi; sadece okuma | ✔ | – | – | US-25 | M |
| S-17 | Kanal seçimi | Girişten sonra HTGO Market ya da GK Market seçilir; kanal başına bekleyen siparişleri gösterir | ✔ | ✔ | ✔ | US-39 | M |
| S-18 | Rapor: toplama kalitesi | Şube ve çalışan bazında okutmalar, elle sayımlar ve yanlış okutmalar | ✔ | ✔ | – | US-38 | S |
| S-19 | Satış durumu | Her ürün ve kanal için bir anahtar (HTGO / GK); stok 0 ise otomatik kapanır | ✔ | ✔ | ✔ | US-40 | M |
| S-20 | Bildirimler ve WhatsApp | Hatırlatma kuralları, WhatsApp izni olan alıcılar, gönderilen hatırlatmalar, demo düğmeleri | ✔ | ✔ | ✔ okuma | US-41 – US-43 | S |
| S-21 | İade teslim alma | Gelen ürünleri işaretleme, QR kod oluşturma, kabul edilen iadeleri ve para iadelerini görme | ✔ okuma | ✔ | ✔ | US-48, US-49 | M |
| S-22 | Kurye onayı | QR kod okutulunca açılır, giriş gerekmez; kurye teslimi onaylar | – | – | – (kurye) | US-48 | M |
| S-23 | Değerlendirmeler | Ortalama, yıldız dağılımı, servis / lezzet / teslimat, son yorumlar | ✔ | ✔ | ✔ | US-51 | S |

Kanal simülatörünün (US-29) panelde ekranı yoktur; ayrı bir servis olarak çalışır ve bir ayar dosyasıyla yapılandırılır.

## 3. Role göre yan menü
| Menü | Admin | Satıcı | Şube çalışanı |
|---|---|---|---|
| Dashboard | ✔ | ✔ | ✔ |
| Siparişler | ✔ | ✔ | ✔ |
| Ürünler | ✔ | ✔ | ✔ |
| İadeler | ✔ | ✔ | ✔ |
| Şubeler | ✔ | ✔ | – |
| Kullanıcılar | ✔ | ✔ | – |
| Raporlar | ✔ | ✔ | – |
| Admin (satıcılar, audit log) | ✔ | – | – |

Menüden bir bölümü gizlemek sadece kolaylık içindir; sunucu yine de her isteği kontrol eder (kural R2).

## 4. Wireframe'ler

### S-04 – Siparişler
Liste **ve** tüm sekme sayaçları sadece girişte seçilen kanalı gösterir (S-17). Varsayılan olarak *Bekliyor* sekmesi açılır.
```
┌──────────────────────────────────────────────────────────────────────────
│ Siparişler                                     🔍 Sipariş no. ara
│
│ ◉ HTGO Market                                 [ Kanal değiştir ]
│
│ [ Bekliyor 5 ]  [ Toplanıyor 3 ]  [ Hazır 2 ]  [ Tümü 104 ]  [ İptal 4 ]
│ ────────────────────────────────────────────────────────────────────────
│ Kanal    Sipariş no.  Saat   Ürün  Tutar    Bekleme
│ HTGO     HT-10482     14:02    6   €23,40   4 dk  ◀ kırmızı, 3 dk üstü
│ GK       GK-55120     14:05    2   €4,18    1 dk
│ HTGO     HT-10483     14:06   11   €41,90   0 dk  YENİ
│                                      [ Toplamaya başla ]  her satırda
└──────────────────────────────────────────────────────────────────────────
```
| Sekme | İçeriği | Ek sütunlar |
|---|---|---|
| Bekliyor | *Bekliyor* durumundakiler | Bekleme süresi, 3 dakikadan sonra kırmızı |
| Toplanıyor | *Toplanıyor* durumundakiler | Siparişi toplayan çalışan |
| Hazır | *Hazır* durumundakiler | Hazır olalı geçen süre |
| Tümü | *Yolda* ve *Teslim edildi* dahil tüm siparişler | Durum, duruma / şubeye / tarihe göre filtre |
| İptal | *İptal* durumundakiler | İptal sebebi, iptal eden (şube / kanal) |

### S-05 – Sipariş detayı
```
┌──────────────────────────────────────────────────────────────────────────
│ ← Siparişler   HT-10482 · HTGO Market · Merkez Şube    Durum: Bekliyor
│
│ Ürünler                                     Adet   Fiyat    Toplam
│ Milvara Tam Yağlı Süt %3,5 · 1 L              2    €1,29    €2,58
│ Crumbwell Tereyağlı Kruvasan · 4 adet         1    €2,49    €2,49  ⚠
│ ...
│                                                    Toplam:  €23,40
│ Müşteri notu: "Lütfen iki kez zili çalın"
│
│ Durum geçmişi
│ 14:02  Bekliyor   HTGO Market'ten geldi
│
│ [ Toplamaya başla ]                          [ Siparişi iptal et … ]
└──────────────────────────────────────────────────────────────────────────
```
⚠ = bu şubede stokta yok. Düğmeler duruma göre değişir: *Bekliyor* → "Toplamaya başla", *Toplanıyor* → "Hazır olarak işaretle". *Hazır*'dan sonra düğme yoktur; sonraki durumları kanal belirler.
Toplama sırasında her satırda **Değiştir…** ve **Çıkar** işlemleri vardır; tartılı ürünlerde ayrıca bir **ağırlık alanı** (kg) bulunur. "Hazır olarak işaretle", **poşet sayısı** için küçük bir pencere açar ve kanala göndermeden önce sipariş tutarını, son tutarı ve farkı gösterir (US-31 – US-33).
Her satırda **toplanan / sipariş edilen** ve **−** / **+** düğmeleri görünür (barkod okutmak 1 ekler). **Müşteriyi ara** düğmesi her denemeyi ("deneme 1 / 2") ve sonucunu kaydeder. **Siparişi iptal et …** arama kuralı sağlanana kadar pasif kalır (R6).
Her okutma siparişle karşılaştırılır (US-37): siparişte olmayan bir ürün hata sesi ve kırmızı mesaj verir, sayılmaz; okutmadan **+** basmak *manuel* sayım olarak kaydedilir.

### S-03 – Dashboard
```
┌──────────────────────────────────────────────────────────────────────────
│ Dashboard · bugün
│ ┌──────────────┐ ┌──────────────┐ ┌──────────────┐ ┌──────────────┐
│ │ Ciro         │ │ HTGO         │ │ GK           │ │ Bekleyen     │
│ │ €1.284,50    │ │ €812,10      │ │ €472,40      │ │ iadeler   3  │
│ └──────────────┘ └──────────────┘ └──────────────┘ └──────────────┘
│ Aktif siparişler:  Bekliyor 5 · Toplanıyor 3 · Hazır 2
│ Bugünkü ortalama hazırlama süresi: 7 dk
│ Mağaza puanı (30 gün): mağaza kaynaklı iptal %1,4 · hedef ≤ %2
│ Stok uyarıları:  6 stokta yok · 8 az stok            [ ürünleri göster ]
└──────────────────────────────────────────────────────────────────────────
```
Her kutucuk tıklanabilir ve ilgili filtrelenmiş listeyi açar.

### S-17 – Kanal seçimi
Girişten hemen sonra görünür (US-39). Her kart kendi kanalının renklerini kullanır; seçim hatırlanır ve üst çubuktaki **Kanal değiştir** ile değiştirilebilir.
```
┌──────────────────────────────────────────────────────────────────────────
│ Kanal seçin                                             🇬🇧 EN · 🇩🇪 DE · 🇹🇷 TR
│
│ ┌───────────────────────────────┐   ┌───────────────────────────────┐
│ │  [çanta]  HTGO Market         │   │  [sepet]  GK Market           │
│ │           mavi                │   │           kahverengi          │
│ │  Bekleyen sipariş: 5          │   │  Bekleyen sipariş: 3          │
│ │             [ Gir ]           │   │             [ Gir ]           │
│ └───────────────────────────────┘   └───────────────────────────────┘
└──────────────────────────────────────────────────────────────────────────
```
