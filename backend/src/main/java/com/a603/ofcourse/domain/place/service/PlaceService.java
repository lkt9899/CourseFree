package com.a603.ofcourse.domain.place.service;

import com.a603.ofcourse.domain.place.domain.Place;
import com.a603.ofcourse.domain.place.dto.PlaceDto;
import com.a603.ofcourse.domain.place.dto.request.SearchPlaceByPointsRequestDto;
import com.a603.ofcourse.domain.place.dto.response.SearchPlaceResponseDto;
import com.a603.ofcourse.domain.place.exception.PlaceErrorCode;
import com.a603.ofcourse.domain.place.exception.PlaceException;
import com.a603.ofcourse.domain.place.repository.PlaceRepository;
import com.a603.ofcourse.global.common.Points;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PlaceService {
    private final PlaceRepository placeRepository;

    /**
     * @author 손현조
     * @date 2024-03-19
     * @description
     *  맵의 중앙 좌표 기준 limitDist 반경 안의 장소들을 반환
     *  1. 중앙 좌표에서 제일 먼 2개의 좌표를 계산한 후, BETWEEN 쿼리를 사용하여 사각형 반경의 장소들을 1차적으로 획득
     *  2. 획득한 장소들과 중앙 좌표와의 거리를 계산하여, limitDist 보다 작은 경우에 해당하는 장소들을 반환
     **/
    public SearchPlaceResponseDto findPlaceListByPoints(SearchPlaceByPointsRequestDto request) {
        List<Place> placeListInBoundary = findPlaceListInBoundary(request);
        List<PlaceDto> placeDtoList = new ArrayList<>();
        for (Place place : placeListInBoundary) {
            double dist = Points.getDistance(request.getCenterPoints(), place.getPoints());
            if (dist <= request.getLimitDist()) {
                placeDtoList.add(PlaceDto.of(place));
            }
        }
        return SearchPlaceResponseDto.of(placeDtoList);
    }

    /**
     * @author 손현조
     * @date 2024-03-19
     * @description 중앙 좌표에서 제일 먼 2개의 좌표를 계산한 후, BETWEEN 쿼리를 사용하여 사각형 반경의 장소
     **/
    private List<Place> findPlaceListInBoundary(SearchPlaceByPointsRequestDto request) {
        Points centerPoints = request.getCenterPoints();
        Points minPoints = centerPoints.getMinPoints(request.getLimitDist());
        Points maxPoints = centerPoints.getMaxPoints(request.getLimitDist());

        return placeRepository.findPlaceListByBoundary(
                request.getPlaceCategory(),
                minPoints.getLat(),
                minPoints.getLng(),
                maxPoints.getLat(),
                maxPoints.getLng()
        );
    }

    public Place findById(Integer placeId) {
        return placeRepository.findById(placeId).orElseThrow(
                () -> new PlaceException(PlaceErrorCode.PLACE_DOES_NOT_EXISTS));
    }

}
