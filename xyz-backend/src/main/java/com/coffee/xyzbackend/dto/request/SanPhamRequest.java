package com.coffee.xyzbackend.dto.request;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SanPhamRequest {
    String id;
    String name;
    String categoryId;
    String imageUrl;
    String description;
    Boolean isActive;
}