package com.coffee.xyzbackend.dto.request;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class HoaDonChiTietRequest {
    String productSizeId;
    Integer quantity;
    Integer price;
}