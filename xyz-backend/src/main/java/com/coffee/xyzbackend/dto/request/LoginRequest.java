package com.coffee.xyzbackend.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class LoginRequest {

    @NotBlank(message = "Username không được để trống")
    @Size(min = 3, message = "Username phải có ít nhất 3 ký tự")
    String username;

    @NotBlank(message = "Password không được để trống")
    @Size(min = 3, message = "Password phải có ít nhất 3 ký tự")
    String password;
}