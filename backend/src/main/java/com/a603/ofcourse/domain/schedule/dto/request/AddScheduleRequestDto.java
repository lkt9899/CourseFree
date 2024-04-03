package com.a603.ofcourse.domain.schedule.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class AddScheduleRequestDto {
    private Integer courseId;
    private String scheduleTitle;
    private String appointmentPlace;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime scheduleDate;
}
