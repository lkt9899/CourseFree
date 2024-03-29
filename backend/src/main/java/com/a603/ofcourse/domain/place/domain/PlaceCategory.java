package com.a603.ofcourse.domain.place.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum PlaceCategory {
    RESTAURANT("식당"),
    CAFE("카페"),
    THEATER("영화관"),
    BOARD_GAME("보드게임카페"),
    PC_ROOM("피씨방"),
    MALL("쇼핑몰");

    private final String value;
}
