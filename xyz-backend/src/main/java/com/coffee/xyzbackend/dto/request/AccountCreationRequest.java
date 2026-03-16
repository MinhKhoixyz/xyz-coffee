package com.coffee.xyzbackend.dto.request;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;

import java.util.Date;

public class AccountCreationRequest {
    @Size(min = 8, max = 20, message = "Username must be at least 8 charracters")
    @Column(name = "username")
    private String username;
    @Size(min = 8, max = 20, message = "Password must be at least 8 charracters")
    @Column(name = "password")
    private String password;

    @Email
    @Column(name = "email")
    private String email;

    @Column(name = "role")
    private String role;

    @Column(name = "is_active")
    private Boolean isActive;

    @Column(name = "created_at")
    private Date createdAt;

    @Column(name = "updated_at")
    private Date updatedAt;
}
