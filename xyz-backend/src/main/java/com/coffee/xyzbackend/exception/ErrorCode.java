package com.coffee.xyzbackend.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {
    USER_EXISTED(9999, "Username already exists");

    private final int code;
    private final String message;
}
