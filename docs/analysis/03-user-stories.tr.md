# 03 – User Story'ler

🇬🇧 [English](03-user-stories.md) · 🇩🇪 [Deutsch](03-user-stories.de.md) · 🇹🇷 **Türkçe**

## Bu belge nasıl okunur
- **Format:** Bir &lt;rol&gt; *olarak* &lt;fonksiyon&gt; *istiyorum*, *böylece* &lt;fayda&gt;.
- **Kabul kriterleri** *Verilen / Ne zaman / O zaman* formatındadır. Bir story ancak tüm kriterleri sağlandığında bitmiş sayılır.
- **Öncelik (MoSCoW):** **M** = Must, olmazsa olmaz (MVP) · **S** = Should, olmalı (MVP sonrası) · **C** = Could, olabilir (zaman kalırsa)
- **Hedef**, her story'yi [01 – Kapsam](01-scope.tr.md) belgesindeki iş hedeflerine (G1–G4) bağlar. **–** = destekleyici story (güvenlik, altyapı).

## Genel bakış
| ID | Epic | Story (kısa) | Rol | Öncelik | Hedef |
|---|---|---|---|---|---|
| US-01 | Erişim | Giriş yapmak | Tüm kullanıcılar | M | – |
| US-02 | Erişim | Kaba kuvvet (brute force) giriş denemelerini engellemek | Tüm kullanıcılar | M | – |
| US-03 | Erişim | Dili EN / DE / TR olarak değiştirmek | Tüm kullanıcılar | M | – |
| US-04 | Erişim | Sadece kendi verimi görmek | Satıcı, Şube çalışanı | M | – |
| US-05 | Siparişler | Kanaldan imzalı sipariş almak | Kanal | M | G1 |
| US-06 | Siparişler | Yeni siparişleri anında görmek | Şube çalışanı | M | G1 |
| US-07 | Siparişler | Durum sekmeli ve kanal seçimli sipariş listesi | Şube çalışanı, Satıcı | M | G1 |
| US-08 | Siparişler | Sipariş detayını açmak | Şube çalışanı | M | G2 |
| US-09 | Siparişler | Siparişi toplamaya başlamak | Şube çalışanı | M | G2 |
| US-10 | Siparişler | Siparişi hazır olarak işaretlemek | Şube çalışanı | M | G2 |
| US-11 | Siparişler | Kanaldan *Yolda* / *Teslim edildi* bilgisi almak | Kanal | M | G2 |
| US-12 | Siparişler | Siparişi iptal etmek | Şube çalışanı, Kanal | M | G1 |
| US-13 | Ürünler | Ürün eklemek | Satıcı | M | G3 |
| US-14 | Ürünler | Fiyat, görsel ve bilgileri düzenlemek | Satıcı | M | G3 |
| US-15 | Ürünler | Ürünü kaldırmak | Satıcı | M | G3 |
| US-16 | Ürünler | Ürünü kendi şubemde stokta yok yapmak | Şube çalışanı | M | G3 |
| US-17 | Ürünler | Ürün aramak ve filtrelemek | Satıcı, Şube çalışanı | M | G3 |
| US-18 | İadeler | Kanaldan iade talebi almak | Kanal | M | G4 |
| US-19 | İadeler | İadeyi onaylamak ya da reddetmek | Şube çalışanı, Satıcı | M | G4 |
| US-20 | Dashboard | Günün temel rakamlarını görmek | Tüm kullanıcılar | M | G1, G4 |
| US-21 | Şubeler ve kullanıcılar | Şube bilgilerini yönetmek | Satıcı | M | – |
| US-22 | Şubeler ve kullanıcılar | Şube çalışanlarını yönetmek | Satıcı | M | – |
| US-23 | Şubeler ve kullanıcılar | Profilimi ve şifremi düzenlemek | Tüm kullanıcılar | M | – |
| US-24 | Admin | Satıcıları ve şubeleri yönetmek | Admin | S | – |
| US-25 | Admin | Audit log'u görüntülemek | Admin | M | – |
| US-26 | Raporlar | Kanal bazında satış raporu | Satıcı, Admin | S | G4 |
| US-27 | Raporlar | Ürün raporu | Satıcı, Admin | S | G4 |
| US-28 | Raporlar | Raporu CSV olarak dışa aktarmak | Satıcı, Admin | C | G4 |
| US-29 | Simülatör | Gerçekçi demo siparişleri üretmek | Demo sunucusu | M | – |
| US-30 | Raporlar | İptal sebepleri raporu | Satıcı, Admin | S | G4 |
| US-31 | Toplama | Eksik ürünü değiştirmek | Şube çalışanı | M | G1 |
| US-32 | Toplama | Gerçek ağırlığı girmek | Şube çalışanı | M | G4 |
| US-33 | Toplama | Poşet ücreti almak | Şube çalışanı | M | G4 |
| US-34 | Toplama | Toplarken her satırı saymak | Şube çalışanı | M | G2 |
| US-35 | Toplama | Müşteriyle yapılan her aramayı kaydetmek | Şube çalışanı | M | G5 |
| US-36 | Dashboard | Mağaza puanını görmek | Satıcı, Şube çalışanı | M | G5 |
| US-37 | Toplama | Okutulan her barkodu kontrol etmek | Şube çalışanı | M | G2 |
| US-38 | Raporlar | Toplama kalitesi raporu | Satıcı, Admin | S | G2 |
| US-39 | Erişim | Panele girerken kanalı seçmek | Şube çalışanı, Satıcı | M | G1 |
| US-40 | Ürünler | Ürünü kanal başına satışa açmak veya kapatmak | Şube çalışanı | M | G3 |
| US-41 | Siparişler | Sesli yeni sipariş uyarısı almak | Şube çalışanı | M | G1 |
| US-42 | Siparişler | Siparişlerin ne kadar beklediğini görmek | Şube çalışanı | M | G1 |
| US-43 | Siparişler | Bekleyen siparişler için WhatsApp hatırlatması | Şube müdürü | S | G1 |
| US-44 | Toplama | Son tutarı korumak | Satıcı, Şube çalışanı | M | G4 |
| US-45 | Toplama | Eksik ürünleri müşteri onayıyla çıkarmak | Şube çalışanı | M | G4 |
| US-46 | Toplama | Kurye kapasitesini aşan siparişleri bölmek | Şube çalışanı | M | G2 |
| US-47 | İadeler | Sadece bazı ürünleri iade etmek | Şube çalışanı | M | G4 |
| US-48 | İadeler | İade ürünlerini QR kodla teslim almak | Şube çalışanı, Kurye | M | G4 |
| US-49 | İadeler | İade kabul edilince para iadesi | Müşteri (kanal üzerinden) | M | G4 |
| US-50 | İadeler | Nedenleri görmek ve süzmek | Şube çalışanı | S | G4 |
| US-51 | Gösterge paneli | Müşteri değerlendirmelerini görmek | Satıcı, Şube çalışanı | S | G5 |
| US-52 | Raporlar | Günlük, haftalık, aylık, yıllık satış raporu | Satıcı | S | G4 |
| US-53 | Erişim | Gündüz ve gece modu | Tüm kullanıcılar | C | – |

