package com.a603.ofcourse.domain.search.dto.response;

import com.a603.ofcourse.global.common.Points;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SubwayDetailResponseDto {
    private String line;
    private String stationName;
    private Points point;

    @Builder
    public SubwayDetailResponseDto(String line, String stationName, Points point) {
        this.line = line;
        this.stationName = stationName;
        this.point = point;
    }
}
