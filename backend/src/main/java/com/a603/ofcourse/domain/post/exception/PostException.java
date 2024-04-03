package com.a603.ofcourse.domain.post.exception;

import com.a603.ofcourse.global.exception.ErrorCode;
import com.a603.ofcourse.global.exception.GlobalException;

public class PostException extends GlobalException {
    public PostException(ErrorCode errorCode) {
        super(errorCode);
    }
}
