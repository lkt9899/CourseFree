package com.a603.ofcourse.domain.course.service;

import com.a603.ofcourse.domain.course.domain.Course;
import com.a603.ofcourse.domain.course.domain.CoursePlace;
import com.a603.ofcourse.domain.course.domain.MyCourse;
import com.a603.ofcourse.domain.course.dto.request.AddCourseRequestDto;
import com.a603.ofcourse.domain.course.repository.CoursePlaceRepository;
import com.a603.ofcourse.domain.course.repository.CourseRepository;
import com.a603.ofcourse.domain.course.repository.MyCourseRepository;
import com.a603.ofcourse.domain.member.domain.Member;
import com.a603.ofcourse.domain.member.service.MemberService;
import com.a603.ofcourse.domain.oauth.service.JwtTokenService;
import com.a603.ofcourse.domain.place.domain.Place;
import com.a603.ofcourse.domain.place.service.PlaceService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CourseService {
    private final PlaceService placeService;
    private final MemberService memberService;
    private final CourseRepository courseRepository;
    private final MyCourseRepository myCourseRepository;
    private final CoursePlaceRepository coursePlaceRepository;
    private final JwtTokenService jwtTokenService;

    /**
     * @author 손현조
     * @date 2024-03-21
     * @description 코스를 등록하는 함수, 해시 키 중복 비교 후 저장을 실행
     **/@Transactional
    public Integer addCourse(String token, AddCourseRequestDto requestDto) {
        String hashKey = getHashKey(requestDto.getPlaceIdList());
        return courseRepository.findByHashKey(hashKey)
                .map(course -> addMyCourse(token, course))
                .orElseGet(() -> addNewCourse(token, requestDto, hashKey));
    }

    /**
     * @author 손현조
     * @date 2024-03-21
     * @description 멤버와 코스 연관 관계 추가
     **/private Integer addMyCourse(String token, Course course) {
        Member member = memberService.findById((Integer) jwtTokenService.getPayload(token).get("member_id"));
        MyCourse myCourse = new MyCourse(member, course);
        myCourseRepository.save(myCourse);
        return course.getId();
    }

    /**
     * @author 손현조
     * @date 2024-03-21
     * @description 새로운 데이트 코스를 생성
     **/
    private Integer addNewCourse(String token, AddCourseRequestDto requestDto, String hashKey) {
        Course newCourse = Course.builder()
                .title(requestDto.getCourseTitle())
                .hashKey("")
                .build();
        newCourse.updateHashKey(hashKey);
        addMyCourse(token, newCourse);
        addPlaceInCourse(newCourse, requestDto.getPlaceIdList());
        return courseRepository.save(newCourse).getId();
    }

    /**
     * @author 손현조
     * @date 2024-03-21
     * @description 장소들의 아이디값을 조합하여 해시 키 생성
     **/
    private String getHashKey(List<Integer> placeIdList) {
        StringBuilder hashKeyBuilder = new StringBuilder();
        for (Integer placeId : placeIdList) {
            hashKeyBuilder.append(":").append(placeId);
        }
        return hashKeyBuilder.toString();
    }

    private void addPlaceInCourse(Course course, List<Integer> placeIdList) {
        for (Integer placeId : placeIdList) {
            Place place = placeService.findById(placeId);
            CoursePlace coursePlace = new CoursePlace(course, place);
            coursePlaceRepository.save(coursePlace);
        }
    }
}
