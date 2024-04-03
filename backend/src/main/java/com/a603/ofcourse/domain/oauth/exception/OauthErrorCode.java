package com.a603.ofcourse.domain.oauth.exception;

import com.a603.ofcourse.global.exception.ErrorCode;
import lombok.Getter;

@Getter
public enum OauthErrorCode implements ErrorCode {
    UNAUTHORIZED(401,"OAUTH_01", "인증되지 않은 요청입니다."),
    INVALID_ACCESS_TOKEN(401, "OAUTH_02", "유효하지 않은 액세스 토큰입니다."),
    INVALID_REFRESH_TOKEN(401, "OAUTH_03", "유효하지 않은 리프레시 토큰입니다."),
    BAD_REQUEST(400, "OAUTH_04","잘못된 요청입니다."),
    NOT_EXIST_MEMBER(401, "OAUTH_05", "존재하지 않는 유저입니다.");

    private final int statusCode;
    private final String errorCode;
    private final String message;

    OauthErrorCode(int statusCode, String errorCode, String message){
        this.statusCode = statusCode;
        this.errorCode = errorCode;
        this.message = message;
    }
}
