package com.a603.ofcourse.domain.course.controller;

import com.a603.ofcourse.domain.course.dto.request.AddCourseReviewRequestDto;
import com.a603.ofcourse.domain.course.dto.request.UpdateCourseReviewRequestDto;
import com.a603.ofcourse.domain.course.dto.response.CourseReviewResponseDto;
import com.a603.ofcourse.domain.course.service.CourseReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/course-review")
public class CourseReviewController {
    private final CourseReviewService courseReviewService;

    @PostMapping("/write")
    public ResponseEntity<Integer> addNewCourseReview(
            @RequestHeader("Authorization") String token,
            @RequestBody AddCourseReviewRequestDto addCourseReviewRequestDto
    ) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(courseReviewService.addNewCourseReview(token, addCourseReviewRequestDto));
    }

    @GetMapping
    public ResponseEntity<List<CourseReviewResponseDto>> findCourseReviewList(@RequestParam int courseId) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(courseReviewService.findCourseReviewList(courseId));
    }

    @PutMapping
    public ResponseEntity<Void> updateCourseReview(@RequestBody UpdateCourseReviewRequestDto updateCourseReviewRequestDto) {
        courseReviewService.updateCourseReview(updateCourseReviewRequestDto);
        return ResponseEntity
                .status(HttpStatus.OK)
                .build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCourseReview(@PathVariable Integer id) {
        courseReviewService.deleteCourseReview(id);
        return ResponseEntity
                .status(HttpStatus.OK)
                .build();
    }
}
