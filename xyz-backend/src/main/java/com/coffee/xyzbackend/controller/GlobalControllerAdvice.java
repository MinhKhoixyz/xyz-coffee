package com.coffee.xyzbackend.controller;

import com.coffee.xyzbackend.model.Account;
import com.coffee.xyzbackend.repository.AccountRepository;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.Optional;

@ControllerAdvice
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class GlobalControllerAdvice {

    AccountRepository accountRepository;

    @ModelAttribute
    public void addGlobalAttributes(HttpServletRequest request, Model model) {
        String username = (String) request.getAttribute("username"); // Lấy từ Interceptor

        if (username != null) {
            Optional<Account> accountOpt = accountRepository.findByUsername(username);

            if (accountOpt.isPresent() && accountOpt.get().getNhanVien() != null) {
                model.addAttribute("currentFullName", accountOpt.get().getNhanVien().getFullName());
                model.addAttribute("currentAvatar", accountOpt.get().getNhanVien().getAvatarUrl());
            } else {
                model.addAttribute("currentFullName", username);
            }
        }
    }
}