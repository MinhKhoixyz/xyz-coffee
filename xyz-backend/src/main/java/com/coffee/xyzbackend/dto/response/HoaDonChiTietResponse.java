package com.coffee.xyzbackend.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class HoaDonChiTietResponse {
    String productName;
    String sizeName;
    Integer quantity;
    Integer price;
    Integer totalAmount;
}