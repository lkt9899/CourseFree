package com.a603.ofcourse.domain.member.domain.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Type {
    KAKAO("kakao");

    private final String value;
}
