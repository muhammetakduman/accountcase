AccountCase — Kotlin + Spring Boot

Bu proje, Kotlin dilinin sağladığı type-safe özelliklerini Spring Boot altyapısıyla birleştiren, sade ama güçlü bir hesap yönetim sistemi (Account Management Service) örneğidir.
Amacı, modern Spring mimarisiyle birlikte Kotlin’in null-safety, data class ve concise syntax avantajlarını göstermek; aynı zamanda katmanlı bir yapıyı ve test odaklı yaklaşımı örneklemektir.

<-----------Neler yapıldı ----------->

-----------------------------------------------------------------------------------------------------------------------

Projede üç ana domain entity bulunuyor:

Account → Hesap bilgisini ve bakiyeyi temsil eder.

Customer → Kullanıcıya ait temel kimlik ve ilişki verilerini tutar.

Transaction → Hesaplar arası para transferlerini veya hareket geçmişini kaydeder.

Entity’ler Spring Data JPA ile tam ilişkisel yapıdadır.

Customer ↔ Account ilişkisi (one-to-many).

Account ↔ Transaction ilişkisi (one-to-many).

Veritabanı ilişkileri Kotlin data class’larıyla immutability ve type safety korunarak tanımlandı.

Tüm veri akışında DTO katmanı kullanıldı.

ModelMapper veya MapStruct gibi harici kütüphaneler yerine, kendi converter sınıflarımı yazdım.

Böylece dönüşümler kontrol edilebilir, sade ve Kotlin’in extension function avantajlarını kullanan bir yapıya dönüştü.

<------------TEST--------------->

-----------------------------------------------------------------------------------------------------------------------
Proje boyunca JUnit 5 ve Mockito kullanılarak integration ve unit testleri yazıldı.

Servis katmanında mock nesneler ile iş mantıkları test edildi.

Repository katmanında gerçek veritabanı üzerinden test senaryoları çalıştırıldı.

Testler; AccountService, TransactionService, ve CustomerService iş akışlarını ayrı ayrı doğruluyor.

Böylece her katmanın beklenen davranışı bağımsız olarak güvence altına alındı.

<----------GEREKSİNİMLER----------->


-----------------------------------------------------------------------------------------------------------------------

Java +17
Maven
H2
DOCKER
