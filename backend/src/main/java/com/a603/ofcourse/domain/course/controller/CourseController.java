package com.a603.ofcourse.domain.course.controller;

import com.a603.ofcourse.domain.course.dto.request.AddCourseRequestDto;
import com.a603.ofcourse.domain.course.dto.request.RecommendationRequest;
import com.a603.ofcourse.domain.course.dto.response.RecommendationResponse;
import com.a603.ofcourse.domain.course.service.CourseService;
import com.a603.ofcourse.domain.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/course")
public class CourseController {
    private final CourseService courseService;
    private final MemberService memberService;

    @PostMapping("/add")
    public ResponseEntity<Integer> addCourse(
            @RequestHeader("Authorization") String token, @RequestBody AddCourseRequestDto requestDto) {
        Integer newCourseId = courseService.addCourse(memberService.getMemberByToken(token), requestDto);
        return ResponseEntity.ok(newCourseId);
    }

    @PostMapping("/recommendation")
    public ResponseEntity<RecommendationResponse> getRecommendedCourse(
            @RequestHeader("Authorization") String token, @RequestBody RecommendationRequest request) {
        return ResponseEntity.ok(courseService.recommendCourse(memberService.getMemberByToken(token), request));
    }
}