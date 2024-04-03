package com.a603.ofcourse.domain.course.util;

import com.a603.ofcourse.domain.course.dto.request.AddCourseRequestDto;
import com.a603.ofcourse.domain.place.dto.PlaceDto;
import com.a603.ofcourse.domain.place.service.PlaceService;
import com.a603.ofcourse.global.common.Points;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Scope("prototype")
@Component
@RequiredArgsConstructor
public class CourseMaker {
    private final PlaceService placeService;

    private int size;
    private double minDist;
    private List<String> categoryList;
    private List<Integer> secondCourse;
    private Map<String, boolean[]> visMap;
    Map<String, List<PlaceDto>> categoryType;

    private static final String RECOMMENDED_COURSE = "추천 코스";


    /**
     * @author 손현조
     * @date 2024-03-29
     * @description 첫번 째 추천 코스(사용자 선호도순으로 가장 적합한 코스) 제공 함수
     **/
    public AddCourseRequestDto makeFirstCourse(List<String> categoryList, Map<String, List<PlaceDto>> categoryType) {
        Map<String, Integer> index = new HashMap<>();
        for (String category : categoryType.keySet()) {
            index.put(category, 0);
        }
        List<Integer> placeIdList = new ArrayList<>();
        for (String category : categoryList) {
            placeIdList.add(categoryType.get(category).get(index.get(category)).getId());
            index.put(category, index.get(category) + 1);
        }
        return new AddCourseRequestDto(RECOMMENDED_COURSE, placeIdList);
    }

    /**
     * @author 손현조
     * @date 2024-03-29
     * @description 두번 째 추천 코스(최단 거리 코스) 제공 함수
     **/
    public AddCourseRequestDto makeSecondCourse(List<String> categoryList, Map<String, List<PlaceDto>> categoryType) {
        init(categoryList, categoryType);
        for (String category : categoryType.keySet()) {
            visMap.put(category, new boolean[5]);
        }
        Permutation(0, new int[size]);
        return new AddCourseRequestDto(RECOMMENDED_COURSE, secondCourse);
    }

    /**
     * @author 손현조
     * @date 2024-03-29
     * @description 순열을 통해 최단 거리 코스 찾기
     **/
    private void Permutation(int L, int[] placeArr) {
        if (L == size) {
            updateMinDist(placeArr);
            return;
        }
        for (int i = 0; i < size; i++) {
            if (visMap.get(categoryList.get(L))[i]) continue;

            visMap.get(categoryList.get(L))[i] = true;
            placeArr[L] = categoryType.get(categoryList.get(L)).get(i).getId();
            Permutation(L + 1, placeArr);
            visMap.get(categoryList.get(L))[i] = false;
        }
    }

    /**
     * @author 손현조
     * @date 2024-03-29
     * @description 두번 째 코스(최단거리 코스) 획득용 초기화
     **/
    private void init(List<String> categoryList, Map<String, List<PlaceDto>> categoryType) {
        visMap = new HashMap<>();
        size = categoryList.size();
        minDist = Double.MAX_VALUE;

        this.categoryList = categoryList;
        this.categoryType = categoryType;

        secondCourse = new ArrayList<>();
    }

    /**
     * @author 손현조
     * @date 2024-03-29
     * @description 최소 거리 갱신 및 최소 거리인 코스 갱신
     **/
    private void updateMinDist(int[] placeArr) {
        double totalDist = 0;
        for (int i = 1; i < size; i++) {
            Points points1 = placeService.findById(placeArr[i - 1]).getPoints();
            Points points2 = placeService.findById(placeArr[i]).getPoints();
            totalDist += Points.getDistance(points1, points2);
        }
        if (totalDist < minDist) {
            minDist = totalDist;
            updateSecondCourse(placeArr);
        }
    }

    /**
     * @author 손현조
     * @date 2024-03-29
     * @description 두번 째 추천 코스 대상을 업데이트하는 함수
     **/
    private void updateSecondCourse(int[] placeArr) {
        secondCourse = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            secondCourse.add(placeArr[i]);
        }
    }
}
