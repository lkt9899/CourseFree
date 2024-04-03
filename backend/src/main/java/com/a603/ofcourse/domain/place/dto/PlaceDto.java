package com.a603.ofcourse.domain.place.dto;

import com.a603.ofcourse.domain.place.domain.Place;
import com.a603.ofcourse.global.common.Points;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Getter
public class PlaceDto {
    private Integer id;
    private String name;
    private String address;
    private String url;
    private String imageUrl;
    private String placeType;
    private String placeCategory;
    private Points points;

    public static PlaceDto of(Place place) {
        return new PlaceDto(
                place.getId(),
                place.getName(),
                place.getAddress(),
                place.getUrl(),
                place.getImageUrl(),
                place.getPlaceType(),
                place.getPlaceCategory().getValue(),
                place.getPoints()
        );
    }
}
