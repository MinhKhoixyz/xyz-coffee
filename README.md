# ☕ XYZ COFFEE 

![Java](https://img.shields.io/badge/Java_23-ED8B00?style=for-the-badge&logo=java&logoColor=white)
![Spring Boot](https://img.shields.io/badge/Spring_Boot-6DB33F?style=for-the-badge&logo=spring-boot&logoColor=white)
![Redis](https://img.shields.io/badge/redis-%23DD0031.svg?style=for-the-badge&logo=redis&logoColor=white)
![Docker](https://img.shields.io/badge/docker-%230db7ed.svg?style=for-the-badge&logo=docker&logoColor=white)
![SQL Server](https://img.shields.io/badge/SQL_Server-CC2927?style=for-the-badge&logo=microsoft-sql-server&logoColor=white)
![Bootstrap](https://img.shields.io/badge/Bootstrap_5-7952B3?style=for-the-badge&logo=bootstrap&logoColor=white)
![JWT](https://img.shields.io/badge/JWT-000000?style=for-the-badge&logo=JSON%20web%20tokens&logoColor=white)
![Cloudinary](https://img.shields.io/badge/Cloudinary-3448C5?style=for-the-badge&logo=Cloudinary&logoColor=white)
## ⚡ Key Features
* **Real-time POS:** High-performance sales and invoice management.
* **Enhanced Security:** JWT authentication with **Redis Blacklist** for instant token revocation.
* **Media Handling:** Automated image processing via Cloudinary API.
* **Clean Architecture:** Strictly followed Controller-Service-Repository pattern.

## 💻 Tech Stack
* **Backend:** Java 23, Spring Boot 4.0.3, JPA, MapStruct.
* **Infrastructure:** Docker, Redis (Caching/Security), MS SQL Server.
* **Frontend:** Thymeleaf, Bootstrap 5, Vanilla JS, Boxicons.

## 📂 Project Structure
```text
xyz-backend/
├── src/main/java/com/coffee/xyzbackend/
│   ├── config/           # System configurations (Security, Cloudinary, Redis)
│   ├── controller/       # API & View endpoints
│   ├── dto/              # Data Transfer Objects
│   ├── exception/        # Global Exception Handling
│   ├── model/            # JPA Entities
│   ├── repository/       # Data access layer (Spring Data JPA)
│   ├── service/          # Business logic implementation
│   └── util/             # Helpers (File upload, JWT, etc.)
└── src/main/resources/
    ├── static/           # Static assets (CSS, JS, Images)
    └── templates/        # Thymeleaf layouts & views
```