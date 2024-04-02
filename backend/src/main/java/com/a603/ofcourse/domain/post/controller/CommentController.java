package com.a603.ofcourse.domain.post.controller;


import com.a603.ofcourse.domain.post.dto.CommentRequestDto;
import com.a603.ofcourse.domain.post.dto.CommentResponseDto;
import com.a603.ofcourse.domain.post.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/comment")
public class CommentController {

    private final CommentService commentService;

    @PostMapping("/save")
    public ResponseEntity<Integer> saveComment(@RequestHeader("Authorization") String accessToken,
                                               @RequestBody CommentRequestDto requestDto){
        return ResponseEntity.ok(commentService.save(accessToken, requestDto));
    }

    @GetMapping
    public ResponseEntity<List<CommentResponseDto>> findComment(@RequestParam Integer postId){
        return ResponseEntity.ok(commentService.findComment(postId));
    }
}
