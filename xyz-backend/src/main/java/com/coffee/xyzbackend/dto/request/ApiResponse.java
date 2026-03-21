package com.coffee.xyzbackend.dto.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@JsonInclude(JsonInclude.Include.NON_NULL) // nếu null thì không kèm vào trong Json
public class ApiResponse <T> {
    int code = 1000; // 1000 = true
    String message;
    T result;
}
