package com.a603.ofcourse.domain.course.exception;

import com.a603.ofcourse.global.exception.ErrorCode;
import lombok.Getter;

@Getter
public enum CourseErrorCode implements ErrorCode {
    COURSE_DOES_NOT_EXIST(400, "COURSE_01", "존재하지 않는 코스입니다.");

    private final int statusCode;
    private final String errorCode;
    private final String message;

    CourseErrorCode(int statusCode, String errorCode, String message){
        this.statusCode = statusCode;
        this.errorCode = errorCode;
        this.message = message;
    }
}