---

## Epic 1 – Erişim

### US-01 – Giriş yapmak · M
Bir kullanıcı *olarak* e-posta ve şifremle giriş yapmak *istiyorum*, *böylece* verilerimle sadece ben çalışabilirim.
- **Verilen** geçerli e-posta ve şifre, **ne zaman** giriş yaparsam, **o zaman** rolüme ait dashboard'u görürüm.
- **Verilen** yanlış şifre, **ne zaman** giriş yaparsam, **o zaman** "E-posta veya şifre hatalı" mesajını görürüm (mesaj hangisinin yanlış olduğunu söylemez).
- **Verilen** 8 saat boyunca işlem yapmadım, **ne zaman** bir yere tıklarsam, **o zaman** oturumum kapanır ve giriş sayfasına yönlendirilirim.

### US-02 – Kaba kuvvet giriş denemelerini engellemek · M
Bir kullanıcı *olarak* hesabımın şifre tahminlerine karşı korunmasını *istiyorum*, *böylece* kimse çok sayıda şifre deneyerek içeri giremez.
- **Verilen** aynı hesap için 15 dakika içinde 5 başarısız giriş, **ne zaman** 6. deneme yapılırsa, **o zaman** hesap 15 dakika kilitlenir ve deneme audit log'a yazılır.
- **Verilen** kilitli bir hesap, **ne zaman** kilit süresi biterse, **o zaman** normal şekilde tekrar giriş yapabilirim.

### US-03 – Dili EN / DE / TR olarak değiştirmek · M
Bir kullanıcı *olarak* arayüzü İngilizce, Almanca ve Türkçe arasında değiştirmek *istiyorum*, *böylece* en iyi bildiğim dilde çalışabilirim.
- **Verilen** giriş yaptım, **ne zaman** 🇬🇧 EN, 🇩🇪 DE ya da 🇹🇷 TR'yi seçersem, **o zaman** tüm ekran metinleri, ürün adları, kategoriler, tarih ve fiyat biçimleri o dile geçer.
- **Verilen** bir dil seçtim, **ne zaman** bir sonraki girişimi yaparsam, **o zaman** panel o dilde açılır.
- Marka isimleri asla çevrilmez.

### US-04 – Sadece kendi verimi görmek · M
Bir satıcı ya da şube çalışanı *olarak* sadece sorumlu olduğum verileri görmek *istiyorum*, *böylece* diğer satıcıların iş verileri gizli kalır.
- **Verilen** satıcıyım, **ne zaman** herhangi bir listeyi açarsam, **o zaman** sadece kendi şubelerimi, ürünlerimi, siparişlerimi ve iadelerimi görürüm.
- **Verilen** şube çalışanıyım, **ne zaman** herhangi bir listeyi açarsam, **o zaman** sadece kendi şubemin verisini görürüm.
- **Verilen** adres çubuğundaki ID'yi başka bir satıcının siparişiyle değiştirdim, **ne zaman** sayfa yüklenirse, **o zaman** "Bulunamadı" mesajı alırım ve deneme audit log'a yazılır.

## Epic 2 – Siparişler

### US-05 – Kanaldan imzalı sipariş almak · M
Bir kanal (HTGO / GK) *olarak* VetisPilot'a yeni siparişler göndermek *istiyorum*, *böylece* şube onları hazırlayabilir.
- **Verilen** geçerli HMAC imzalı bir sipariş, **ne zaman** gönderilirse, **o zaman** *Bekliyor* durumunda, kanal ve şube bilgisiyle kaydedilir ve kanal bir onay alır.
- **Verilen** imzası eksik ya da hatalı bir sipariş, **ne zaman** gönderilirse, **o zaman** reddedilir ve hiçbir şey kaydedilmez.
- **Verilen** aynı sipariş numarası iki kez gönderildi, **ne zaman** ikincisi gelirse, **o zaman** kopya kayıt oluşmaz.
- **Verilen** siparişte o şubede stokta olmayan bir ürün var, **ne zaman** sipariş gelirse, **o zaman** kaydedilir ve o ürün şube için vurgulanır.

