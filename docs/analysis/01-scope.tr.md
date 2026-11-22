# 01 – Kapsam

🇬🇧 [English](01-scope.md) · 🇩🇪 [Deutsch](01-scope.de.md) · 🇹🇷 **Türkçe**

## 1. Problem
Bir market iki teslimat platformu üzerinden satış yapıyor: **HTGO Market** ve **GK Market**. Her platformun kendi satıcı paneli var. Bu yüzden çalışanlar:
- gün boyu iki ekran arasında gidip geliyor, bazı siparişleri kaçırıyor ya da geciktiriyor,
- ürün durumunu iki kez güncelliyor ve iki kanal birbirinden kopuyor (bir ürün bir kanalda tükenmiş, diğerinde hâlâ sipariş edilebiliyor),
- ciroyu tek yerde göremiyor; yönetim iki rapordaki rakamları elle Excel'e aktarıyor.

## 2. Hedef
**VetisPilot**, iki kanal için tek bir satıcı panelidir. HTGO ve GK siparişleri tek listede toplanır, ürünler bir kez yönetilir, raporlar iki kanalı yan yana gösterir.

| # | İş hedefi | Nasıl ölçülür |
|---|---|---|
| G1 | Hiçbir sipariş kaçmasın | Her yeni sipariş 5 saniye içinde panelde görünür |
| G2 | Daha hızlı sipariş hazırlama | *Bekliyor*'dan *Hazır*'a geçen süre sipariş ve şube bazında gösterilir |
| G3 | Ürünler için tek doğru kaynak | Ürün değişikliği bir kez yapılır ve iki kanala da uygulanır |
| G4 | Ortak raporlama | Kanal, şube ve ürün bazında günlük ciro, elle Excel işi olmadan |
| G5 | Güvenilir mağazalar | Mağazadan kaynaklanan iptallerin oranı şube bazında gösterilir (mağaza puanı); hedef ≤ %2 |

