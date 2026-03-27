package com.coffee.xyzbackend.exception;

import com.coffee.xyzbackend.dto.request.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.resource.NoResourceFoundException;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<ApiResponse<Object>> handlingRuntimeException(Exception exception) {
        return ResponseEntity.badRequest().body(ApiResponse.builder()
                .code(ErrorCode.UNCATEGORIZED_EXCEPTION.getCode())
                .message(ErrorCode.UNCATEGORIZED_EXCEPTION.getMessage())
                .build());
    }

    @ExceptionHandler(value = AppException.class)
    public ResponseEntity<ApiResponse<Object>> handlingAppException(AppException exception) {
        ErrorCode errorCode = exception.getErrorCode();
        return ResponseEntity.badRequest().body(ApiResponse.builder()
                .code(errorCode.getCode())
                .message(errorCode.getMessage())
                .build());
    }

    // lỗi input Validate
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse<Object>> handlingValidationException(MethodArgumentNotValidException exception) {
        return ResponseEntity.badRequest().body(ApiResponse.builder()
                .code(ErrorCode.INVALID_KEY.getCode())
                // Lấy message cụ thể từ DTO (ví dụ: "Tên không được để trống")
                .message(exception.getFieldError() != null ? exception.getFieldError().getDefaultMessage() : "Lỗi validate")
                .build());
    }

    // 404 -> return error.html
    @ExceptionHandler(value = NoResourceFoundException.class)
    public ModelAndView handle404Exception(NoResourceFoundException exception) {
        ModelAndView modelAndView = new ModelAndView("error");

        modelAndView.addObject("errorMessage", "Đường dẫn bạn tìm kiếm không tồn tại trên hệ thống!");

        return modelAndView;
    }
}