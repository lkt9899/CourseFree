package com.a603.ofcourse.domain.redis;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@RedisHash("token")
public class Token {
    @Id
    private String id;

    private String token;

    @Builder
    public Token(String id, String token) {
        this.id = id;
        this.token = token;
    }

    public TokenDto toResponse() {
        return TokenDto.builder()
                .token(token)
                .build();
    }
}
