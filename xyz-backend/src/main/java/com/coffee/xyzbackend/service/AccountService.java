package com.coffee.xyzbackend.service;

import com.coffee.xyzbackend.model.Account;
import com.coffee.xyzbackend.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AccountService {
    @Autowired
    private AccountRepository accountRepository;

    public Account authenticate(String username, String password) {
        Optional<Account> accountOpt = accountRepository.findByUsername(username);
        if (accountOpt.isPresent() && accountOpt.get().getPassword().equals(password)) {
            return accountOpt.get(); // Xác thực thành công
        }
        return null; // Xác thực thất bại
    }
}
