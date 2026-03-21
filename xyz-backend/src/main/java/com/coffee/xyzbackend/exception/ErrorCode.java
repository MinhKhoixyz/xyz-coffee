package com.coffee.xyzbackend.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {
    // Nhóm lỗi chung
    UNCATEGORIZED_EXCEPTION(9999, "Lỗi hệ thống không xác định, vui lòng liên hệ IT"),
    INVALID_KEY(1001, "Lỗi dữ liệu đầu vào không hợp lệ"),

    // Nhóm lỗi Account
    USER_EXISTED(1002, "Tên đăng nhập đã tồn tại trong hệ thống"),
    USER_NOT_FOUND(1003, "Tài khoản không tồn tại"),
    UNAUTHENTICATED(1004, "Mật khẩu không chính xác"),

    // Nhóm lỗi Sản phẩm
    CATEGORY_NOT_FOUND(2001, "Không tìm thấy loại sản phẩm này");

    private final int code;
    private final String message;
}