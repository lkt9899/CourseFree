package com.a603.ofcourse.domain.couple.controller;

import com.a603.ofcourse.domain.couple.service.CoupleService;
import com.a603.ofcourse.domain.oauth.service.JwtTokenService;
import com.a603.ofcourse.domain.oauth.service.OauthService;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api/couple")
public class CoupleController {
    public static final String AUTHORIZATION_HEADER = "Authorization";
    private final CoupleService coupleService;
    private final OauthService oauthService;
    private final JwtTokenService jwtTokenService;

    /*
    작성자 : 김은비
    작성내용 : 초대 링크 생성
     * @param accessToken
     * @return 초대 링크
     */
    @PostMapping("/generate-link")
    public ResponseEntity<String> generateInviteLink(@RequestHeader(AUTHORIZATION_HEADER) String accessToken) {
        //1. accessToken에서 멤버아이디 가져오기
        Integer memberId = (Integer) jwtTokenService.getPayload(accessToken.substring(7)).get("member_id");
        return ResponseEntity.ok(coupleService.generateInviteLink(memberId));
    }

    /*
    작성자 : 김은비
    작성내용 : 커플 연동해주기
     * @param accessToken
     */
    @PostMapping("/connect/exist/{uuid}")
    public ResponseEntity<Void> connectCoupleWithMember(@PathVariable("uuid") String uuid, @RequestHeader(AUTHORIZATION_HEADER) String accessToken){
        //1. accessToken에서 초대받은 멤버아이디 가져오기
        Integer visitorId = (Integer) jwtTokenService.getPayload(accessToken.substring(7)).get("member_id");
        //2. 초대한 사람의 멤버 아이디 가져오기
        Integer inviterId = coupleService.getInviterIdFromLink(uuid);
        //2. 커플 연동하고 커플 아이디 받아오기
        Integer coupleId = coupleService.connectCouple(visitorId, inviterId);

        HttpHeaders headers = new HttpHeaders();
        //커플 아이디 포함한 액세스토큰 발급
        headers.set(AUTHORIZATION_HEADER, "Bearer " + oauthService.refreshAccessTokenWithCoupleId(visitorId, coupleId));

        return ResponseEntity.ok().headers(headers).build();
    }

    /*
    작성자 : 김은비
    작성내용 : 커플 연동 끊기
     * @param accessToken
     * @return accessToken
     */
    @PostMapping("/disconnect")
    public ResponseEntity<Void> disconnectCouple(@RequestHeader(AUTHORIZATION_HEADER) String accessToken){
        //1. 클레임 가져오기
        Claims claims = jwtTokenService.getPayload(accessToken.substring(7));
        //2. 커플 아이디 뽑아오기
        Integer coupleId = jwtTokenService.getCoupleIdFromClaims(claims);
        log.info("coupleId : {}", coupleId);
        //3. 커플 연동 끊기
        coupleService.disconnectCouple(coupleId);
        //4. 멤버 아이디 뽑아오기
        Integer memberId = jwtTokenService.getMemberIdFromClaims(claims);
        HttpHeaders headers = new HttpHeaders();
        //5. 멤버 아이디만 있는 액세스토큰으로 갱신
        headers.set(AUTHORIZATION_HEADER, "Bearer " + oauthService.refreshAccessToken(memberId));

        return ResponseEntity.ok().headers(headers).build();
    }
}
