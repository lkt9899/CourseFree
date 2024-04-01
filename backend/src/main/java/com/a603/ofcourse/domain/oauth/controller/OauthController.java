package com.a603.ofcourse.domain.oauth.controller;

import com.a603.ofcourse.domain.couple.service.CoupleService;
import com.a603.ofcourse.domain.oauth.dto.MemberExistWithAccessToken;
import com.a603.ofcourse.domain.oauth.dto.request.OauthRequest;
import com.a603.ofcourse.domain.oauth.service.JwtTokenService;
import com.a603.ofcourse.domain.oauth.service.KakaoOauthService;
import com.a603.ofcourse.domain.oauth.service.OauthService;
import io.jsonwebtoken.Claims;
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
    private final CoupleService coupleService;

    /*
    작성자 : 김은비
    작성내용 : 인가코드로 kakaoAccessToken 받아온 후 인증된 사용자에 대한 액세스 토큰을 반환
     * @param String 로그인 형식 (카카오)
     * @param code (인가코드)
     * @return accessToken(JWT)
     */
    @PostMapping("/oauth/kakao")
    public HttpEntity<Void> login(@RequestBody OauthRequest oauthRequest){
        log.info("\ncode : {}", oauthRequest.getCode());
        HttpHeaders headers = new HttpHeaders();
        //1. 인가코드로 카카오 액세스 토큰 반환
        String kakaoAccessToken = kakaoOauthService.getKakaoAccessTokenByCode(oauthRequest);
        log.info("\ntoken :: {}", kakaoAccessToken);
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
        Claims claims = jwtTokenService.getPayload(clientAccessToken.substring(7));
        //1. accessToken의 페이로드에 저장돼있는 id 가져오기
        Integer memberId = jwtTokenService.getMemberIdFromClaims(claims);
        //2. 리프레시 토큰으로 갱신된 액세스 토큰 넣을 HttpHeaders
        HttpHeaders headers = new HttpHeaders();
        //토큰에 커플아이디가 있으면
        if(jwtTokenService.hasCoupleId(claims)){
            Integer coupleId = jwtTokenService.getCoupleIdFromClaims(claims);
            headers.set(AUTHORIZATION_HEADER, "Bearer " + oauthService.refreshAccessTokenWithCoupleId(memberId, coupleId));
        }
        //토큰에 커플 아이디 없으면
        else{
            //커플인지 확인
            coupleService.getMemberCouple(memberId).ifPresentOrElse(
                    //커플이면
                    memberCouple -> headers.set(AUTHORIZATION_HEADER, "Bearer " + oauthService.refreshAccessTokenWithCoupleId(memberId, memberCouple.getCouple().getId())),
                    //커플 아니면
                    () -> headers.set(AUTHORIZATION_HEADER, "Bearer " + oauthService.refreshAccessToken(memberId))
            );
        }
        return ResponseEntity.ok().headers(headers).build();
    }
}
