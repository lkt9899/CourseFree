package com.a603.ofcourse.domain.schedule.exception;

import com.a603.ofcourse.global.exception.ErrorCode;
import com.a603.ofcourse.global.exception.GlobalException;

public class ScheduleException extends GlobalException {
    public ScheduleException(ErrorCode errorCode) {
        super(errorCode);
    }
}
