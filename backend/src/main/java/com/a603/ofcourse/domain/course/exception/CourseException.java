package com.a603.ofcourse.domain.course.exception;

import com.a603.ofcourse.global.exception.ErrorCode;
import com.a603.ofcourse.global.exception.GlobalException;

public class CourseException extends GlobalException {
    public CourseException(ErrorCode errorCode) {
        super(errorCode);
    }
}