### US-06 – Yeni siparişleri anında görmek · M
Bir şube çalışanı *olarak* yeni siparişlerin sayfayı yenilemeden görünmesini *istiyorum*, *böylece* hiçbir sipariş kaçmaz.
- **Verilen** sipariş listesi açık, **ne zaman** yeni bir sipariş gelirse, **o zaman** 5 saniye içinde listenin en üstünde "Yeni" etiketi ve bir sesle görünür.
- **Verilen** bir sipariş 3 dakikadan uzun süredir *Bekliyor* durumunda, **ne zaman** listeye bakarsam, **o zaman** kırmızıyla vurgulanmış olur.

### US-07 – Durum sekmeli ve kanal seçimli sipariş listesi · M
Bir şube çalışanı ya da satıcı *olarak* sipariş listesinin, iki kanal için de, canlı sayaçlı durum sekmelerine ayrılmasını *istiyorum*, *böylece* ne yapılması gerektiğini tek bakışta görürüm.
- **Verilen** "Siparişler"e girdim, **o zaman** **Bekliyor (n)**, **Toplanıyor (n)**, **Hazır (n)**, **Tümü (n)** ve **İptal (n)** sekmelerini görürüm; *n* o sekmedeki sipariş sayısıdır. Varsayılan olarak *Bekliyor* sekmesi açılır.
- **Verilen** panele girerken seçilen kanal (US-39), **o zaman** liste ve tüm sekme sayaçları sadece o kanalın siparişlerini gösterir. Her sipariş satırında kanal etiketi görünür.
- **Verilen** yeni bir sipariş ya da durum değişikliği, **o zaman** sekme sayaçları sayfa yenilenmeden 5 saniye içinde güncellenir.
- **Verilen** *Tümü* sekmesi, **o zaman** *Yolda* ve *Teslim edildi* dahil tüm siparişleri içerir; duruma, şubeye (sadece satıcı) ve tarihe göre filtrelenebilir.
- **Verilen** *İptal* sekmesi, **o zaman** her satırda ayrıca iptal sebebi ve iptal eden (şube ya da kanal) görünür.
- **Verilen** bir sipariş numarası yazdım, **ne zaman** ararsam, **o zaman** hangi sekme açık olursa olsun o sipariş gösterilir.

### US-08 – Sipariş detayını açmak · M
Bir şube çalışanı *olarak* bir siparişin tüm detaylarını görmek *istiyorum*, *böylece* doğru ürünleri toplarım.
- **Verilen** bir sipariş, **ne zaman** açarsam, **o zaman** kanalı, sipariş numarasını, saati, ürünleri (ad, boyut, adet, fiyat), toplamı, müşteri notunu ve durum geçmişini (kim, neyi, ne zaman değiştirdi) görürüm.

### US-09 – Siparişi toplamaya başlamak · M
Bir şube çalışanı *olarak* siparişi *Bekliyor*'dan *Toplanıyor*'a almak *istiyorum*, *böylece* herkes siparişle ilgilenildiğini görür.
- **Verilen** *Bekliyor* durumunda bir sipariş, **ne zaman** "Toplamaya başla"ya tıklarsam, **o zaman** durum *Toplanıyor* olur, adım ve saat kaydedilir, kanal bilgilendirilir.
- **Verilen** başka bir durumdaki sipariş, **o zaman** bu düğme görünmez.

### US-10 – Siparişi hazır olarak işaretlemek · M
Bir şube çalışanı *olarak* siparişi *Hazır* olarak işaretlemek *istiyorum*, *böylece* kanalın kuryesi onu teslim alabilir.
- **Verilen** *Toplanıyor* durumunda bir sipariş, **ne zaman** "Hazır"a tıklarsam, **o zaman** durum *Hazır* olur, hazırlama süresi (*Bekliyor* → *Hazır*) kaydedilir ve kanal bilgilendirilir.

### US-11 – Kanaldan *Yolda* / *Teslim edildi* bilgisi almak · M
Bir kanal *olarak* kuryenin siparişi ne zaman aldığını ve teslim ettiğini bildirmek *istiyorum*, *böylece* şube siparişin tüm yaşam döngüsünü görür.
- **Verilen** *Hazır* durumunda bir sipariş, **ne zaman** kanal imzalı bir "teslim alındı" mesajı gönderirse, **o zaman** durum *Yolda* olur.
- **Verilen** *Yolda* durumunda bir sipariş, **ne zaman** kanal "teslim edildi" gönderirse, **o zaman** durum *Teslim edildi* olur ve sipariş ciroya sayılır.
- **Verilen** mevcut duruma uymayan bir durum mesajı (ör. *Bekliyor* durumundaki bir sipariş için *Teslim edildi*), **o zaman** reddedilir ve kayda geçer.

### US-12 – Siparişi iptal etmek · M
Bir şube çalışanı ya da kanal *olarak* siparişi hazır olmadan iptal etmek *istiyorum*, *böylece* müşteriler teslim edilemeyecek siparişler için ödeme yapmaz.
- **Verilen** *Bekliyor* ya da *Toplanıyor* durumunda bir sipariş, **ne zaman** şube iptal ederse, **o zaman** bir sebep seçilmesi zorunludur (ör. "ürün eksik"), durum *İptal* olur ve kanal bilgilendirilir.
- **Verilen** *Bekliyor* ya da *Toplanıyor* durumunda bir sipariş, **ne zaman** kanal iptal ederse, **o zaman** durum *İptal* olur ve şube bir bildirim görür.
- **Verilen** *Hazır* ya da sonraki bir durumdaki sipariş, **o zaman** artık iptal edilemez.
- Siparişler asla silinmez (kural R3).
- **Verilen** şube siparişi iptal etmek istiyor, **o zaman** önce bir neden seçilmelidir (hiçbiri önceden seçili gelmez) ve düğme griden kırmızıya döner. *Şube çok yoğun* hemen seçilebilir; *müşteriye ulaşılamadı* ve *ürün stokta yok* ancak 2 kayıtlı aramadan sonra (kural R6, güncellendi – bkz. [06 §4](06-prototype-decisions.tr.md)).
- **Verilen** 2 arama cevapsız kaldı, **o zaman** sistem siparişi *müşteriye ulaşılamadı* sebebiyle iptal eder ve bu iptal mağaza puanını **etkilemez** (US-36). Başka sebeplerle yapılan iptaller puanı etkiler.

