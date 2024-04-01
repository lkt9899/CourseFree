package com.a603.ofcourse.domain.member.dto.request;

public class Preference {
    private String first;
    private String second;
    private String third;

    private Preference(String first, String second, String third){
        this.first = first;
        this.second = second;
        this.third = third;
    }

    public static Preference from(String first, String second, String third){
        return new Preference(first, second, third);
    }
}
