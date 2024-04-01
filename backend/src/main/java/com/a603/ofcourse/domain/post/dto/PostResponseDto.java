package com.a603.ofcourse.domain.post.dto;

import lombok.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Getter
@Builder
public class PostResponseDto {
    private Integer postId;
    private String title;
    private String imageUrl;
}