## Epic 3 – Ürünler ve stok

### US-13 – Ürün eklemek · M
Bir satıcı *olarak* yeni bir ürün eklemek *istiyorum*, *böylece* iki kanalda da satılabilir.
- **Verilen** ürün formu, **ne zaman** adı (EN / DE / TR), markayı, kategoriyi, boyutu, fiyatı ve isteğe bağlı bir görseli girip kaydedersem, **o zaman** ürün tüm şubelerimde kullanılabilir olur.
- **Verilen** zorunlu bir alan boş ya da fiyat pozitif bir sayı değil, **ne zaman** kaydedersem, **o zaman** alanın yanında hata görürüm ve hiçbir şey kaydedilmez.
- **Verilen** 2 MB'tan büyük ya da JPG / PNG / WebP olmayan bir görsel, **ne zaman** yüklersem, **o zaman** bir mesajla reddedilir.

### US-14 – Fiyat, görsel ve bilgileri düzenlemek · M
Bir satıcı *olarak* bir ürünü değiştirmek *istiyorum*, *böylece* iki kanal da her zaman doğru bilgiyi gösterir.
- **Verilen** bir ürün, **ne zaman** fiyatını değiştirip kaydedersem, **o zaman** yeni fiyat iki kanaldaki yeni siparişlere uygulanır; mevcut siparişler eski fiyatı korur.
- **Verilen** herhangi bir değişiklik, **o zaman** eski ve yeni değer audit log'a yazılır.

### US-15 – Ürünü kaldırmak · M
Bir satıcı *olarak* artık satmadığım bir ürünü kaldırmak *istiyorum*, *böylece* artık sipariş edilemez.
- **Verilen** hiç sipariş edilmemiş bir ürün, **ne zaman** silersem, **o zaman** kaldırılır.
- **Verilen** geçmiş siparişlerde yer alan bir ürün, **ne zaman** silersem, **o zaman** silinmek yerine arşivlenir: ürün listesinden ve kanallardan kaybolur, ama eski siparişler ve raporlar onu göstermeye devam eder.

### US-16 – Ürünü kendi şubemde stokta yok yapmak · M
Bir şube çalışanı *olarak* bir ürünü kendi şubem için "stokta var" ile "stokta yok" arasında değiştirmek *istiyorum*, *böylece* müşteriler elimizde olmayanı sipariş edemez.
- **Verilen** bir ürün, **ne zaman** "stokta yok" yaparsam, **o zaman** 5 saniye içinde iki kanalda da sadece benim şubem için satışa kapanır.
- **Verilen** ürünün stok miktarı 0'a düştü, **o zaman** otomatik olarak "stokta yok" olur.
- **Verilen** stok miktarı 10'un altında, **o zaman** üründe "Az stok" etiketi görünür.

### US-17 – Ürün aramak ve filtrelemek · M
Bir satıcı ya da şube çalışanı *olarak* ürün aramak ve filtrelemek *istiyorum*, *böylece* çok sayıda ürün arasından birini bulabilirim.
- **Verilen** ürün listesi, **ne zaman** ada, SKU'ya ya da barkoda göre arar veya kategoriye, markaya ya da stok durumuna göre filtrelersem, **o zaman** sadece uyan ürünler gösterilir.

## Epic 4 – İadeler

### US-18 – Kanaldan iade talebi almak · M
Bir kanal *olarak* müşterinin iade talebini göndermek *istiyorum*, *böylece* şube karar verebilir.
- **Verilen** teslim edilmiş bir sipariş, **ne zaman** kanal ürünleri ve sebebiyle birlikte imzalı bir iade talebi gönderirse, **o zaman** *Talep edildi* durumunda bir iade oluşur ve dashboard'da "bekleyen iade" olarak görünür.
- **Verilen** *Teslim edildi* durumunda olmayan bir sipariş, **o zaman** talep reddedilir.

### US-19 – İadeyi onaylamak ya da reddetmek · M
Bir şube çalışanı ya da satıcı *olarak* bir iadeyi onaylamak ya da reddetmek *istiyorum*, *böylece* müşteri net bir cevap alır.
- **Verilen** *Talep edildi* durumunda bir iade, **ne zaman** onaylarsam, **o zaman** durum *Onaylandı* olur, tutar cirodan düşülür ve kanal bilgilendirilir.
- **Verilen** *Talep edildi* durumunda bir iade, **ne zaman** reddedersem, **o zaman** sebep zorunludur, durum *Reddedildi* olur ve kanal bilgilendirilir.

## Epic 5 – Dashboard

### US-20 – Günün temel rakamlarını görmek · M
Bir kullanıcı *olarak* günün en önemli rakamlarını gösteren bir dashboard *istiyorum*, *böylece* durumu tek bakışta görürüm.
- **Verilen** dashboard'u açtım, **o zaman** kendi kapsamım için (tümü / şubelerim / şubem) şunları görürüm: bugünkü ciro (toplam ve kanal bazında), duruma göre aktif sipariş sayısı, bekleyen iadeler, stokta olmayan ve stoğu azalan ürünler.
- **Verilen** yeni bir sipariş ya da durum değişikliği, **o zaman** rakamlar sayfa yenilenmeden güncellenir.

## Epic 6 – Şubeler ve kullanıcılar

