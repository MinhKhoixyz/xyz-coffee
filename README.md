# ☕ XYZ COFFEE MANAGEMENT SYSTEM

![Java](https://img.shields.io/badge/Java_23-ED8B00?style=for-the-badge&logo=java&logoColor=white)
![Spring Boot](https://img.shields.io/badge/Spring_Boot-6DB33F?style=for-the-badge&logo=spring-boot&logoColor=white)
![SQL Server](https://img.shields.io/badge/SQL_Server-CC2927?style=for-the-badge&logo=microsoft-sql-server&logoColor=white)
![Bootstrap](https://img.shields.io/badge/Bootstrap_5-7952B3?style=for-the-badge&logo=bootstrap&logoColor=white)
![JWT](https://img.shields.io/badge/JWT-000000?style=for-the-badge&logo=JSON%20web%20tokens&logoColor=white)

## 📌 Giới thiệu
**XYZ COFFEE** là hệ thống quản trị nội bộ dành cho chuỗi cửa hàng cà phê. 
Dự án tập trung vào kiến trúc Monolithic (SSR), tối ưu hiệu năng, chuẩn hóa luồng dữ liệu và thiết lập tiêu chuẩn bảo mật khắt khe.

## 💻 Tech Stack
- **Backend:** Java 23, Spring Boot (Web, Data JPA, Validation).
- **Frontend:** HTML5, Bootstrap 5, Thymeleaf, Vanilla JS, CSS Variables (Custom Theme Engine).
- **Database:** Microsoft SQL Server.
- **Bảo mật:** JSON Web Token (JWT), HttpOnly Cookie, Spring Interceptor.

## 🔥 Tính năng kỹ thuật cốt lõi
- **Bảo mật Đa tầng:** JWT cấp phát qua HttpOnly Cookie chống tấn công XSS. Sử dụng `AuthInterceptor` làm trạm kiểm soát chặn đứng các truy cập trái phép vào route Admin.
- **Giao diện Động (Dynamic Theme Engine):** Tự xây dựng cơ chế chuyển đổi Dark/Light mode và bộ màu Surface bằng Vanilla JS. Can thiệp sâu vào vòng đời load trang để triệt tiêu lỗi chớp nháy giao diện (FOUC).
- **Kiến trúc Chuẩn mực:** Áp dụng MVC Pattern phân tách tầng rõ ràng. Dùng DTO Pattern (`LoginRequest`, `CreationRequest`) che giấu Entity và chống Over-posting.
- **Kiểm soát Ngoại lệ (Global Exception):** Tích hợp `@ControllerAdvice` và Bean Validation chặn dữ liệu rác, xử lý lỗi tập trung, đảm bảo hệ thống không bao giờ crash trắng trang.
- **Khóa chính An toàn:** Chuẩn hóa UUID cho toàn bộ primary key trong Database.

## 📂 Cấu trúc thư mục (Backend)
```text
src/main/java/com/coffee/xyzbackend/
├── controller/       # Xử lý HTTP Requests & Trả về Thymeleaf View
├── service/          # Chứa Business Logic & Tạo/Xác thực JWT
├── repository/       # Giao tiếp Database (Spring Data JPA)
├── model/            # Entity Map với SQL Server
├── dto/              # Đối tượng luân chuyển dữ liệu (Request/Response)
├── exception/        # Global Exception Handler & Error Codes
└── config/           # Cấu hình Interceptor bảo vệ Route