## 3. Paydaşlar
| Paydaş | İlgisi |
|---|---|
| **Admin** (VetisPilot'u işleten) | Satıcıları, şubeleri ve kullanıcıları yönetir; her şeyi görür |
| **Satıcı** (işletme sahibi) | Kendi şubelerinin cirosu, ürün yelpazesi ve performansı |
| **Şube çalışanı** | Kendi şubesine gelen siparişleri hızlıca hazırlar |
| **HTGO Market / GK Market** (dış sistemler) | Sipariş ve durum bilgisi gönderir; güvenilir ve güvenli bir arayüze ihtiyaç duyar |
| **Son müşteri** (dolaylı) | Doğru siparişi zamanında alır; VetisPilot'u kendisi kullanmaz |

## 4. Kapsam
### Kapsamda – MVP
- Rollerle giriş (Admin, Satıcı, Şube çalışanı)
- Arayüz **İngilizce, Almanca ve Türkçe**, kullanıcı seçebilir
- İki kanaldan güvenli bir arayüz üzerinden sipariş alma (HMAC imzası)
- Kanala, duruma ve şubeye göre filtrelenebilen sipariş listesi; sipariş durum akışı
- Ürün yönetimi: ekle, düzenle, sil, fiyat, görsel, stokta var / yok
- Şube yönetimi ve kullanıcı profili
- Dashboard: bugünkü ciro, aktif siparişler, bekleyen iadeler
- İadeler: kaydet, onayla ya da reddet
- Gerçekçi HTGO ve GK siparişleri üreten kanal simülatörü
- Önemli değişikliklerin audit log'u
- Toplama kuralları: eksik ürünü müşterinin onayıyla değiştirme, tartılı ürünleri tartma, poşet ücreti alma – son tutar kanala gönderilir
- İptal koruması ve mağaza puanı: şube ancak müşteriyle iletişime geçtikten sonra iptal edebilir (2 arama kuralı); mağazadan kaynaklanan iptaller puan olarak gösterilir
- Toplarken barkod kontrolü (el terminali ya da telefon kamerası); elle sayımlar kayda geçer
- Panele girerken kanal seçimi: HTGO Market ya da GK Market'te, o kanalın renkleriyle çalışma

### Kapsamda – MVP sonrası
- Raporlar: HTGO satışları, GK satışları, ürün raporu (dışa aktarma ile)
- Satıcılar, şubeler ve kullanıcılar için admin paneli
- Kanala özel fiyatlar ve raporlarda kanal komisyonu

### Kapsam dışı
- Müşterinin sipariş verdiği uygulama ya da web sitesi
- Ödemeler (parayı kanallar tahsil eder; VetisPilot sadece tutarları kaydeder)
- Kurye yönetimi ve canlı teslimat takibi
- Gerçek teslimat platformlarına bağlantı

## 5. Varsayımlar (22.11.2026'da onaylandı)
| # | Varsayım | Neden önemli |
|---|---|---|
| A1 | Bir satıcının bir veya daha fazla şubesi olur. Her şube iki kanaldan da sipariş alır. | Satıcının ve şube çalışanının neyi göreceğini belirler |
| A2 | Ürün kataloğu satıcıya aittir. Stok (miktar, stokta var / yok) **şube bazında** tutulur. | Aynı süt A şubesinde tükenmiş, B şubesinde mevcut olabilir |
| A3 | MVP'de bir ürünün fiyatı iki kanalda da aynıdır. | Fiyatlandırmayı basit tutar; kanal fiyatları sonra gelir |
| A4 | *Bekliyor → Toplanıyor → Hazır* adımlarını şube yapar. Siparişi kanalın kuryesi alır, bu yüzden *Yolda* ve *Teslim edildi* bilgisini kanal gönderir. | Sorumluluğu bizim sistemimizle kanal arasında paylaştırır |
| A5 | Sipariş *Hazır* olana kadar iptal edilebilir: şube tarafından (ör. ürün eksik) ya da kanal tarafından (müşteri vazgeçti). | Durum akışındaki iptal kurallarını belirler |
| A6 | İadeyi müşteri kanal üzerinden ister; şube ya da satıcı onaylar veya reddeder. | İadelerle kimin ilgileneceğini belirler |
| A7 | Para birimi EUR, saat dilimi Europe/Berlin, arayüz İngilizce, Almanca ve Türkçe. Marka isimleri asla çevrilmez; ürün adları, boyutlar ve kategoriler çevrilir. | Raporları, tarih/sayı biçimlerini ve tüm ekran metinlerini etkiler |
| A8 | Eksik bir ürün ancak müşteri telefonda onay verirse değiştirilebilir. Müşteri yeni ürünün fiyatını öder; aradaki fark (ör. +0,10 €) son tutara yansır. Müşteriye 2 aramada ulaşılamazsa ürün siparişten çıkarılır ve ücretlendirilmez. | 22.11.2026'da eklendi; müşteri sürprizle karşılaşmaz |
| A9 | Tartılı ürünler sipariş edilen ağırlıktan ±%10 sapabilir (ör. 1 kg yerine 1,10 kg). Müşteri gerçek ağırlığı öder. Bu aralığın dışında önce müşterinin onayı gerekir. | 22.11.2026'da eklendi; meyve, sebze ve taze et tam gramında tartılamaz |
| A10 | Her poşet 0,25 € (şube bazında ayar). Poşet sayısı sipariş hazır olduğunda girilir ve son tutara eklenir. | 22.11.2026'da eklendi; daha az poşeti teşvik eder |
| A11 | Şube bir siparişi kendi başına iptal edemez. Önce müşteriyi araması gerekir: müşteri onay verirse sipariş iptal edilir ve bu mağaza puanını etkiler; 2 cevapsız aramadan sonra sistem siparişi *müşteriye ulaşılamadı* sebebiyle, mağaza puanını etkilemeden iptal eder. Her arama kayda geçer. | 22.11.2026'da eklendi; müşteriyi korur ve her iptali izlenebilir yapar |
| A12 | Çalışan her satırı sayar (toplanan / sipariş edilen, ör. 3'ten 2). Eksik kalan adet, eksik ürün gibi işlenir (ara, değiştir ya da çıkar). Bir sipariş ancak tüm satırlar netleşince hazır olarak işaretlenebilir. | 22.11.2026'da eklendi; mağazadan yanlış adet çıkmaz |
| A13 | Kullanıcı girişten sonra bir kanal seçer (HTGO Market ya da GK Market); panel o andan itibaren sadece o kanalda ve onun renkleriyle çalışır. Elle sayım (okutmadan +) serbesttir ama her zaman *manuel* olarak kayda geçer. | 22.11.2026'da eklendi; kanal bazında net odak, izlenebilir toplama kalitesi |

## 6. Kısıtlar
- Tek kişilik proje; önce çalışan ilk sürüm (MVP), sonra raporlar ve admin; her şey **Ocak 2027 sonuna** kadar bitecek
- Tüm markalar (kanallar, ürünler) kurgusaldır; gerçek marka adı, logo ya da renk kimliği kullanılmaz
- Tek komutla yerelde çalışır (Docker Compose); gizli bilgiler `.env` dosyasında kalır ve asla GitHub'a gitmez
