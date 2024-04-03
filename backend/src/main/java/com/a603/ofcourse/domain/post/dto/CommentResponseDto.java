package com.a603.ofcourse.domain.post.dto;

import lombok.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Getter
@Builder
public class CommentResponseDto {
    private String memberImageUrl;
    private String memberNickname;
    private String content;
}
