package com.coffee.xyzbackend.dto.request;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class HoaDonRequest {
    String customerId;
    Integer totalAmount;
    String paymentMethod;
    String note;
    List<HoaDonChiTietRequest> chiTietList;
}