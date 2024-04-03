package com.a603.ofcourse.domain.course.dto.request;

import lombok.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@AllArgsConstructor
public class UpdateCourseReviewRequestDto {
    private Integer id;
    private String content;
}
