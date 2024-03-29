package com.a603.ofcourse.domain.member.exception;

import com.a603.ofcourse.global.exception.ErrorCode;
import lombok.Getter;

@Getter
public enum ProfileErrorCode implements ErrorCode {
    NICNAME_ALREADY_SAVED(409, "PROFILE_01", "이미 사용 중인 닉네임입니다.");

    private final int statusCode;
    private final String errorCode;
    private final String message;

    ProfileErrorCode(int statusCode, String errorCode, String message){
        this.statusCode = statusCode;
        this.errorCode = errorCode;
        this.message = message;
    }
}
