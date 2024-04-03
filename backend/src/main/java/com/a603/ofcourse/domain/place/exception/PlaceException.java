package com.a603.ofcourse.domain.place.exception;

import com.a603.ofcourse.global.exception.ErrorCode;
import com.a603.ofcourse.global.exception.GlobalException;

public class PlaceException extends GlobalException {
    public PlaceException(ErrorCode errorCode) {
        super(errorCode);
    }
}
