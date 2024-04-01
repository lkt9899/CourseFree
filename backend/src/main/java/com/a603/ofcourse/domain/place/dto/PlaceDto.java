package com.a603.ofcourse.domain.place.dto;

import com.a603.ofcourse.domain.place.domain.Place;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Getter
public class PlaceDto {
    private String name;
    private String imageUrl;
    private Integer reviewCount;

    public static PlaceDto of(Place place) {
        return new PlaceDto(
                place.getName(),
                place.getImageUrl(),
                place.getReviewCount()
        );
    }
}
