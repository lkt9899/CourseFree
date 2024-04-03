package com.a603.ofcourse.domain.post.dto;

import lombok.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Getter
@Builder
public class CommentRequestDto {
    private Integer postId;
    private String content;
}
