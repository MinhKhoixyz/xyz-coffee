package com.coffee.xyzbackend.dto.request;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class KichCoRequest {
    String id;
    String productId;
    String sizeName;
    Integer price;
    Boolean isActive;
}