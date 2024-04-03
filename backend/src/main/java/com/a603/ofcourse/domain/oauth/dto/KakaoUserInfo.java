package com.a603.ofcourse.domain.oauth.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Map;

/*
카카오 Api로 정보를 가져와서 담을 Dto
 */
@Getter
@AllArgsConstructor
public class KakaoUserInfo {
    private Long id;

    public KakaoUserInfo(Map<String, Object> attributes){
        this.id = Long.valueOf(attributes.get("id").toString());
    }
}