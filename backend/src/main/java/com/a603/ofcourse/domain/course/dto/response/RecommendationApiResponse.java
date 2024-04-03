package com.a603.ofcourse.domain.course.dto.response;

import com.a603.ofcourse.domain.place.dto.PlaceDto;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class RecommendationApiResponse {
    Double similarity;

    @JsonProperty("place")
    PlaceDto placeDto;
}
