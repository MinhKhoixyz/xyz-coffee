package com.coffee.xyzbackend.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class HoaDonResponse {
    String id;
    String accountName;
    Integer totalAmount;
    String status;
    String paymentMethod;
    LocalDateTime createdAt;
}