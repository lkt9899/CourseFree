package com.a603.ofcourse.domain.schedule.dto.response;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ScheduleListResponseDto {
    private List<ScheduleDetailResponseDto> scheduleDetailResponseDtoList;

    @Builder
    public ScheduleListResponseDto(List<ScheduleDetailResponseDto> scheduleDetailResponseDtoList) {
        this.scheduleDetailResponseDtoList = scheduleDetailResponseDtoList;
    }
}
