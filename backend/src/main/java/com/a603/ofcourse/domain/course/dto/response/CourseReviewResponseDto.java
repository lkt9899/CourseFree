package com.a603.ofcourse.domain.course.dto.response;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CourseReviewResponseDto {
    private Integer id;
    private String authorNickname;
    private String content;
    private LocalDateTime regDate;

    @Builder
    public CourseReviewResponseDto(
            Integer id,
            String authorNickname,
            String content,
            LocalDateTime regDate
    ) {
        this.id = id;
        this.authorNickname = authorNickname;
        this.content = content;
        this.regDate = regDate;
    }
}
