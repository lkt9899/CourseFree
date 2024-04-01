package com.a603.ofcourse.domain.redis;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class AddTokenRequest {
    private String id;
    private String token;

    public Token toEntity() {
        return Token.builder()
                .id(id)
                .token(token)
                .build();
    }
}
