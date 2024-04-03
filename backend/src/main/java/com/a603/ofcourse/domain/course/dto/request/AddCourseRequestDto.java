package com.a603.ofcourse.domain.course.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class AddCourseRequestDto {
    private String courseTitle;
    private List<Integer> placeIdList;
}
