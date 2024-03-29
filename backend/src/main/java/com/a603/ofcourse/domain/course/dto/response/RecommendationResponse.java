package com.a603.ofcourse.domain.course.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class RecommendationResponse {
    private Integer firstCourseId;
    private Integer secondCourseId;
}
