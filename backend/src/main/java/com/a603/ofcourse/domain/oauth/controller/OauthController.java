package com.a603.ofcourse.domain.oauth.controller;

import com.a603.ofcourse.domain.oauth.dto.MemberExistWithAccessToken;
import com.a603.ofcourse.domain.oauth.redis.RefreshToken;
import com.a603.ofcourse.domain.oauth.service.JwtTokenService;
import com.a603.ofcourse.domain.oauth.service.KakaoOauthService;
import com.a603.ofcourse.domain.oauth.service.OauthService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/login")
@Slf4j
public class OauthController {
    public static final String AUTHORIZATION_HEADER = "Authorization";
    private final OauthService oauthService;
    private final JwtTokenService jwtTokenService;
    private final KakaoOauthService kakaoOauthService;

    /*
    작성자 : 김은비
    작성내용 : 인가코드로 kakaoAccessToken 받아온 후 인증된 사용자에 대한 액세스 토큰을 반환
     * @param String 로그인 형식 (카카오)
     * @param code (인가코드)
     * @return accessToken(JWT)
     */
    @PostMapping("/oauth/kakao")
    public HttpEntity<Void> login(@RequestBody String code){
        HttpHeaders headers = new HttpHeaders();
        //1. 인가코드로 카카오 액세스 토큰 반환
        String kakaoAccessToken = kakaoOauthService.getKakaoAccessTokenByCode(code);

        //2, 카카오 액세스 토큰으로 우리 서버 토큰 발급
        MemberExistWithAccessToken memberExistWithAccessToken = oauthService.loginWithKakao(kakaoAccessToken);

        String accessToken = memberExistWithAccessToken.getAccessToken();
        //3. 헤더에 넣어서 프론트로 보내기
        headers.set(AUTHORIZATION_HEADER, "Bearer " + accessToken);

        //기존 회원이면
        if(memberExistWithAccessToken.isExist()){
            return ResponseEntity.ok().headers(headers).build();
        }
        //신규 회원이면
        else{
            return ResponseEntity.status(HttpStatus.CREATED).headers(headers).build();
        }
    }

    /*
    작성자 : 김은비
    작성내용 : 자동로그인
     * @param clientAccessToken
     * return 갱신된 accesToken
     */
    @PostMapping("/auto")
    public HttpEntity<Void> autoLogin(@RequestHeader(AUTHORIZATION_HEADER) String clientAccessToken){
        //1. accessToken에서 멤버아이디 가져오기
        Integer memberId = (Integer) jwtTokenService.getPayload(clientAccessToken.substring(7)).get("member_id");
        //2. HttpHeaders 객체에 리프레시 토큰으로 갱신된 액세스 토큰 넣기
        HttpHeaders headers = new HttpHeaders();
        headers.set(AUTHORIZATION_HEADER, "Bearer " + oauthService.refreshAccessToken(memberId));

        return ResponseEntity.ok().headers(headers).build();
    }
}
