package com.a603.ofcourse.global.exception;

import com.a603.ofcourse.global.exception.dto.GlobalExceptionResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ControllerAdvice {
    @ExceptionHandler(GlobalException.class)
    public ResponseEntity<GlobalExceptionResponse> globalExceptionHandler(GlobalException e) {
        return GlobalExceptionResponse.toResponse(e);
    }
}
