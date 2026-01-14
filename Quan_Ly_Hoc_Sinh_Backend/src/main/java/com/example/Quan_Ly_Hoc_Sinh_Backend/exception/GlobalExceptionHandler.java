package com.example.Quan_Ly_Hoc_Sinh_Backend.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    // Bắt lỗi RuntimeException (Các kiiux chủ động throw)
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<Map<String, Object>> handleRuntimeException(RuntimeException e) {
        Map<String, Object> body = new HashMap<>();
        body.put("timestamo", LocalDateTime.now());
        body.put("message", e.getMessage());
        body.put("status", HttpStatus.BAD_REQUEST.value());

        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }

    //bắt lỗi thiếu tham ố
    public ResponseEntity<Map<String, Object>> handleMissingParams(MissingServletRequestParameterException e) {
        Map<String, Object> body = new HashMap<>();
        body.put("timestamo", LocalDateTime.now());
        body.put("message", "Thiếu tham số bắt buộc: " + e.getParameterName());
        body.put("status", HttpStatus.BAD_REQUEST.value());

        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }

    //Bắt lỗi hệ thống để không lộ log đỏ
    public ResponseEntity<Map<String, Object>> handleGlobalException(Exception e) {
        Map<String, Object> body = new HashMap<>();
        body.put("timestamo", LocalDateTime.now());
        body.put("message", "Đã xảy ra lỗi hệ thống. Vui lòng liên hệ Admin!");
        body.put("error", e.getMessage());
        body.put("status", HttpStatus.INTERNAL_SERVER_ERROR.value());

        return new ResponseEntity<>(body, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
