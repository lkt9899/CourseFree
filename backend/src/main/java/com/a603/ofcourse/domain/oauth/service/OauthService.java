package com.a603.ofcourse.domain.oauth.service;

import com.a603.ofcourse.domain.couple.repository.MemberCoupleRepository;
import com.a603.ofcourse.domain.member.domain.Member;
import com.a603.ofcourse.domain.oauth.dto.MemberExistWithAccessToken;
import com.a603.ofcourse.domain.oauth.dto.MemberWithIsExist;
import com.a603.ofcourse.domain.oauth.exception.OauthErrorCode;
import com.a603.ofcourse.domain.oauth.exception.OauthException;
import com.a603.ofcourse.domain.oauth.repository.AuthRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/*
토큰 생성, 갱신 등 로그인 관련 로직 서비스
 */
@Service
@RequiredArgsConstructor
public class OauthService {
    private final JwtTokenService jwtTokenService;
    private final KakaoOauthService kakaoOauthService;
    private final AuthRepository authRepository;
    private final MemberCoupleRepository memberCoupleRepository;

    /*
    작성자 : 김은비
    작성내용 : 카카오 로그인
     * @param kakaoAccessToken
     * @return MemberExistWithAccessToken
     */
    public MemberExistWithAccessToken loginWithKakao(String kakaoAccessToken){
        //이미 가입돼 있는 회원 or 신규회원 멤버
        MemberWithIsExist memberWithIsExist = kakaoOauthService.getMemberProfileByToken(kakaoAccessToken);
        Member member = memberWithIsExist.getMember();


        //만약 커플이면 커플 아이디를 포함한 액세스토큰을 발행하고
        //커플이 아니면 멤버 아이디만 포함한 액세스 토큰을 발행한다
        String accessToken = memberCoupleRepository.findByMember(member)
                .map(mc -> getTokensWithCoupleId(member.getId(), mc.getCouple().getId()))
                .orElseGet( () -> getTokens(member.getId()));

        //accessToken 반환
        return MemberExistWithAccessToken.from(accessToken, memberWithIsExist.isExist());
    }

    /*
    작성자 : 김은비
    작성내용 : 액세스 토큰, 리프레시 토큰 생성
     * @param Integer memberId
     * @return accessToken
     */
    public String getTokens(Integer memberId){
        //1. 사용자 아이디로 액세스토큰 생성
        final String accessToken = jwtTokenService.createAccessToken(memberId);
        final String refreshToken = jwtTokenService.createRefreshToken(memberId);

        //2. 리프레시토큰 redis에 저장하기
        jwtTokenService.saveRefreshTokenToRedis(memberId, refreshToken);
        return accessToken;
    }

    /*
    작성자 : 김은비
    작성내용 : 액세스 토큰, 리프레시 토큰 생성
     * @param Integer memberId
     * @return accessToken
     */
    public String getTokensWithCoupleId(Integer memberId, Integer coupleId){
        //1. 사용자 아이디로 액세스토큰 생성
        final String accessToken = jwtTokenService.createAccessTokenWithCoupleId(memberId, coupleId);
        final String refreshToken = jwtTokenService.createRefreshTokenWithCoupleId(memberId, coupleId);

        //2. 리프레시토큰 redis에 저장하기
        jwtTokenService.saveRefreshTokenToRedis(memberId, refreshToken);
        return accessToken;
    }

    /*
    작성자 : 김은비
    작성내용 : redis에서 리프레시토큰 가져온 후, 리프레시 토큰으로 액세스토큰 새로 갱신 후 반환 + 리프레시 토큰도 갱신
     * @param memberId
     * @return accessToken
     */
    public String refreshAccessToken(Integer memberId){
        //redis에서 리프레시 토큰 가져오기
        return authRepository.findById(memberId)
                //리프레시 토큰이 유효하면 유저 정보로 액세스 토큰 갱신 + 리프레시 토큰 갱신
                .map(RefreshToken -> getTokens(memberId))
                //refreshToken이 없으면 에러 코드 전송
                .orElseThrow(() -> new OauthException(OauthErrorCode.INVALID_REFRESH_TOKEN));
    }

    /*
    작성자 : 김은비
    작성내용 : 멤버아이디와 커플아이디가 있는 액세스토큰 갱신 후 반환 + 리프레시 토큰도 갱신
     * @param memberId, coupleId
     * @return accessToken
     */
    public String refreshAccessTokenWithCoupleId(Integer memberId, Integer coupleId){
        //redis에서 리프레시 토큰 가져오기
        return authRepository.findById(memberId)
                //리프레시 토큰이 유효하면 유저 정보로 액세스 토큰 갱신 + 리프레시 토큰 갱신
                .map(RefreshToken -> getTokensWithCoupleId(memberId, coupleId))
                //refreshToken이 없으면 에러 코드 전송
                .orElseThrow(() -> new OauthException(OauthErrorCode.INVALID_REFRESH_TOKEN));
    }
}
