package com.a603.ofcourse.domain.post.dto;

import lombok.*;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Getter
@Builder
public class PostContentResponseDto {
    private Integer postId;
    private String postTitle;
    private String memberImageUrl;
    private String memberNickname;
    private List<PostContentInfo> postContentInfoList;
    private Integer courseId;
    private Boolean isHave;

    @Data @Builder
    public static class PostContentInfo{
        private String placeName;
        private String placeImageUrl;
        private String url;
        private String title;
        private String content;
    }

}

