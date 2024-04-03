package com.a603.ofcourse.domain.member.exception;

import com.a603.ofcourse.global.exception.ErrorCode;
import lombok.Getter;

@Getter
public enum MemberErrorCode implements ErrorCode {
    MEMBER_DOES_NOT_EXISTS(400, "MEMBER_01", "존재하지 않는 회원입니다.");

    private final int statusCode;
    private final String errorCode;
    private final String message;

    MemberErrorCode(int statusCode, String errorCode, String message){
        this.statusCode = statusCode;
        this.errorCode = errorCode;
        this.message = message;
    }
}
