package com.a603.ofcourse.domain.schedule.domain.enums;

import com.a603.ofcourse.domain.course.enums.CourseCategory;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

@Getter
@AllArgsConstructor
public enum ScheduleState {
    COMPLETE("COMPLETE"),
    TODO("TODO");

    private final String value;

    public static ScheduleState from(String value) {
        return Arrays.stream(ScheduleState.values())
                .filter(scheduleState -> scheduleState.getValue().equals(value))
                .findFirst()
                .orElseThrow();
    }
}
