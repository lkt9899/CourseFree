package com.a603.ofcourse.domain.post.controller;

import com.a603.ofcourse.domain.post.dto.PostContentResponseDto;
import com.a603.ofcourse.domain.post.service.PostContentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/post-content")
public class PostContentController {

    private final PostContentService postContentService;

    @GetMapping
    public ResponseEntity<PostContentResponseDto> findPostContentList(@RequestHeader("Authorization") String accessToken,
                                                                      @RequestParam int postId){
        return ResponseEntity.ok(postContentService.findPostContentList(accessToken, postId));
    }
}
