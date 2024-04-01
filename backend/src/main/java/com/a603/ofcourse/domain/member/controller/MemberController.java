package com.a603.ofcourse.domain.member.controller;

import com.a603.ofcourse.domain.member.dto.request.ProfileInfoRequest;
import com.a603.ofcourse.domain.member.service.ProfileService;
import com.a603.ofcourse.domain.oauth.service.JwtTokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/member")
public class MemberController {
    public static final String AUTHORIZATION_HEADER = "Authorization";
    private final ProfileService profileService;
    private final JwtTokenService jwtTokenService;

    /*
    작성자 : 김은비
    내용 : 닉네임 증복 검사
     * @param nickName
     * @return 사용 가능하면 OK, 불가하면 CONFLICK
     */
    @GetMapping("/check/nickname")
    public ResponseEntity<Void> checkNickName(@RequestParam("nickName") String nickName) {
        if(profileService.checkNickName(nickName)){
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
        return ResponseEntity.ok().build();
    }

    /*
    작성자 : 김은비
    내용 : 닉네임을 해쉬맵에 잠시 저장
     * @param
     * @return
     */
//    @GetMapping("/transient-save/nickname")


    /*
    작성자 : 김은비
    내용 : 프로필 정보 저장
     * @param : Request
     * @return :
     */
    @PostMapping("/profile-info")
    public ResponseEntity<Void> saveMemberProfile(@RequestHeader(AUTHORIZATION_HEADER) String accessToken, @RequestBody ProfileInfoRequest profileInfoRequest){
        //1.멤버아이디 가져오기
        Integer memberId = (Integer) jwtTokenService.getPayload(accessToken.substring(7)).get("member_id");

        return ResponseEntity.ok().build();
    }
}
