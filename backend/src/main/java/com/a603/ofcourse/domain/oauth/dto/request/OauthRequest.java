package com.a603.ofcourse.domain.oauth.dto.request;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/*
로그인 Api RequestBody
 */
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class OauthRequest {
    private String code;

    @Builder
    public OauthRequest(String code) {
        this.code = code;
    }
}
