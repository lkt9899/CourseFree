package com.a603.ofcourse.domain.member.domain.enums;

public enum Type {
    KAKAO("kakao");

    private final String type;

    Type(String type){
        this.type = type;
    }

    public String getType(){
        return type;
    }
}
