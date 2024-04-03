package com.a603.ofcourse.domain.course.exception;

import com.a603.ofcourse.global.exception.ErrorCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum CourseErrorCode implements ErrorCode {
    COURSE_NOT_EXIST(403, "COURSE_01", "존재하지 않는 코스입니다."),
    ;


    private final int statusCode;
    private final String errorCode;
    private final String message;
}
