package com.a603.ofcourse.domain.place.controller;

import com.a603.ofcourse.domain.place.dto.request.SearchPlaceByPointsRequestDto;
import com.a603.ofcourse.domain.place.dto.response.SearchPlaceResponseDto;
import com.a603.ofcourse.domain.place.service.PlaceService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/place")
public class PlaceController {
    private final PlaceService placeService;

    @PostMapping("/search/by-points")
    public SearchPlaceResponseDto findPlaceListByPoints(@RequestBody SearchPlaceByPointsRequestDto request) {
        return placeService.findPlaceListByPoints(request);
    }
}