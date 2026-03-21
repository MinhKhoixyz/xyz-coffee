IF NOT EXISTS (SELECT * FROM sys.databases WHERE name = 'XYZ_Coffee')
BEGIN
    CREATE DATABASE [XYZ_Coffee];
END
GO

USE [XYZ_Coffee];
GO

DROP TABLE IF EXISTS [dbo].[san_pham_kich_co];
DROP TABLE IF EXISTS [dbo].[san_pham];
DROP TABLE IF EXISTS [dbo].[loai_san_pham];
DROP TABLE IF EXISTS [dbo].[account];
GO 

CREATE TABLE [dbo].[account] (
    id VARCHAR(36) PRIMARY KEY DEFAULT NEWID(), 
    username VARCHAR(50) NOT NULL UNIQUE, 
    email VARCHAR(100) NOT NULL UNIQUE, 
    password VARCHAR(255) NOT NULL UNIQUE,
    role VARCHAR(20) DEFAULT 'USER', 
    is_active BIT DEFAULT 1, 
    created_at DATETIME DEFAULT GETDATE(),
    updated_at DATETIME DEFAULT GETDATE(),
    CONSTRAINT CHK_Role CHECK (role IN ('BOSS', 'ADMIN', 'STAFF'))
);
GO

CREATE UNIQUE INDEX UX_OnlyOneBoss ON [dbo].[account](role) WHERE role = 'BOSS';
GO

CREATE TABLE [dbo].[loai_san_pham] (
    id VARCHAR(36) PRIMARY KEY DEFAULT NEWID(),
    name NVARCHAR(100) NOT NULL UNIQUE, 
    description NVARCHAR(MAX),
    is_active BIT DEFAULT 1,
    created_at DATETIME DEFAULT GETDATE(),
    updated_at DATETIME DEFAULT GETDATE()
);
GO

CREATE TABLE [dbo].[san_pham] (
    id VARCHAR(36) PRIMARY KEY DEFAULT NEWID(),
    category_id VARCHAR(36) NOT NULL, 
    name NVARCHAR(100) NOT NULL,
    image_url VARCHAR(500), 
    description NVARCHAR(MAX),
    is_active BIT DEFAULT 1,
    created_at DATETIME DEFAULT GETDATE(),
    updated_at DATETIME DEFAULT GETDATE(),
    CONSTRAINT FK_SanPham_LoaiSanPham FOREIGN KEY (category_id) REFERENCES [dbo].[loai_san_pham](id) ON DELETE CASCADE
);
GO

CREATE TABLE [dbo].[san_pham_kich_co] (
    id VARCHAR(36) PRIMARY KEY DEFAULT NEWID(),
    product_id VARCHAR(36) NOT NULL,
    size_name NVARCHAR(20) NOT NULL,
    price INT NOT NULL CHECK (price >= 0),
    is_active BIT DEFAULT 1,
    CONSTRAINT FK_KichCo_SanPham FOREIGN KEY (product_id) REFERENCES [dbo].[san_pham](id) ON DELETE CASCADE,
    CONSTRAINT UQ_SanPham_KichCo UNIQUE (product_id, size_name)
);
GO

