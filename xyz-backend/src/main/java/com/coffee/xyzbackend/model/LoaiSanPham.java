package com.coffee.xyzbackend.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "loai_san_pham")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class LoaiSanPham {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    String id;

    @Column(name = "name", nullable = false, unique = true)
    @Size(min = 1, max = 100, message = "Tên loại sản phẩm phải trong khoảng 1-100 ký tự")
    String ten;

    @Column(name = "description")
    @Size(min = 1, max = 255, message = "Miêu loại sản phẩm phải trong khoảng 1-255 ký tự")
    String description;

    @Column(name = "is_active")
    Boolean isActive;

    // Đóng băng field này, không cho update lần thứ 2 (updatable)
    @Column(name = "created_at", updatable = false)
    @CreationTimestamp
    LocalDateTime createdAt;

    @Column(name = "updated_at")
    @CreationTimestamp
    LocalDateTime updatedAt;
}
