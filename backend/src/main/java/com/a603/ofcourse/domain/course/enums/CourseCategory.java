package com.a603.ofcourse.domain.course.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;

@Getter
@RequiredArgsConstructor
public enum CourseCategory {
    TMP_CATEGORY("임시용 카테고리"),
    TMP_CATEGORY2("임시용 카테고리2"),
    ;

    private final String value;

    public static CourseCategory from(String value) {
        return Arrays.stream(CourseCategory.values())
                .filter(category -> category.getValue().equals(value))
                .findFirst()
                .orElseThrow();
    }
}