BEGIN TRY
    BEGIN TRANSACTION; 

    INSERT INTO [dbo].[account] (username, email, password, role)
    VALUES 
        ('boss', 'boss@xyzcoffee.com', 'boss123@', 'BOSS'),
        ('admin', 'admin@xyzcoffee.com', 'admin123@', 'ADMIN'),
        ('staff', 'staff@xyzcoffee.com', 'staff123@', 'STAFF');

    DECLARE @CatCoffeeId VARCHAR(36) = NEWID();
    DECLARE @CatTeaId VARCHAR(36) = NEWID();
    DECLARE @CatFreezeId VARCHAR(36) = NEWID();
    DECLARE @CatOtherId VARCHAR(36) = NEWID();

    INSERT INTO [dbo].[loai_san_pham] (id, name, description) VALUES
        (@CatCoffeeId, N'Cà Phê', N'Sự kết hợp hoàn hảo giữa hạt cà phê Robusta & Arabica thượng hạng được trồng trên những vùng cao nguyên Việt Nam màu mỡ, qua những bí quyết rang xay độc đáo, Highlands Coffee chúng tôi tự hào giới thiệu những dòng sản phẩm Cà phê mang hương vị đậm đà và tinh tế.'),
        (@CatTeaId, N'Trà', N'Hương vị tự nhiên, thơm ngon của Trà Việt với phong cách hiện đại tại Highlands Coffee sẽ giúp bạn gợi mở vị giác của bản thân và tận hưởng một cảm giác thật khoan khoái, tươi mới.'),
        (@CatFreezeId, N'Freeze', N'Sảng khoái với thức uống đá xay phong cách Việt. Freeze là thức uống đá xay mát lạnh được pha chế từ những nguyên liệu thuần túy của Việt Nam.'),
        (@CatOtherId, N'Khác', N'Sẽ càng ngon miệng hơn khi bạn kết hợp đồ uống với những chiếc bánh ngọt thơm ngon được làm thủ công hàng ngày ngay tại bếp bánh của Highlands Coffee, và cũng đừng quên sắm cho mình bộ bí kíp pha cà phê tại nhà gồm Phin inox, ly sứ Mosaic và cà phê gói Truyền thống mỗi khi có thời gian rảnh rỗi nhé.');

    DECLARE @AmDaId VARCHAR(36) = NEWID();
    DECLARE @BacSiuId VARCHAR(36) = NEWID();
    DECLARE @CapDaId VARCHAR(36) = NEWID();
    DECLARE @CarMacId VARCHAR(36) = NEWID();
    DECLARE @EspNongId VARCHAR(36) = NEWID();
    DECLARE @LatDaId VARCHAR(36) = NEWID();
    DECLARE @PhinDenId VARCHAR(36) = NEWID();
    DECLARE @PhinSuaId VARCHAR(36) = NEWID();
    DECLARE @PhindiHanhNhanId VARCHAR(36) = NEWID();

    DECLARE @CarPhinFrzId VARCHAR(36) = NEWID();
    DECLARE @ClsPhinFrzId VARCHAR(36) = NEWID();
    DECLARE @FrzChocoId VARCHAR(36) = NEWID();
    DECLARE @FrzCookId VARCHAR(36) = NEWID();
    DECLARE @FrzTraXanhId VARCHAR(36) = NEWID();

    DECLARE @BmqGaId VARCHAR(36) = NEWID();
    DECLARE @BmqPateId VARCHAR(36) = NEWID();
    DECLARE @CroisId VARCHAR(36) = NEWID();
    DECLARE @Culi200Id VARCHAR(36) = NEWID();
    DECLARE @Moka200Id VARCHAR(36) = NEWID();
    DECLARE @TacDaXayId VARCHAR(36) = NEWID();
    DECLARE @TrThong1kgId VARCHAR(36) = NEWID();
    DECLARE @TrThong200gId VARCHAR(36) = NEWID();

    DECLARE @TrThachDaoId VARCHAR(36) = NEWID();
    DECLARE @TrThachVaiId VARCHAR(36) = NEWID();
    DECLARE @TrXanhDauDoId VARCHAR(36) = NEWID();
    DECLARE @TsvCuNangId VARCHAR(36) = NEWID();
    DECLARE @TsvSenId VARCHAR(36) = NEWID();

    INSERT INTO [dbo].[san_pham] (id, category_id, name, image_url, description) VALUES
        (@AmDaId, @CatCoffeeId, N'Americano Đá', '/assets/img/products/coffee/AMERICANO_DA.jpg', N''),
        (@BacSiuId, @CatCoffeeId, N'Bạc Xỉu', '/assets/img/products/coffee/BAC_SIU.jpg', N''),
        (@CapDaId, @CatCoffeeId, N'Cappuccino Đá', '/assets/img/products/coffee/CAPPU_DA.jpg', N''),
        (@CarMacId, @CatCoffeeId, N'Caramel Macchiatto', '/assets/img/products/coffee/CARAMEL_MACCHIATTO.jpg', N''),
        (@EspNongId, @CatCoffeeId, N'Espresso Nóng', '/assets/img/products/coffee/ESPRESSO_NONG.jpg', N''),
        (@LatDaId, @CatCoffeeId, N'Latte Đá', '/assets/img/products/coffee/LATTE_DA.jpg', N''),
        (@PhinDenId, @CatCoffeeId, N'Phin Đen Đá', '/assets/img/products/coffee/PHIN_DEN_DA.jpg', N''),
        (@PhinSuaId, @CatCoffeeId, N'Phin Sữa Đá', '/assets/img/products/coffee/PHIN_SUA_DA.jpg', N''),
        (@PhindiHanhNhanId, @CatCoffeeId, N'PhinDi Hạnh Nhân', '/assets/img/products/coffee/PHINDI_HANH_NHAN.jpg', N''),

        (@CarPhinFrzId, @CatFreezeId, N'Caramel Phin Freeze', '/assets/img/products/freeze/CARAMEL_PHIN_FREEZE.jpg', N''),
        (@ClsPhinFrzId, @CatFreezeId, N'Classic Phin Freeze', '/assets/img/products/freeze/CLASSIC_PHIN_FREEZE.jpg', N''),
        (@FrzChocoId, @CatFreezeId, N'Freeze Chocolate', '/assets/img/products/freeze/FREEZE_CHOCOLATE.jpg', N''),
        (@FrzCookId, @CatFreezeId, N'Freeze Cookies', '/assets/img/products/freeze/FREEZE_COOKIES.jpg', N''),
        (@FrzTraXanhId, @CatFreezeId, N'Freeze Trà Xanh', '/assets/img/products/freeze/FREEZE_TRA_XANH.jpg', N''),

        (@BmqGaId, @CatOtherId, N'Bánh Mì Que Gà', '/assets/img/products/khac/BMQ_GA.jpg', N''),
        (@BmqPateId, @CatOtherId, N'Bánh Mì Que Pate', '/assets/img/products/khac/BMQ_PATE.jpg', N''),
        (@CroisId, @CatOtherId, N'Croissant', '/assets/img/products/khac/CROISAINT.jpg', N''),
        (@Culi200Id, @CatOtherId, N'Cà Phê Culi 200g', '/assets/img/products/khac/Culi_200g.png', N''),
        (@Moka200Id, @CatOtherId, N'Cà Phê Moka 200g', '/assets/img/products/khac/Moka_200g.png', N''),
        (@TacDaXayId, @CatOtherId, N'Tắc Đá Xay', '/assets/img/products/khac/TAC_DA_XAY.jpg', N''),
        (@TrThong1kgId, @CatOtherId, N'Truyền Thống 1kg', '/assets/img/products/khac/Truyen_Thong_1kg.png', N''),
        (@TrThong200gId, @CatOtherId, N'Truyền Thống 200g', '/assets/img/products/khac/Truyen_Thong_200g.png', N''),

        (@TrThachDaoId, @CatTeaId, N'Trà Thạch Đào', '/assets/img/products/tra/TRA_THACH_DAO.jpg', N''),
        (@TrThachVaiId, @CatTeaId, N'Trà Thạch Vải', '/assets/img/products/tra/TRA_THACH_VAI.jpg', N''),
        (@TrXanhDauDoId, @CatTeaId, N'Trà Xanh Đậu Đỏ', '/assets/img/products/tra/TRA_XANH_DAU_DO.jpg', N''),
        (@TsvCuNangId, @CatTeaId, N'Trà Sen Vàng Củ Năng', '/assets/img/products/tra/TSV_CU_NANG.jpg', N''),
        (@TsvSenId, @CatTeaId, N'Trà Sen Vàng', '/assets/img/products/tra/TSV_SEN.jpg', N'');

    INSERT INTO [dbo].[san_pham_kich_co] (product_id, size_name, price) VALUES
        (@AmDaId, 'S', 39000), (@AmDaId, 'M', 49000), (@AmDaId, 'L', 55000),
        (@BacSiuId, 'S', 29000), (@BacSiuId, 'M', 39000), (@BacSiuId, 'L', 45000),
        (@CapDaId, 'S', 45000), (@CapDaId, 'M', 55000), (@CapDaId, 'L', 65000),
        (@CarMacId, 'S', 49000), (@CarMacId, 'M', 59000), (@CarMacId, 'L', 69000),
        (@EspNongId, 'S', 35000), (@EspNongId, 'M', 45000),
        (@LatDaId, 'S', 45000), (@LatDaId, 'M', 55000), (@LatDaId, 'L', 65000),
        (@PhinDenId, 'S', 29000), (@PhinDenId, 'M', 39000), (@PhinDenId, 'L', 45000),
        (@PhinSuaId, 'S', 29000), (@PhinSuaId, 'M', 39000), (@PhinSuaId, 'L', 45000),
        (@PhindiHanhNhanId, 'S', 45000), (@PhindiHanhNhanId, 'M', 55000), (@PhindiHanhNhanId, 'L', 65000),

        (@CarPhinFrzId, 'S', 55000), (@CarPhinFrzId, 'M', 65000), (@CarPhinFrzId, 'L', 69000),
        (@ClsPhinFrzId, 'S', 55000), (@ClsPhinFrzId, 'M', 65000), (@ClsPhinFrzId, 'L', 69000),
        (@FrzChocoId, 'S', 55000), (@FrzChocoId, 'M', 65000), (@FrzChocoId, 'L', 69000),
        (@FrzCookId, 'S', 55000), (@FrzCookId, 'M', 65000), (@FrzCookId, 'L', 69000),
        (@FrzTraXanhId, 'S', 55000), (@FrzTraXanhId, 'M', 65000), (@FrzTraXanhId, 'L', 69000),

        (@BmqGaId, N'Tiêu chuẩn', 19000),
        (@BmqPateId, N'Tiêu chuẩn', 19000),
        (@CroisId, N'Tiêu chuẩn', 25000),
        (@Culi200Id, N'Gói 200g', 75000),
        (@Moka200Id, N'Gói 200g', 85000),
        (@TacDaXayId, 'S', 39000), (@TacDaXayId, 'M', 49000), (@TacDaXayId, 'L', 55000),
        (@TrThong1kgId, N'Gói 1kg', 250000),
        (@TrThong200gId, N'Gói 200g', 65000),

        (@TrThachDaoId, 'S', 45000), (@TrThachDaoId, 'M', 55000), (@TrThachDaoId, 'L', 65000),
        (@TrThachVaiId, 'S', 45000), (@TrThachVaiId, 'M', 55000), (@TrThachVaiId, 'L', 65000),
        (@TrXanhDauDoId, 'S', 45000), (@TrXanhDauDoId, 'M', 55000), (@TrXanhDauDoId, 'L', 65000),
        (@TsvCuNangId, 'S', 45000), (@TsvCuNangId, 'M', 55000), (@TsvCuNangId, 'L', 65000),
        (@TsvSenId, 'S', 45000), (@TsvSenId, 'M', 55000), (@TsvSenId, 'L', 65000);

    COMMIT TRANSACTION; 
    PRINT N'Insert success';
END TRY
BEGIN CATCH
    ROLLBACK TRANSACTION; 
    PRINT ERROR_MESSAGE();
END CATCH;
GO