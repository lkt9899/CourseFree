package com.a603.ofcourse.domain.member.exception;

import com.a603.ofcourse.global.exception.ErrorCode;
import com.a603.ofcourse.global.exception.GlobalException;

public class MemberException extends GlobalException {
    public MemberException(ErrorCode errorCode){
        super(errorCode);
    }
}
