package com.a603.ofcourse.domain.place.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public enum PlaceCategory {
    RESTAURANT("식당"),
    CAFE("카페"),
    THEATER("영화관"),
    BOARD_CAFE("보드게임카페"),
    CARTOON_CAFE("만화카페"),
    PC_ROOM("피씨방"),
    ESCAPE_ROOM("방탈출"),
    MALL("쇼핑몰");

    private String value;
}
