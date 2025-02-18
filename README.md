Payment System

Overview

Bu proje, mikroservis mimarisi kullanılarak geliştirilmiş bir ödeme sistemidir. Java 21 ve Spring Boot 3 kullanılarak inşa edilmiştir. Proje, SOLID prensiplerine uygun olarak tasarlanmış ve Clean Architecture yaklaşımı benimsenmiştir.

Tech Stack

Backend
	•	Java 21
	•	Spring Boot 3
	•	Spring JPA (Batch işlemleri desteklenmektedir)
	•	Spring Cloud
	•	Spring AOP
	•	Hibernate
	•	Feign Client
	•	Spring Gateway
	•	Spring Config Server
	•	Eureka (Service Discovery)
	•	Spring Actuator
	•	Prometheus (Monitoring)
	•	MapStruct (DTO Mapping)
	•	Lombok
	•	Spring Specification
	•	Resilience4j
 	•	Swagger

Database & Messaging
	•	MySQL (Transactional Data)
	•	Kafka (Event-Driven Communication)
	•	Redis (Caching & Performance Improvement)
	•	Elasticsearch (Raporlama için kullanılıyor)

Security
	•	Keycloak (Token bazlı authentication)

Notification Service
	•	Gmail SMTP üzerinden kullanıcılara bildirim e-postası gönderilmektedir.

 Container Tecnology
	•	Docker

Design Patterns

Projede aşağıdaki tasarım desenleri kullanılmıştır:
	•	Strategy Pattern (Ödeme yöntemlerini yönetmek için)
	•	Proxy Pattern
	•	Saga Pattern (Dağıtık işlemlerin yönetimi)
	•	Facade Pattern
	•	Builder Pattern
	•	Factory Pattern
 	•	Signleaton Pattern

Architecture
	•	Mikroservis Mimarisi ile inşa edilmiştir.
	•	Clean Architecture prensiplerine uyulmuştur.
	•	MVC (Model-View-Controller) yapısı kullanılmıştır.
	•	Common & DAL modülleri ile ortak servisler sağlanmıştır.

Key Features
	•	Ödeme İşlemleri: Kullanıcıların kart bilgilerini kullanarak ödeme yapmasını sağlar.
	•	Faturalandırma: Kullanıcılara oluşturulan faturalar üzerinden ödeme yapma imkanı sunar.
	•	Bildirim Sistemi: Ödeme ve faturalarla ilgili e-posta bildirimleri gönderilir.
	•	Yetkilendirme: Kullanıcılar Keycloak ile kimlik doğrulama yapar.
	•	Ölçeklenebilirlik: Kafka ve Redis ile performans artırılmıştır.
	•	Raporlama: Elasticsearch üzerinden detaylı raporlamalar sunulmaktadır.
	•	Batch İşlemleri: Büyük veri setleri üzerinde batch işlemleri yapılabilmektedir. Buna uygun job bulunmaktadır.
	•	Scheduled Jobs: Arka planda belirli periyotlarla çalışan görevler bulunmaktadır.
