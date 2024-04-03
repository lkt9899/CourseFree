package com.a603.ofcourse.domain.schedule.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UpdateScheduleRequestDto {
    private int scheduleId;
    private int courseId;
    private String scheduleTitle;
    private String appointmentPlace;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime scheduleDate;

    @Builder
    public UpdateScheduleRequestDto(
            int scheduleId,
            int courseId,
            String scheduleTitle,
            String appointmentPlace,
            LocalDateTime scheduleDate
    ) {
        this.scheduleId = scheduleId;
        this.courseId = courseId;
        this.scheduleTitle = scheduleTitle;
        this.appointmentPlace = appointmentPlace;
        this.scheduleDate = scheduleDate;
    }
}
