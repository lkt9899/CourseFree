package com.a603.ofcourse.domain.couple.exception;

import com.a603.ofcourse.global.exception.ErrorCode;
import lombok.Getter;

@Getter
public enum CoupleErrorCode implements ErrorCode {
    ALREADY_COUPLE_MEMBER(400, "COUPLE01", "이미 커플인 회원입니다."),
    INVALID_INVITE_LINK(400, "COUPLE02", "만료되거나 존재하지 않는 초대 링크입니다."),
    SAME_MEMBER(400, "COUPLE03", "같은 사용자끼리 연동은 불가합니다.");

    private final int statusCode;
    private final String errorCode;
    private final String message;

    CoupleErrorCode(int statusCode, String errorCode, String message){
        this.statusCode = statusCode;
        this.errorCode = errorCode;
        this.message = message;
    }
}
