package com.a603.ofcourse.domain.schedule.exception;

import com.a603.ofcourse.global.exception.ErrorCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ScheduleErrorCode implements ErrorCode {
    SCHEDULE_NOT_EXIST(403, "SCHEDULE_01", "존재하지 않는 스케쥴입니다."),
    ;

    private final int statusCode;
    private final String errorCode;
    private final String message;
}
