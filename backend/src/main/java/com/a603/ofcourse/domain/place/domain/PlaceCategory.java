package com.a603.ofcourse.domain.place.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum PlaceCategory {
    Restaurant("식당"),
    Cafe("카페"),
    Theater("영화관"),
    BoardGame("보드게임카페"),
    PcRoom("피씨방"),
    Mall("쇼핑몰");

    private String placeCategory;
}
