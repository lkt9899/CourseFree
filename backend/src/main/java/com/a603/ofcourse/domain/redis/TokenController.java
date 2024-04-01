package com.a603.ofcourse.domain.redis;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/redis")
public class TokenController {
    private final TokenService tokenService;

    @PostMapping("/create")
    public ResponseEntity<Void> addToken(@RequestBody AddTokenRequest addTokenRequest) {
        tokenService.addToken(addTokenRequest);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<TokenDto> getToken(@PathVariable String id) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(tokenService.getToken(id));
    }
}