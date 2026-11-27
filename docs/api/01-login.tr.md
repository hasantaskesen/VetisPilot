# API 01 – Giriş

🇬🇧 [English](01-login.md) · 🇩🇪 [Deutsch](01-login.de.md) · 🇹🇷 **Türkçe**

Backend'in gerçek veritabanıyla çalışan ilk parçası. [US-01 (giriş yapmak), US-02 (şifre deneme saldırısına karşı koruma) ve US-22 (pasif hesaplar)](../analysis/03-user-stories.tr.md) hikâyelerini karşılar. Kod: [`backend/src/main/java/com/vetispilot/auth`](../../backend/src/main/java/com/vetispilot/auth).

## 1. Uç noktalar (endpoint)
| Metot | Yol | Giriş gerekli mi | Ne yapar |
|---|---|---|---|
| POST | `/api/v1/auth/login` | hayır | e-posta + şifre girer → token + kullanıcı çıkar |
| GET | `/api/v1/auth/me` | evet | giriş yapmış kullanıcıyı döner |
| GET | `/api/v1/system/info` | hayır | ad, sürüm, diller, kanallar |
| GET | `/actuator/health` | hayır | backend çalışıyorsa `{"status":"UP"}` |

Diğer bütün yollar geçerli bir token olmadan **401** döner. Token `Authorization: Bearer <token>` başlığıyla gönderilir.

**Örnek**
```http
POST /api/v1/auth/login
{"email": "nadia.brenner@lindenkorb.example", "password": "Nadia#2026"}

200 OK
{"token": "eyJ…", "expiresAt": "2026-11-28T06:08:07Z",
 "user": {"id": 2, "fullName": "Nadia Brenner", "role": "BRANCH_EMPLOYEE", "branchId": 2, "language": "tr", "mustChangePassword": false}}
```

## 2. Kurallar
| Durum | Cevap | Denetim kaydı |
|---|---|---|
| doğru e-posta ve şifre | 200 + **8 saat** geçerli token | `LOGIN_SUCCESS` |
| yanlış şifre **ya da** bilinmeyen e-posta | 401 *"Email or password is incorrect."* – aynı metin, böylece kimse hangi hesapların var olduğunu öğrenemez | `LOGIN_FAILED` |
| üst üste 5. yanlış şifre | 429 *"Too many failed attempts …"*, hesap **15 dakika kilitlenir** | `ACCOUNT_LOCKED` |
| kilitliyken giriş (doğru şifreyle bile) | 429 | `LOGIN_WHILE_LOCKED` |
| pasif hesap | 401, yanlış şifreyle aynı metin | `LOGIN_FAILED` |
| boş alan | 400 *"Please fill in all required fields."* | – |

- Başarılı giriş yanlış şifre sayacını sıfırlar.
- Bilinmeyen e-postada sunucu yine de sahte bir şifre kontrol eder; böylece cevap, gerçek bir hesaptaki kadar sürer.
- Basitleştirme: sayaç sadece 15 dakika içindekileri değil, **üst üste** gelen yanlış şifreleri sayar (US-02). Zaman penceresi sonra eklenebilir.

## 3. Güvenlik kararları
| Karar | Neden |
|---|---|
| Şifreler sadece **BCrypt özeti** olarak saklanır | veritabanının kopyası ele geçse bile şifreler okunamaz |
| Sunucu oturumu yerine **JWT** (imzalı token) | backend durum tutmaz; anahtar olmadan token değiştirilemez |
| İmza anahtarı `JWT_SECRET` `.env` içinde | gizli bilgiler asla GitHub'a gitmez; anahtar 32 karakterden kısaysa backend açılmaz |
| Giriş denemeleri `audit_log`'a yazılır | tablo sadece INSERT kabul eder (V1 trigger'ı), denemeler gizlenemez |

## 4. Demo hesapları
[`db/demo/V3__demo_data.sql`](../../backend/src/main/resources/db/demo/V3__demo_data.sql) migration'ı *Lindenkorb · Berlin-Spandau* şubesini, HTGO Market ve GK Market kanallarını ve prototipin dört çalışanını ekler (Nadia, Lukas, Mira, Tobias – şifreler prototipin giriş sayfasındaki gibi). Dosya `db/migration` değil `db/demo` klasöründe: gerçek bir kurulum `FLYWAY_LOCATIONS=classpath:db/migration` ayarlar ve demo verisi olmadan başlar.

## 5. Testler
- `AuthServiceTest` – yukarıdaki kuralların veritabanı olmadan 8 testi (sabit saat).
- `AuthControllerTest` – web katmanının 7 testi: durum kodları, JSON, token'lı ve token'sız `/me`.
- Gerçek PostgreSQL veritabanında elle denendi: doğru şifre, bilinmeyen e-posta, `/me`, 5 yanlış şifre → kilit, denetim kaydı satırları.
