package com.coffee.xyzbackend.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddException extends RuntimeException {
    private ErrorCode errorCode;

    public AddException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }
}
