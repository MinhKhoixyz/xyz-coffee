IF NOT EXISTS (SELECT * FROM sys.databases WHERE name = 'XYZ_Coffee')
BEGIN
    CREATE DATABASE [XYZ_Coffee];
END
GO

USE [XYZ_Coffee];
GO

DROP TABLE IF EXISTS [dbo].[Account];
GO

CREATE TABLE [dbo].[Account] (
    id INT IDENTITY(1,1) PRIMARY KEY, 
    username VARCHAR(50) NOT NULL UNIQUE, 
    password VARCHAR(255) NOT NULL, -- hash
    role VARCHAR(20) DEFAULT 'USER', 
    is_active BIT DEFAULT 1, -- 1 là tài khoản đang hoạt động, 0 là bị khóa
    created_at DATETIME DEFAULT GETDATE(),
    updated_at DATETIME DEFAULT GETDATE(),
    CONSTRAINT CHK_Role CHECK (role IN ('BOSS', 'ADMIN', 'STAFF')) -- fix cứng
);
GO

CREATE UNIQUE INDEX UX_OnlyOneBoss
ON [dbo].[Account](role)
WHERE role = 'BOSS';
GO

INSERT INTO [dbo].[Account] (username, password, role)
VALUES 
    ('boss', '1234', 'BOSS'),
    ('admin', '1234', 'ADMIN'),
    ('staff', '1234', 'STAFF');
GO
