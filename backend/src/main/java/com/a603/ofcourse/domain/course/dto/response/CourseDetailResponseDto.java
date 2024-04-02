package com.a603.ofcourse.domain.course.dto.response;

import com.a603.ofcourse.domain.place.dto.PlaceDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CourseDetailResponseDto {
    private List<PlaceDto> placeDtoList;

    public static CourseDetailResponseDto from(List<PlaceDto> placeDtoList) {
        return new CourseDetailResponseDto(placeDtoList);
    }
}
