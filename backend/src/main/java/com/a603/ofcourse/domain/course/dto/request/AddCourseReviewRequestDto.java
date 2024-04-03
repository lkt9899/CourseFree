package com.a603.ofcourse.domain.course.dto.request;

import lombok.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@AllArgsConstructor
public class AddCourseReviewRequestDto {
    private int courseId;
    private String content;
}
