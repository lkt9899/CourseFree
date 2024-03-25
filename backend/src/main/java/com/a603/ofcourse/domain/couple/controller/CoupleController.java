package com.a603.ofcourse.domain.couple.controller;

import com.a603.ofcourse.domain.couple.redis.InviteLink;
import com.a603.ofcourse.domain.couple.service.CoupleService;
import com.a603.ofcourse.domain.oauth.service.JwtTokenService;
import com.a603.ofcourse.domain.oauth.service.OauthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/couple")
public class CoupleController {
    public static final String AUTHORIZATION_HEADER = "Authorization";
    private final CoupleService coupleService;
    private final JwtTokenService jwtTokenService;

    /*
    작성자 : 김은비
    작성내용 : 초대 링크 생성
     */
    @PostMapping("/generate-link/{memberId}")
    public ResponseEntity<String> generateInviteLink(@PathVariable Integer memberId) {
        return ResponseEntity.ok(coupleService.generateInviteLink(memberId));
    }

    /*
    작성자 : 김은비
    작성내용 : 커플 연동해주기
     * @param accessToken
     * @return
     */
    @PostMapping("/connect/exist/{UUID}")
    public ResponseEntity<Void> connectCoupleWithMember(@PathVariable("UUID") String UUID, @RequestHeader(AUTHORIZATION_HEADER) String accessToken){
        //1. accessToken에서 초대받은 멤버아이디 가져오기
        Integer visitorId = (Integer) jwtTokenService.getPayload(accessToken.substring(7)).get("member_id");
        //2. 초대한 사람의 멤버 아이디 가져오기
        Integer inviterId = coupleService.getInviterIdFromLink(UUID);
        //2. 커플 연동
        coupleService.connectCouple(visitorId, inviterId);

        return ResponseEntity.ok().build();
    }
}
