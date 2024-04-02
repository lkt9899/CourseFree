package com.a603.ofcourse.domain.post.controller;

import com.a603.ofcourse.domain.post.dto.PostRequestDto;
import com.a603.ofcourse.domain.post.dto.PostResponseDto;
import com.a603.ofcourse.domain.post.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/post")
public class PostController {

    private final PostService postService;

    @PostMapping("/save")
    public ResponseEntity<Integer> savePost(@RequestHeader("Authorization") String accessToken, @RequestBody PostRequestDto requestDto){
        return ResponseEntity.ok(postService.save(accessToken, requestDto));
    }

    @GetMapping("/recommend")
    public ResponseEntity<List<PostResponseDto>> findPostListByRecommend(@RequestHeader("Authorization") String accessToken){
        return ResponseEntity.ok(postService.findPostListByRecommend(accessToken));
    }

    @GetMapping("/recent")
    public ResponseEntity<List<PostResponseDto>> findPostListByRecent(@RequestParam int offset){
        return ResponseEntity.ok(postService.findPostListByRecent(offset));
    }

    @GetMapping("/wish-list")
    public ResponseEntity<List<PostResponseDto>> findPostListByWishList(@RequestParam int offset){
        return ResponseEntity.ok(postService.findPostListByWishList(offset));
    }

}
