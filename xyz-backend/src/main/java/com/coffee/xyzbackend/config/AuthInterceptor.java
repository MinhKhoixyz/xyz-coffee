package com.coffee.xyzbackend.config;

import com.coffee.xyzbackend.service.JwtService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.util.WebUtils;

@Component
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AuthInterceptor implements HandlerInterceptor {

    JwtService jwtService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Cookie jwtCookie = WebUtils.getCookie(request, "accessToken");

        if (jwtCookie != null && jwtService.validateToken(jwtCookie.getValue())) {
            return true;
        }

        response.sendRedirect("/login");
        return false;
    }
}