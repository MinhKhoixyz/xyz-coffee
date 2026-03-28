# ☕ XYZ COFFEE MANAGEMENT SYSTEM

![Java](https://img.shields.io/badge/Java_23-ED8B00?style=for-the-badge&logo=java&logoColor=white)
![Spring Boot](https://img.shields.io/badge/Spring_Boot-6DB33F?style=for-the-badge&logo=spring-boot&logoColor=white)
![SQL Server](https://img.shields.io/badge/SQL_Server-CC2927?style=for-the-badge&logo=microsoft-sql-server&logoColor=white)
![Bootstrap](https://img.shields.io/badge/Bootstrap_5-7952B3?style=for-the-badge&logo=bootstrap&logoColor=white)
![JWT](https://img.shields.io/badge/JWT-000000?style=for-the-badge&logo=JSON%20web%20tokens&logoColor=white)
![Cloudinary](https://img.shields.io/badge/Cloudinary-3448C5?style=for-the-badge&logo=Cloudinary&logoColor=white)

## 📌 Giới thiệu
**XYZ COFFEE** là hệ thống quản trị nội bộ toàn diện dành cho chuỗi cửa hàng cà phê.
Dự án được tạo ra với mục đích giúp các quán coffee tối ưu hóa quy trình vận hành, từ việc quản lý thực đơn, kiểm soát giá bán, cho đến tự động hóa quy trình thanh toán tại quầy (POS) và lưu trữ hóa đơn điện tử.

## 💻 Tech Stack
- **Backend:** Java 23, Spring Boot (Spring Web, Spring Data JPA)
- **Frontend:** HTML5, Bootstrap 5, Thymeleaf, Vanilla JS, Boxicons
- **Database:** Microsoft SQL Server
- **Bảo mật:** JWT (JSON Web Tokens)
- **Cloud Storage:** Cloudinary

## 📂 Cấu trúc dự án
```text
xyz-backend/
├── src/main/java/com/coffee/xyzbackend/
│   ├── config/           # Cấu hình Interceptor bảo vệ Route & Cloudinary
│   ├── controller/       # Xử lý HTTP Requests & Trả về Thymeleaf View
│   ├── dto/              # Đối tượng luân chuyển dữ liệu (Request/Response)
│   ├── exception/        # Global Exception Handler & Error Codes
│   ├── model/            # Entity Map với SQL Server
│   ├── repository/       # Giao tiếp Database (Spring Data JPA)
│   ├── service/          # Chứa Business Logic & Tạo/Xác thực JWT
│   └── util/             # Các lớp tiện ích 
└── src/main/resources/
    ├── static/assets/    # Tài nguyên tĩnh tách biệt (CSS, JS logic, Images)
    └── templates/        # Giao diện Thymeleaf (Layout chung, Components, Views)