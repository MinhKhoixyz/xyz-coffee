package com.coffee.xyzbackend.model;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "san_pham_kich_co")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SanPhamKichCo {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(columnDefinition = "VARCHAR(36)")
    String id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", nullable = false)
    SanPham sanPham;

    @Column(name = "size_name", length = 20, nullable = false)
    String sizeName;

    @Column(name = "price", nullable = false)
    Integer price;

    @Column(name = "is_active")
    Boolean isActive = true;
}