package com.a603.ofcourse.domain.member.dto.request;

public class ProfileInfoRequest {
    private String nickName;
    private String gender;
    private Preference preference;

    private ProfileInfoRequest(String nickName, String gender, Preference preference){
        this.nickName = nickName;
        this.gender = gender;
        this.preference = preference;
    }

    public static ProfileInfoRequest toResponse(String nickName, String gender, Preference preference){
        return new ProfileInfoRequest(nickName, gender, preference);
    }
}
