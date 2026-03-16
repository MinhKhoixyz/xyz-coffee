package com.coffee.xyzbackend.controller;

import com.coffee.xyzbackend.dto.request.LoginRequest;
import com.coffee.xyzbackend.model.Account;
import com.coffee.xyzbackend.service.AccountService;
import com.coffee.xyzbackend.service.JwtService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
public class LoginController {
    @Autowired
    private AccountService accountService;

    @Autowired
    private JwtService jwtService;

    @GetMapping("/login")
    public String loginPage() {
        return "views/auth/login";
    }

    @PostMapping("/login")
    public String processLogin(@ModelAttribute LoginRequest loginRequest,
                               HttpServletResponse response,
                               Model model) {
        Account account = accountService.authenticate(loginRequest.getUsername(),
                loginRequest.getPassword());
        if (account != null) {
            String token = jwtService.generateToken(account.getUsername(), account.getRole());

            Cookie jwtCookie = new Cookie("accessToken", token);
            jwtCookie.setHttpOnly(true); // Chống XSS
            jwtCookie.setSecure(false); // Khi nào đẩy lên domain HTTPS thật thì đổi thành true
            jwtCookie.setPath("/");
            jwtCookie.setMaxAge(24 * 60 * 60);

            response.addCookie(jwtCookie);
            return "redirect:/views/admin/ban-hang";
        }
        model.addAttribute("error", "Tài khoản hoặc mật khẩu không đúng");
        return "views/auth/login";
    }


}
