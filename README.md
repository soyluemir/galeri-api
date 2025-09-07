Araba Satış Sistemi – Spring Boot REST API
Bu proje, sektörel standartlara uygun şekilde geliştirilmiş Spring Boot tabanlı bir RESTful API’dir. Uygulama; JWT ile kimlik doğrulama ve rol bazlı yetkilendirme, merkezi exception handling yapısı, katmanlı mimari (Controller–Service–Repository), DTO kullanımı ile temiz veri transferi ve bakım kolaylığı sağlamaktadır. Ayrıca, Merkez Bankası’ndan anlık döviz kuru çekme entegrasyonu sayesinde satış işlemleri güncel kur üzerinden gerçekleştirilebilmektedir. Bu özellikleriyle birlikte proje, gerçek dünyadaki kurumsal yazılım ihtiyaçlarına uygun, ölçeklenebilir ve güvenli bir altyapı sunmaktadır.

Modern, çok katmanlı bir Spring Boot uygulaması. JWT tabanlı kimlik doğrulama, rol bazlı yetkilendirme, yenileme (refresh) token’ları ve temiz bir istisna mimarisi ile galeri–müşteri–araç–satış süreçlerini uçtan uca yönetir.

🚀 Özellikler

JWT Authentication (Access + Refresh token, AuthEntryPoint ile standart hata yanıtları)

Role-based Authorization (ADMIN, GALLERY_OWNER, USER)

Katmanlı Mimari: Controller → Service → Repository (DTO & Mapper katmanlarıyla)

Merkezi Hata Yönetimi: Global ControllerAdvice, özel exception hiyerarşisi

Adres / Müşteri / Galeri / Araç / Satış CRUD akışları

Kur (Exchange Rate): MB’den kur çekme (opsiyonel entegrasyon)

Stateless Security: SecurityConfig + JWTService

Swagger/OpenAPI desteği (varsa)

Docker (opsiyonel) PostgreSQL ile hızlı ayağa kaldırma

🧱 Teknolojiler

Java 17+ • Spring Boot (Web, Security, Validation)

Spring Data JPA • Hibernate • PostgreSQL

Lombok • MapStruct/ModelMapper (varsa)

Maven/Gradle

Swagger/OpenAPI 3 (opsiyonel)

JUnit 5 • Mockito (test)

📦 Modüller / Domain

Account (Kullanıcı) – kimlik, roller, yetkiler

Address – il/ilçe/posta kodu

Customer – kullanıcıya bağlı müşteri profili

Gallery – araçların listelendiği galeri

Car – marka/model/yıl/vites/yakıt/fiyat vb.

Sale – müşteri–araç–kur bilgisi ile satış işlemi

RefreshToken – oturum yenileme

Basit ilişki şeması:
Account(1) – (1) Customer
Gallery(1) – (N) Car
Customer(1) – (N) Sale – (1) Car
Customer(1) – (1) Address

⚙️ Gereksinimler

Java 17+

Maven 3.9+ (ya da Gradle)

PostgreSQL 14+ (veya Docker)
