package com.a603.ofcourse.domain.post.dto;

import lombok.*;

import java.util.List;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Getter
@Builder
public class PostRequestDto {

    private Integer courseId;
    private String postTitle;
    private List<PostContentInfo> postContentInfoList;

    @Data @Builder
    public static class PostContentInfo{
        private String title;
        private String content;
    }
}
