package com.a603.ofcourse.domain.member.domain.enums;

public enum Role {
    MEMBER("member"),
    ADMIN("admin");

    private final String role;

    Role(String role){
        this.role = role;
    }

    public String getRole(){
        return role;
    }
}
