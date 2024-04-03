package com.a603.ofcourse.domain.place.exception;

import com.a603.ofcourse.global.exception.ErrorCode;
import lombok.Getter;

@Getter
public enum PlaceErrorCode implements ErrorCode {
    PLACE_DOES_NOT_EXISTS(400, "PLACE_01", "존재하지 않는 장소입니다.");

    private final int statusCode;
    private final String errorCode;
    private final String message;

    PlaceErrorCode(int statusCode, String errorCode, String message){
        this.statusCode = statusCode;
        this.errorCode = errorCode;
        this.message = message;
    }
}
