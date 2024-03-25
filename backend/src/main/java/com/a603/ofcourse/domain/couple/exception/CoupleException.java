package com.a603.ofcourse.domain.couple.exception;

import com.a603.ofcourse.global.exception.ErrorCode;
import com.a603.ofcourse.global.exception.GlobalException;

public class CoupleException extends GlobalException {

    public CoupleException(ErrorCode errorCode) {
        super(errorCode);
    }
}
