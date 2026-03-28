package com.coffee.xyzbackend.controller;

import com.coffee.xyzbackend.dto.request.LoginRequest;
import com.coffee.xyzbackend.model.Account;
import com.coffee.xyzbackend.service.AccountService;
import com.coffee.xyzbackend.service.JwtService;
import com.coffee.xyzbackend.service.TokenBlacklistService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.util.WebUtils;


@Controller
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class LoginController {
    AccountService accountService;
    JwtService jwtService;
    private final TokenBlacklistService tokenBlacklistService;

    @GetMapping("/login")
    public String loginPage() {
        return "views/auth/login";
    }

    @PostMapping("/login")
    public String processLogin(@ModelAttribute LoginRequest loginRequest, HttpServletResponse response, Model model) {
        Account account = accountService.authenticate(loginRequest.getUsername(), loginRequest.getPassword());
        if (account != null) {
            String token = jwtService.generateToken(account.getUsername(), account.getRole());

            Cookie jwtCookie = new Cookie("accessToken", token);
            jwtCookie.setHttpOnly(true);
            jwtCookie.setSecure(false);
            jwtCookie.setPath("/");
            jwtCookie.setMaxAge(24 * 60 * 60);

            response.addCookie(jwtCookie);
            return "redirect:/ban-hang";
        }
        model.addAttribute("error", "Tài khoản hoặc mật khẩu không đúng");
        return "views/auth/login";
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response) {
        Cookie jwtCookie = WebUtils.getCookie(request, "accessToken");

        if (jwtCookie != null && jwtCookie.getValue() != null) {
            String token = jwtCookie.getValue();

            // Kiểm tra xem token còn hợp lệ không ? parse
            if (jwtService.validateToken(token)) {
                long expirationTime = jwtService.extractExpiration(token).getTime();
                long currentTime = System.currentTimeMillis();
                long remainingTime = expirationTime - currentTime;

                // Còn hạn thì đá ra Blacklist
                if (remainingTime > 0) {
                    tokenBlacklistService.addToBlacklist(token, remainingTime);
                }
            }
        }

        // Xóa Cookie
        Cookie clearCookie = new Cookie("accessToken", null);
        clearCookie.setMaxAge(0);
        clearCookie.setPath("/");
        response.addCookie(clearCookie);

        return "redirect:/login";
    }
}
