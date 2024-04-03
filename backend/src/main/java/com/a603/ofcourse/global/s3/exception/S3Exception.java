package com.a603.ofcourse.global.s3.exception;

import com.a603.ofcourse.global.exception.ErrorCode;
import com.a603.ofcourse.global.exception.GlobalException;

public class S3Exception extends GlobalException {
    public S3Exception(ErrorCode errorCode) {
        super(errorCode);
    }
}
