package com.a603.ofcourse.domain.redis;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class TokenService {
    private final TokenRepository tokenRepository;

    @Transactional
    public void addToken(AddTokenRequest addTokenRequest) {
        tokenRepository.save(addTokenRequest.toEntity());
    }

    public TokenDto getToken(String id) {
        return tokenRepository.findById(id)
                .orElseThrow(RuntimeException::new)
                .toResponse();
    }
}