### US-21 – Şube bilgilerini yönetmek · M
Bir satıcı *olarak* şube bilgilerimi (ad, adres, çalışma saatleri, açık / kapalı) düzenlemek *istiyorum*, *böylece* kanallar sadece şube açıkken sipariş gönderir.
- **Verilen** bir şubeyi "kapalı" yaptım, **o zaman** kanallar o şubeye gönderdikleri yeni siparişler için ret cevabı alır.

### US-22 – Şube çalışanlarını yönetmek · M
Bir satıcı *olarak* şubelerim için çalışan hesapları oluşturmak, düzenlemek ve pasifleştirmek *istiyorum*, *böylece* personelim paneli kullanabilir.
- **Verilen** ad, e-posta ve şubeyle bir çalışan oluşturdum, **o zaman** hesaba ilk girişte değiştirilmesi zorunlu olan geçici bir şifre verilir.
- **Verilen** bir çalışanı pasifleştirdim, **o zaman** o kişi artık giriş yapamaz, ama geçmiş işlemleri audit log'da kalır.

### US-23 – Profilimi ve şifremi düzenlemek · M
Bir kullanıcı *olarak* adımı, dilimi ve şifremi değiştirmek *istiyorum*, *böylece* hesabım güncel ve güvenli olur.
- **Verilen** şifremi değiştiriyorum, **o zaman** yeni şifre harf ve rakam içeren en az 10 karakter olmalı ve eski şifre sorulur.

## Epic 7 – Admin

### US-24 – Satıcıları ve şubeleri yönetmek · S
Bir admin *olarak* satıcıları ve şubelerini oluşturmak, düzenlemek ve pasifleştirmek *istiyorum*, *böylece* yeni işletmeler sisteme katılabilir.
- **Verilen** bir satıcı oluşturdum, **o zaman** satıcının ilk kullanıcı hesabı geçici bir şifreyle oluşturulur.
- **Verilen** bir satıcıyı pasifleştirdim, **o zaman** satıcının tüm kullanıcıları engellenir ve şubeleri sipariş kabul etmeyi bırakır.

### US-25 – Audit log'u görüntülemek · M
Bir admin *olarak* kimin neyi ne zaman değiştirdiğini görmek *istiyorum*, *böylece* sorunları ve kötüye kullanımı takip edebilirim.
- **Verilen** audit log, **ne zaman** kullanıcıya, tarihe ya da alana göre filtrelersem, **o zaman** zamanı, kullanıcıyı, işlemi, nesneyi, eski ve yeni değeri görürüm.
- Audit kayıtları hiç kimse tarafından düzenlenemez ya da silinemez.

## Epic 8 – Raporlar

### US-26 – Kanal bazında satış raporu · S
Bir satıcı ya da admin *olarak* kanal bazında (HTGO, GK) bir satış raporu *istiyorum*, *böylece* kanalları karşılaştırabilirim.
- **Verilen** bir tarih aralığı, **o zaman** her kanal için sipariş sayısını, ciroyu, ortalama sepet tutarını, iptalleri ve iadeleri, şube bazında da görürüm.

### US-27 – Ürün raporu · S
Bir satıcı ya da admin *olarak* bir ürün raporu *istiyorum*, *böylece* hangi ürünlerin iyi ya da kötü sattığını bilirim.
- **Verilen** bir tarih aralığı, **o zaman** her ürün için satılan miktarı, ciroyu, kanal paylarını ve kaç kez stokta olmadığını görürüm.

### US-28 – Raporu CSV olarak dışa aktarmak · C
Bir satıcı ya da admin *olarak* bir raporu CSV olarak indirmek *istiyorum*, *böylece* Excel'de daha ayrıntılı analiz edebilirim.
- **Verilen** ekranda bir rapor, **ne zaman** "Dışa aktar"a tıklarsam, **o zaman** tam olarak ekrandaki verileri içeren bir CSV dosyası iner.

### US-30 – İptal sebepleri raporu · S
Bir satıcı ya da admin *olarak* siparişlerin neden iptal edildiğini görmek *istiyorum*, *böylece* sorunun kaynağını düzeltebilirim (ör. zayıf stok yönetimi).
- **Verilen** bir tarih aralığı, **o zaman** sebep başına iptal sayısını (ör. ürün eksik, şube yoğun, müşteri iptal etti) kanal ve şube bazında bir grafikte görürüm.
- **Verilen** bir sebebe tıkladım, **o zaman** o sebeple iptal edilen siparişlerin listesini görürüm.

## Epic 9 – Kanal simülatörü

### US-29 – Gerçekçi demo siparişleri üretmek · M
Bir demo sunucusu *olarak* HTGO ve GK gibi davranan bir simülatör *istiyorum*, *böylece* panelin canlı çalıştığını gösterebilirim.
- **Verilen** simülatör çalışıyor, **o zaman** demo kataloğundaki ürünlerle imzalı siparişleri rastgele açık şubelere ayarlanabilir bir sıklıkta (ör. 20 saniyede bir sipariş) gönderir.
- **Verilen** bir sipariş *Hazır*, **o zaman** simülatör *Yolda* ve sonra *Teslim edildi* gönderir, ara sıra da bir iptal ya da iade talebi gönderir.

## Epic 10 – Toplama ve son tutar

