package com.a603.ofcourse.domain.member.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Gender {
    MALE("남성"),
    FEMALE("여성");

    private String gender;
}
