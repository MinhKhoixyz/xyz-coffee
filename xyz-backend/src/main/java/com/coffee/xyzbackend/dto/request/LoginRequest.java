package com.coffee.xyzbackend.dto.request;

import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class LoginRequest {
    @Size(min = 3, message = "Username không hợp lệ")
    private String username;
    @Size(min = 3, message = "Password không hợp lệ")
    private String password;
    private String role;
}
