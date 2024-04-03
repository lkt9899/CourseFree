package com.a603.ofcourse.global.exception.dto;

import com.a603.ofcourse.global.exception.GlobalException;
import lombok.*;
import org.springframework.http.ResponseEntity;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class GlobalExceptionResponse {
    private String message;
    private String errorCode;

    public static ResponseEntity<GlobalExceptionResponse> toResponse(GlobalException e) {
        return ResponseEntity
                .status(e.getStatusCode())
                .body(GlobalExceptionResponse.builder()
                        .errorCode(e.getErrorCode())
                        .message(e.getMessage())
                        .build()
                );

    }
}
