package com.a603.ofcourse.domain.schedule.dto.response;

import com.a603.ofcourse.domain.schedule.domain.enums.ScheduleState;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ScheduleDetailResponseDto {
    private int scheduleId;
    private String scheduleTitle;
    private Integer courseId;
    private String appointmentPlace;
    private LocalDateTime scheduleDate;
    private ScheduleState scheduleState;

    @Builder
    public ScheduleDetailResponseDto(
            int scheduleId,
            String scheduleTitle,
            Integer courseId,
            String appointmentPlace,
            LocalDateTime scheduleDate,
            ScheduleState scheduleState
    ) {
        this.scheduleId = scheduleId;
        this.scheduleTitle = scheduleTitle;
        this.courseId = courseId;
        this.appointmentPlace = appointmentPlace;
        this.scheduleDate = scheduleDate;
        this.scheduleState = scheduleState;
    }
}
