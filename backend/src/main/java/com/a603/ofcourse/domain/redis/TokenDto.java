package com.a603.ofcourse.domain.redis;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class TokenDto {
    private String token;

    @Builder
    public TokenDto(String token) {
        this.token = token;
    }
}
