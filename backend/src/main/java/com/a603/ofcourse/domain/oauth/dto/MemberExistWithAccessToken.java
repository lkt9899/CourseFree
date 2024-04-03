package com.a603.ofcourse.domain.oauth.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemberExistWithAccessToken {
    private String accessToken;
    private boolean isExist;

    private MemberExistWithAccessToken(String accessToken, boolean isExist){
        this.accessToken = accessToken;
        this.isExist = isExist;
    }

    public static MemberExistWithAccessToken from(String accessToken, boolean isExist){
        return new MemberExistWithAccessToken(accessToken, isExist);
    }
}
