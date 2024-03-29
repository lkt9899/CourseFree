package com.a603.ofcourse.domain.course.service;

import com.a603.ofcourse.domain.course.domain.Course;
import com.a603.ofcourse.domain.course.domain.CoursePlace;
import com.a603.ofcourse.domain.course.domain.MyCourse;
import com.a603.ofcourse.domain.course.dto.request.AddCourseRequestDto;
import com.a603.ofcourse.domain.course.dto.request.RecommendationRequest;
import com.a603.ofcourse.domain.course.dto.response.RecommendationApiResponse;
import com.a603.ofcourse.domain.course.dto.response.RecommendationResponse;
import com.a603.ofcourse.domain.course.repository.CoursePlaceRepository;
import com.a603.ofcourse.domain.course.repository.CourseRepository;
import com.a603.ofcourse.domain.course.repository.MyCourseRepository;
import com.a603.ofcourse.domain.course.util.CourseMaker;
import com.a603.ofcourse.domain.member.domain.Member;
import com.a603.ofcourse.domain.place.domain.Place;
import com.a603.ofcourse.domain.place.dto.PlaceDto;
import com.a603.ofcourse.domain.place.service.PlaceService;
import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;

import jakarta.inject.Provider;
import java.util.*;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class CourseService {
    private final PlaceService placeService;
    private final CourseRepository courseRepository;
    private final MyCourseRepository myCourseRepository;
    private final CoursePlaceRepository coursePlaceRepository;

    private final Provider<CourseMaker> courseMakerProvider;

    @Value("${course.recommendation.url}")
    private String courseRecommendationUrl;

    /**
     * @author 손현조
     * @date 2024-03-21
     * @description 코스를 등록하는 함수, 해시 키 중복 비교 후 저장을 실행
     **/
    @Transactional
    public Integer addCourse(Member member, AddCourseRequestDto requestDto) {
        String hashKey = getHashKey(requestDto.getPlaceIdList());
        return courseRepository.findByHashKey(hashKey)
                .map(course -> addMyCourse(member, course))
                .orElseGet(() -> addNewCourse(member, requestDto, hashKey));
    }

    /**
     * @author 손현조
     * @date 2024-03-21
     * @description 멤버와 코스 연관 관계 추가
     **/
    private Integer addMyCourse(Member member, Course course) {
        MyCourse myCourse = new MyCourse(member, course);
        myCourseRepository.save(myCourse);
        return course.getId();
    }

    /**
     * @author 손현조
     * @date 2024-03-21
     * @description 새로운 데이트 코스를 생성
     **/
    private Integer addNewCourse(Member member, AddCourseRequestDto requestDto, String hashKey) {
        Course newCourse = Course.builder()
                .title(requestDto.getCourseTitle())
                .hashKey("")
                .build();
        newCourse.updateHashKey(hashKey);
        addMyCourse(member, newCourse);
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
            hashKeyBuilder.append(placeId).append(":");
        }
        if (!hashKeyBuilder.isEmpty()) hashKeyBuilder.deleteCharAt(hashKeyBuilder.length() - 1);
        return hashKeyBuilder.toString();
    }

    /**
     * @author 손현조
     * @date 2024-03-29
     * @description 코스에 장소들을 추가한 후 코스를 저장
     **/
    private void addPlaceInCourse(Course course, List<Integer> placeIdList) {
        for (Integer placeId : placeIdList) {
            Place place = placeService.findById(placeId);
            CoursePlace coursePlace = new CoursePlace(course, place);
            coursePlaceRepository.save(coursePlace);
        }
    }

    /**
     * @author 손현조
     * @date 2024-03-29
     * @description 추천 코스 제공 함수
     * @Param request : {좌표, 설정 거리, 카테고리 목록}
     **/
    public RecommendationResponse recommendCourse(Member member, RecommendationRequest request) {
        Map<String, List<PlaceDto>> categoryType = new HashMap<>();
        Double[] memberVector = member.getProfile().getDoubleVector();
        for (String category : request.getCategoryList()) {
            if (!categoryType.containsKey(category)) {
                categoryType.put(category, fetchRecommendationApi(memberVector, category, request));
            }
        }
        CourseMaker courseMaker = courseMakerProvider.get();
        Integer first = addCourse(member, courseMaker.makeFirstCourse(request.getCategoryList(), categoryType));
        Integer second = addCourse(member, courseMaker.makeSecondCourse(request.getCategoryList(), categoryType));
        return new RecommendationResponse(first, second);
    }

    /**
     * @author 손현조
     * @date 2024-03-29
     * @description 추천 서버(FastAPI)에 호출하여 추천 장소들 불러오는 함수
     * @Param vector : 사용자의 선호도 벡터
     **/
    private List<PlaceDto> fetchRecommendationApi(Double[] vector, String category, RecommendationRequest request) {
        WebClient webClient = WebClient.builder().baseUrl(courseRecommendationUrl).build();
        return webClient.post()
                .bodyValue(getBodyMap(vector, category, request))
                .retrieve()
                .bodyToFlux(RecommendationApiResponse.class)
                .map(RecommendationApiResponse::getPlaceDto)
                .collectList()
                .block();
    }

    /**
     * @author 손현조
     * @date 2024-03-29
     * @description RequestBody 작성
     **/
    private Map<String, Object> getBodyMap(Double[] vector, String category, RecommendationRequest request) {
        Map<String, Object> bodyMap = new HashMap<>();
        bodyMap.put("member_vector", vector);
        bodyMap.put("place_category", category);
        bodyMap.put("lat", request.getPoints().getLat());
        bodyMap.put("lng", request.getPoints().getLng());
        bodyMap.put("limit_dist", request.getLimitDist());
        return bodyMap;
    }
}
