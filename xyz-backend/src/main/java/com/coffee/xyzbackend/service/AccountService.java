package com.coffee.xyzbackend.service;

import com.coffee.xyzbackend.exception.AppException;
import com.coffee.xyzbackend.exception.ErrorCode;
import com.coffee.xyzbackend.model.Account;
import com.coffee.xyzbackend.repository.AccountRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AccountService {

    AccountRepository accountRepository;

    public Account authenticate(String username, String password) {
        // 1. Tìm user, nếu không thấy -> Ném qua USER_NOT_FOUND
        Account account = accountRepository.findByUsername(username)
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));

        // Check password
        if (!account.getPassword().equals(password)) {
            throw new AppException(ErrorCode.UNAUTHENTICATED);
        }

        // Check is_active
        if (!account.getIsActive()) {
            throw new AppException(ErrorCode.UNCATEGORIZED_EXCEPTION);
        }

        return account;
    }
}