package com.coffee.xyzbackend.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class LoaiSanPhamRequest {
    String id;
    @NotBlank(message = "Tên loại sản phẩm không được để trống")
    String name;
    String description;
    Boolean isActive;
}