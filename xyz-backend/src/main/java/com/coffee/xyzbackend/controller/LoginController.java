package com.coffee.xyzbackend.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {
    @GetMapping("login")
    public String loginPage() {
        return "views/auth/Login.html";
    }
}
