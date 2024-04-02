package com.a603.ofcourse.domain.member.controller;

import com.a603.ofcourse.domain.member.domain.Member;
import com.a603.ofcourse.domain.member.dto.request.CoupleInfoRequest;
import com.a603.ofcourse.domain.member.dto.request.MemberInfoRequest;
import com.a603.ofcourse.domain.member.dto.request.ProfileInfoRequest;
import com.a603.ofcourse.domain.member.dto.response.MemberInfoResponse;
import com.a603.ofcourse.domain.member.dto.response.MyFavoriteCourseListResponse;
import com.a603.ofcourse.domain.member.dto.response.CoursePlaceListResponse;
import com.a603.ofcourse.domain.member.service.MemberService;
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
    private final JwtTokenService jwtTokenService;
    private final ProfileService profileService;
    private final MemberService memberService;

    /*
    작성자 : 김은비
    내용 : 닉네임 증복 검사
     * @param nickname
     * @return 사용 가능하면 OK, 불가하면 CONFLICK
     */
    @GetMapping("/check/nickname")
    public ResponseEntity<Void> checkNickName(@RequestParam("nickname") String nickname) {
        //저장 불가하면
        if(profileService.isDuplicateNickName(nickname)){
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
        return ResponseEntity.ok().build();
    }

    /*
    작성자 : 김은비
    내용 : 닉네임을 해쉬맵에 잠시 저장
     * @param nickname
     * @param accessToken
     * @return 저장 성공하면 OK, 실패하면 CONFLICK
     */
    @GetMapping("/transient-save/nickname")
    public ResponseEntity<Void> saveNicknameToHashMap(@RequestParam("nickname") String nickname, @RequestHeader(AUTHORIZATION_HEADER) String accessToken){
        Integer memberId = jwtTokenService.getMemberId(accessToken);
        if(!profileService.saveNicknameToHashMap(nickname, memberId)){
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
        return ResponseEntity.ok().build();
    }

    /*
    작성자 : 김은비
    작성내용 : 임시 저장한 닉네임 삭제
     * @param nickname
     */
    @GetMapping("transient-delete/nickname")
    public ResponseEntity<Void> deleteNicknameFromHashMap(@RequestParam("nickname") String nickname){
        profileService.deleteNicknameFromHashMap(nickname);
        return ResponseEntity.ok().build();
    }

    /*
    작성자 : 김은비
    내용 : 프로필 정보 저장
     * @param accessToken
     * @param Request
     */
    @PostMapping("/profile-info")
    public ResponseEntity<Void> saveMemberProfile(@RequestHeader(AUTHORIZATION_HEADER) String accessToken, @RequestBody ProfileInfoRequest profileInfoRequest){
        Member member = memberService.getMemberByToken(accessToken);

        profileService.saveMemberProfile(member, profileInfoRequest);
        return ResponseEntity.ok().build();
    }

    /*
    작성자 : 김은비
    작성내용 : 내가 찜한 코스 가져오기
     * @param accessToken
     */
    @GetMapping("/myfavcourse")
    public ResponseEntity<MyFavoriteCourseListResponse> getMyFavoriteCourseList(@RequestHeader(AUTHORIZATION_HEADER) String accessToken){
        Integer memberId = jwtTokenService.getMemberId(accessToken);

        return ResponseEntity.ok(MyFavoriteCourseListResponse.toResponse(profileService.getMyFavoriteCourseList(memberId)));
    }

    /*
    작성자 : 김은비
    작성내용 : 프로필 정보 조회
     * @param : accessToken
     */
    @GetMapping("/profile")
    public ResponseEntity<MemberInfoResponse> getMemberProfile(@RequestHeader(AUTHORIZATION_HEADER) String accessToken){
        Integer memberId = jwtTokenService.getMemberId(accessToken);

        return ResponseEntity.ok(profileService.getMemberProfile(memberId));
    }

    /*
    작성자 : 김은비
    작성내용 : 커플 프로필 정보 수정
     * @param accessToken
     * @param request
     */
    @PostMapping("/update/couple-profile")
    public ResponseEntity<Void> updateCoupleProfile(@RequestHeader(AUTHORIZATION_HEADER) String accessToken, @RequestBody CoupleInfoRequest coupleInfoRequest){
        Integer memberId = jwtTokenService.getMemberId(accessToken);
        profileService.updatCoupleProfile(memberId, coupleInfoRequest);

        return ResponseEntity.ok().build();
    }

    /*
    작성자 : 김은비
    작성내용 : 비커플 프로필 정보 수정
     * @param accessToken
     * @param request
     */
    @PostMapping("/update/profile")
    public ResponseEntity<Void> updateMemberProfile(@RequestHeader(AUTHORIZATION_HEADER) String accessToken, @RequestBody MemberInfoRequest memberInfoRequest){
        Integer memberId = jwtTokenService.getMemberId(accessToken);
        profileService.updatMemberProfile(memberId, memberInfoRequest);

        return ResponseEntity.ok().build();
    }
}
