package com.coffee.xyzbackend.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AccountCreationRequest {

    @NotBlank(message = "Username không được để trống")
    @Size(min = 8, max = 20, message = "Username phải từ 8-20 ký tự")
    String username;

    @NotBlank(message = "Password không được để trống")
    @Size(min = 8, max = 20, message = "Password phải từ 8-20 ký tự")
    String password;

    @NotBlank(message = "Email không được để trống")
    @Email(message = "Email không đúng định dạng")
    String email;

    @NotBlank(message = "Lỗi không nhận được role")
    String role;
}