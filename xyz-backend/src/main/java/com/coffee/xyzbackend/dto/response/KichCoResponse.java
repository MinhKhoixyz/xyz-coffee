package com.coffee.xyzbackend.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class KichCoResponse {
    String id;
    String productName;
    String productId;
    String sizeName;
    Integer price;
    Boolean isActive;
}