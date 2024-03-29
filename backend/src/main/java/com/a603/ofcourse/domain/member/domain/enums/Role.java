package com.a603.ofcourse.domain.member.domain.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Role {
    MEMBER("member"),
    ADMIN("admin");

    private final String value;
}
