package com.coffee.xyzbackend.service;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class TokenBlacklistService {

    StringRedisTemplate redisTemplate;

    // Đá ra Blacklist kèm theo thời gian sống (TTL)
    public void addToBlacklist(String token, long remainingTimeInMillis) {
        // Cấu trúc: KEY = token, VALUE = "blacklisted", TTL = thời gian sống còn lại
        redisTemplate.opsForValue().set(token, "blacklisted", remainingTimeInMillis, TimeUnit.MILLISECONDS);
    }

    // Kiểm tra xem Token có đang nằm trong danh sách đen không
    public boolean isBlacklisted(String token) {
        return Boolean.TRUE.equals(redisTemplate.hasKey(token));
    }
}