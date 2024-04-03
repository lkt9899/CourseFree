package com.a603.ofcourse.domain.course.service;

import com.a603.ofcourse.domain.course.domain.Course;
import com.a603.ofcourse.domain.course.domain.CourseReview;
import com.a603.ofcourse.domain.course.dto.request.AddCourseReviewRequestDto;
import com.a603.ofcourse.domain.course.dto.request.UpdateCourseReviewRequestDto;
import com.a603.ofcourse.domain.course.dto.response.CourseReviewResponseDto;
import com.a603.ofcourse.domain.course.repository.CourseRepository;
import com.a603.ofcourse.domain.course.repository.CourseReviewRepository;
import com.a603.ofcourse.domain.member.exception.MemberErrorCode;
import com.a603.ofcourse.domain.member.exception.MemberException;
import com.a603.ofcourse.domain.member.repository.MemberRepository;
import com.a603.ofcourse.domain.oauth.service.JwtTokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CourseReviewService {
    private final CourseReviewRepository courseReviewRepository;
    private final MemberRepository memberRepository;
    private final CourseRepository courseRepository;
    private final JwtTokenService jwtTokenService;

    /**
     * 리뷰 작성 함수
     * @param token JWT
     * @param addCourseReviewRequestDto 작성할 리뷰 데이터
     */
    @Transactional
    public int addNewCourseReview(String token, AddCourseReviewRequestDto addCourseReviewRequestDto) {
        String authorNickname = memberRepository.findById((Integer) jwtTokenService.getPayload(token).get("member_id"))
                .orElseThrow(() -> new MemberException(MemberErrorCode.MEMBER_DOES_NOT_EXISTS))
                .getProfile().getNickname();

        Course course = courseRepository.findById(addCourseReviewRequestDto.getCourseId())
                .orElseThrow();

        return courseReviewRepository.save(CourseReview.builder()
                .authorNickname(authorNickname)
                .course(course)
                .content(addCourseReviewRequestDto.getContent())
                .build()).getId();
    }

    /**
     * 리뷰 글 전체 조회
     * @return List<CourseReviewResponseDto> 라뷰 전체 목록
     */
    public List<CourseReviewResponseDto> findCourseReviewList(int courseId) {
        return courseReviewRepository.findByCourseId(courseId).stream()
                .map(CourseReview::toResponse)
                .toList();
    }

    /**
     * 리뷰 수정 함수
     * @param updateCourseReviewRequestDto 수정할 리뷰 데이터
     */
    @Transactional
    public void updateCourseReview(UpdateCourseReviewRequestDto updateCourseReviewRequestDto) {
        // 업데이트할 리뷰 조회
        CourseReview courseReview = courseReviewRepository.findById(updateCourseReviewRequestDto.getId())
                .orElseThrow();

        // 더티 체크 활용
        courseReview.updateContent(updateCourseReviewRequestDto.getContent());
    }

    /**
     * 리뷰 삭제 함수
     * @param id    삭제할 리뷰 ID
     */
    @Transactional
    public void deleteCourseReview(Integer id) {
        courseReviewRepository.deleteById(id);
    }
}