### US-31 – Eksik ürünü değiştirmek · M
Bir şube çalışanı *olarak* bir ürün eksik olduğunda müşteriye yerine başka bir ürün önermek *istiyorum*, *böylece* müşteri yine ihtiyacını alır ve doğru fiyatı öder.
- **Verilen** toplama sırasında bir ürün yok, **ne zaman** "Değiştir…"e tıklarsam, **o zaman** aynı kategorideki stokta olan ürünleri fiyat yakınlığına göre sıralı ve kanalın verdiği müşteri telefon numarasını görürüm.
- **Verilen** müşteriyi aradım ve onay verdi, **ne zaman** yeni ürünü seçersem, **o zaman** satırda eski ve yeni ürün görünür, yeni fiyat kullanılır ve fark gösterilir (ör. Yarım Yağlı Süt €1,19 → Tam Yağlı Süt €1,29 = **+€0,10**).
- **Verilen** müşteri kabul etmedi ya da 2 aramada ulaşılamadı (US-35), **o zaman** ürün siparişten çıkarılır ve ücretlendirilmez.
- Son tutar, çıkarılan ürünün fiyatı kadar düşer (ör. eksik €2,49'luk kruvasan paketi toplamı €2,49 azaltır).
- Her değişiklik ve müşterinin cevabı kaydedilir (kim, ne zaman) ve durum geçmişinde görünür.

### US-32 – Gerçek ağırlığı girmek · M
Bir şube çalışanı *olarak* tartılı ürünlerin gerçek ağırlığını girmek *istiyorum*, *böylece* müşteri tam olarak aldığı kadar öder.
- **Verilen** tartılı bir ürün (ör. Muz, €1,69 / kg, 1 kg sipariş edildi), **ne zaman** 1,10 kg girersem, **o zaman** satır tutarı **€1,86** olur (1,10 × 1,69, kuruşa yuvarlanır).
- **Verilen** sipariş edilen ağırlığın ±%10'u dışında bir ağırlık (ör. 1 kg yerine 1,20 kg), **o zaman** sistem yeniden tartmamı ya da önce müşterinin onayını almamı ister.
- Tartılarak satılmayan ürünlerde ağırlık alanı yoktur.

### US-33 – Poşet ücreti almak · M
Bir şube çalışanı *olarak* kaç poşet kullandığımı girmek *istiyorum*, *böylece* poşet ücreti doğru hesaplanır.
- **Verilen** *Toplanıyor* durumunda bir sipariş, **ne zaman** "Hazır olarak işaretle"ye tıklarsam, **o zaman** poşet sayısını girmem zorunludur (0–20).
- **O zaman** poşet ücreti (poşet sayısı × şubenin ücreti, varsayılan **€0,25**) eklenir; **son tutar** = tüm son satır tutarları + poşet ücreti kaydedilir ve *Hazır* durumuyla birlikte kanala gönderilir.
- **Verilen** müşteri poşet istemedi (kanal notu), **o zaman** 0 poşet önerilir.
- Satıcı poşet ücretini şube ayarlarından şube bazında değiştirebilir (S-09).

### US-34 – Toplarken her satırı saymak · M
Bir şube çalışanı *olarak* her üründen kaç adet topladığımı saymak *istiyorum*, *böylece* müşteri tam olarak sipariş ettiği adedi alır.
- **Verilen** *Toplanıyor* durumunda bir sipariş, **o zaman** her satırda "toplanan / sipariş edilen" görünür (ör. *Fizzbay Klasik Kola · 1 L — 0 / 3*), yanında **−** ve **+** düğmeleri vardır; barkod okutmak 1 adet ekler.
- **Verilen** toplanan = sipariş edilen, **o zaman** satır yeşile döner. Sayaç sipariş edilen adedin üstüne çıkamaz.
- **Verilen** toplanan < sipariş edilen (ör. 3'ten 2), **o zaman** eksik adet, eksik ürün gibi işlenir (US-31): müşteri aranır, sonra eksik kısım değiştirilir ya da çıkarılır. Son tutar sadece gerçekten toplananı sayar.
- **Verilen** en az bir satır netleşmedi, **o zaman** "Hazır olarak işaretle" pasiftir.

### US-35 – Müşteriyle yapılan her aramayı kaydetmek · M
Bir şube çalışanı *olarak* müşteriyle yapılan her aramanın kaydedilmesini *istiyorum*, *böylece* ürün çıkarmaları ve iptaller izlenebilir olur.
- **Verilen** müşteriyi siparişin içinden arıyorum, **o zaman** sonucu kaydederim: *cevap yok* ya da *ulaşıldı* ve müşterinin cevabı (değişime / çıkarmaya / iptale onay veriyor ya da kabul etmiyor).
- **Verilen** bir arama cevapsız kaldı, **o zaman** sonraki deneme en erken 2 dakika sonra yapılabilir; panel "deneme 1 / 2" gösterir.
- Arama kaydı sipariş detayında görünür ve sonradan değiştirilemez.

## Epic 11 – Mağaza puanı

### US-36 – Mağaza puanını görmek · M
Bir satıcı ya da şube çalışanı *olarak* mağazamın kaç iptale sebep olduğunu görmek *istiyorum*, *böylece* kendimizi geliştirip kanallar nezdinde iyi bir puan koruruz.
- **Verilen** dashboard, **o zaman** mağaza puanını görürüm: son 30 gündeki mağaza kaynaklı iptaller ÷ tüm siparişler (hedef ≤ %2), şube ve kanal bazında.
- Mağaza kaynaklı = şubenin *ürün eksik*, *şube yoğun* ya da *diğer* sebebiyle yaptığı iptaller. Sayılmayanlar: 2 kayıtlı aramadan sonra *müşteriye ulaşılamadı*, müşterinin kendi isteğiyle iptal ya da kanalın yaptığı iptal.
- **Verilen** şube kayıtlı bir arama olmadan iptal etmeye çalışıyor, **o zaman** sistem reddeder (kural R6).

## Epic 12 – Barkod kontrolü ve kanal seçimi

### US-37 – Okutulan her barkodu kontrol etmek · M
Bir şube çalışanı *olarak* sistemin okutulan her barkodu siparişle karşılaştırmasını *istiyorum*, *böylece* yanlış ürün paketlenmez.
- El terminaliyle (barkod klavyeden yazılmış gibi gelir) ve tarayıcıda telefon kamerasıyla çalışır.
- **Verilen** barkod siparişteki bir satıra ait, **o zaman** o satıra +1 eklenir ve kısa bir onay sesi çalar.
- **Verilen** satır zaten tamamlandı, **o zaman** "zaten tamamlandı" uyarısı çıkar ve hiçbir şey sayılmaz.
- **Verilen** ürün katalogda var ama **bu siparişte yok** (ör. tam yağlı yerine yarım yağlı süt), **o zaman** hata sesi ve kırmızı "Bu ürün siparişte yok" mesajı çıkar, hiçbir şey sayılmaz.
- **Verilen** yedek bir ürün okutuldu, **o zaman** sadece müşteri bu değişime onay verdiyse sayılır (US-31).
- **Verilen** barkod tanınmıyor, **o zaman** "Bilinmeyen barkod" çıkar ve hiçbir şey sayılmaz.
- **Verilen** tartılı bir ürün okutuldu, **o zaman** satırı seçilir ve ağırlığın girilmesi istenir (US-32).
- **Verilen** barkod bozuk, **o zaman** **+** ile elle sayabilirim; her elle sayım *manuel* olarak kaydedilir.
- Her okutma ve her elle sayım kaydedilir (kim, ne zaman, sonuç).

### US-38 – Toplama kalitesi raporu · S
Bir satıcı ya da admin *olarak* elle sayımları ve yanlış okutmaları görmek *istiyorum*, *böylece* eğitim ihtiyacını ve bozuk barkodları bulurum.
- **Verilen** bir tarih aralığı, **o zaman** şube ve çalışan bazında okutma sayısını, elle sayımları (adet ve % payı) ve yanlış okutmaları (siparişte yok / bilinmiyor) görürüm.
- Liste en çok elle sayım yapandan başlayarak sıralanır. Bir satıra tıklayınca en sık elle sayılan ürünler görünür – bozuk barkodlar için bir ipucu.

### US-39 – Panele girerken kanalı seçmek · M
Bir şube çalışanı ya da satıcı *olarak* panele girerken HTGO Market ya da GK Market'i seçmek *istiyorum*, *böylece* her seferinde tek bir kanalda çalışırım ve bir siparişin nereden geldiğini hep bilirim.
- **Verilen** giriş yaptım, **o zaman** yan yana iki kart görürüm: mavi **HTGO Market** ve kahverengi **GK Market**; her birinde logosu ve bekleyen sipariş sayısı vardır.
- **Verilen** bir kanal seçtim, **o zaman** panel o kanalın renklerini ve logosunu kullanır; siparişler, sekmeler ve dashboard sadece o kanalı gösterir.
- **Verilen** diğer kanala geçmek istiyorum, **o zaman** üst çubuktaki "Kanal değiştir" beni seçim ekranına geri götürür; son seçim hatırlanır.
- **Verilen** bakmadığım kanala yeni bir sipariş geldi, **o zaman** o kanalın adı ve rengiyle bir bildirim çıkar, böylece sipariş kaçmaz (G1).
- Raporlar (S-12 – S-14, S-18) iki kanalı yine yan yana karşılaştırabilir.

## Epic 13 – Tıklanabilir prototipten çıkan bulgular
Bu hikâyeler prototip (v1.0.1) denenirken ortaya çıktı. Arka plan, örnekler ve gerekçeler: [06 – Prototip kararları](06-prototype-decisions.tr.md).

### US-40 – Ürünü kanal başına satışa açmak veya kapatmak · M
*Bir* şube çalışanı *olarak* bir ürünü HTGO Market ve GK Market'te ayrı ayrı satışa açıp kapatmak *istiyorum*, *böylece* müşteriler sadece gerçekten teslim edebileceğimiz ürünleri görür.
- **Verilen** bir ürün, **o zaman** her kanal için bir anahtar görürüm; kapatınca o kanalın müşterileri ürünü görmez.
- **Verilen** stok 0, **o zaman** ürün iki kanalda da otomatik kapanır ve anahtar açılamaz.

### US-41 – Sesli yeni sipariş uyarısı almak · M
*Bir* şube çalışanı *olarak* sipariş gelince ses ve üst bildirim *istiyorum*, *böylece* ekrana bakmıyorken de fark ederim.
- **Verilen** yeni bir sipariş, **o zaman** kanalın renginde ve logosuyla 3 saniyelik bir bildirim *"Yeni sipariş var · kanal"*, sipariş numarasını ve bekleyen sipariş sayısını gösterir.
- **Verilen** HTGO ya da GK, **o zaman** her kanalın kendi sesi vardır. Mobil uygulamada bu, telefon kilitliyken de gelen anlık bildirimdir.

### US-42 – Siparişlerin ne kadar beklediğini görmek · M
*Bir* şube çalışanı *olarak* bekleyen siparişleri bekleme süresine göre etiketli görmek *istiyorum*, *böylece* en eskisini önce kabul ederim.
- **Verilen** *Bekliyor* sekmesi, **o zaman** her sipariş dakikayı ve saatin yanında bir etiket gösterir: *Yeni* (< 2 dk), *Kabul bekliyor* (2 – 5), *Gecikmede* (6 – 11), *Çok gecikmiş* (≥ 12).

### US-43 – Bekleyen siparişler için WhatsApp hatırlatması · S
*Bir* şube müdürü *olarak* vardiyadaki personele WhatsApp hatırlatması gitmesini *istiyorum*, *böylece* hiçbir sipariş fazla beklemez.
- **Verilen** siparişler 2 dakika ya da daha fazla bekliyor, **o zaman** her 2 dakikada bir mesaj gider; 6 dakikadan sonra *gecikmede*, 12 dakikadan sonra *çok gecikmiş* yazar.
- **Verilen** bir çalışan izin vermedi ya da vardiyada değil, **o zaman** ona mesaj gitmez (DSGVO, bkz. 06 §8).

### US-44 – Son tutarı korumak · M
*Bir* satıcı *olarak* yanlış ağırlık ve tutarların engellenmesini *istiyorum*, *böylece* müşteriden asla fazla para çekilmez.
- **Verilen** tartılı bir satır, **ne zaman** ağırlık sipariş edilenin ±%10'u dışında, **o zaman** kutu kırmızı olur ve *Hazır* engellenir.
- **Verilen** ürünler siparişten +%15'ten fazla tutacak, **o zaman** *Hazır* engellenir – müşteri onayıyla da.

### US-45 – Eksik ürünleri müşteri onayıyla çıkarmak · M
*Bir* şube çalışanı *olarak* müşteriyle konuştuktan sonra siparişi eksik ürünler olmadan tamamlamak *istiyorum*, *böylece* müşteri geri kalanını yine alır.
- **Verilen** bir ürün eksik ya da tutar %15'ten fazla düşüyor, **o zaman** *Hazır* için *"müşteri bilgilendirildi ve onayladı"* işareti gerekir; saat siparişle birlikte kaydedilir.

### US-46 – Kurye kapasitesini aşan siparişleri bölmek · M
*Bir* şube çalışanı *olarak* 20 litreyi aşan siparişlerin otomatik bölünmesini *istiyorum*, *böylece* her kurye kendi kısmını taşıyabilir.
- **Verilen** 20 L'yi aşan bir sipariş, **o zaman** olabildiğince az kısma bölünür; her kısım ≤ 20 L, litreler eşit paylaşılır, her ürün tek kısımda olur.
- **Verilen** bölünmüş bir sipariş, **o zaman** her kısım sipariş numarasını *"(otomatik bölünmüş sipariş · n. kısım)"* notuyla korur, ayrı satırda bekler ve ayrı toplanır; raporlar onu tek sipariş sayar.

### US-47 – Sadece bazı ürünleri iade etmek · M
*Bir* şube çalışanı *olarak* hangi üründen kaç tane iade edildiğini tam görmek *istiyorum*, *böylece* doğru kontrol eder ve doğru tutarı iade ederim.
- **Verilen** bir iade, **o zaman** iade edilen her ürün fotoğrafı ve adediyle listelenir; iade tutarı adet × sipariş satırındaki fiyattır.

### US-48 – İade ürünlerini QR kodla teslim almak · M
*Bir* şube çalışanı *olarak* teslimi QR kodla onaylamak *istiyorum*, *böylece* kurye ve mağaza neyin geri getirildiği konusunda anlaşır.
- **Verilen** onaylanmış bir iade, **ne zaman** kurye getirir, **o zaman** her ürünü işaretler ve tek kullanımlık bir QR kod oluştururum.
- **Verilen** kurye kodu okutur (kurye uygulaması; şimdilik herhangi bir telefon), **o zaman** iade *kabul edildi* olur.

### US-49 – İade kabul edilince para iadesi · M
*Bir* müşteri *olarak* mağaza ürünlerimi alır almaz paramı geri *istiyorum*, *böylece* beklemem.
- **Verilen** iade kabul edildi, **o zaman** para iadesi kanal üzerinden müşterinin kartına gönderilir ve iadede saatiyle görünür.

### US-50 – Nedenleri görmek ve süzmek · S
*Bir* şube çalışanı *olarak* her iadenin ve iptalin nedenini listede görmek *istiyorum*, *böylece* sorunları hızlı anlarım.
- **Verilen** iade listesi, **o zaman** her iade nedenini ve müşterinin notunu gösterir; nedene basınca sipariş açılır, neden düğmeleri listeyi süzer.
- **Verilen** *İptal* sekmesi, **o zaman** her sipariş nedenini ve mağaza puanını etkileyip etkilemediğini gösterir.

### US-51 – Müşteri değerlendirmelerini görmek · S
*Bir* satıcı *olarak* kanalların değerlendirmelerini görmek *istiyorum*, *böylece* müşterilerin servis, lezzet ve teslimatı nasıl bulduğunu bilirim.
- **Verilen** ana sayfa, **o zaman** her kanal ortalamasını ve yorum sayısını gösterir; basınca değerlendirmeler açılır.
- **Verilen** değerlendirme sayfası, **o zaman** ortalamayı, yıldız dağılımını, üç kriteri ve dolu yıldız kutularıyla son yorumları görürüm.

### US-52 – Günlük, haftalık, aylık, yıllık satış raporu · S
*Bir* satıcı *olarak* satış raporunu dönemler arasında değiştirmek *istiyorum*, *böylece* kısa ve uzun vadeli eğilimleri görürüm.
- **Verilen** bir dönem sekmesi, **o zaman** kutular, grafik, ayrıntı tablosu ve en çok satanlar o dönemi gösterir; bir tarih etiketi dönemi belirtir.
- **Verilen** günlük görünüm, **o zaman** *Sipariş, Ciro, İptaller, İadeler* kutuları rakamın arkasındaki listeyi açar; bugünün rakamları ana sayfayla aynıdır.

### US-53 – Gündüz ve gece modu · C
*Bir* kullanıcı *olarak* açık ve koyu görünüm arasında geçiş yapmak *istiyorum*, *böylece* gece vardiyasında ekran göz yormaz.
- **Verilen** güneş / ay düğmesi, **o zaman** bütün sayfalar değişir; durum renklerinin anlamı korunur ve seçim cihazda hatırlanır.
