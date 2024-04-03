package com.a603.ofcourse.domain.oauth.service;

import com.a603.ofcourse.domain.oauth.exception.OauthErrorCode;
import com.a603.ofcourse.domain.oauth.exception.OauthException;
import com.a603.ofcourse.domain.oauth.redis.RefreshToken;
import com.a603.ofcourse.domain.oauth.repository.AuthRepository;
import io.jsonwebtoken.*;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.util.Date;

/*
JWT 토큰 생성, 조회 관련 서비스
 */
@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class JwtTokenService {
    private static final String MEMBER_ID = "member_id";
    private static final String COUPLE_ID = "couple_id";

    @Value("${jwt.access.token.expiration.seconds}")
    private long accessTokenExpirationInSeconds;

    @Value("${jwt.refresh.token.expiration.seconds}")
    private long refreshTokenExpirationInSeconds;

    @Value("${jwt.token.secret-key}")
    private String secretKey;

    private final AuthRepository authRepository;

    /*
    작성자 : 김은비
    작성내용 : 멤버아이디만 담은 액세스토큰 생성
     * @param memberId (payload)
     * @return accessToken
     */
    public String createAccessToken(Integer memberId){
        //페이로드를 포함한 액세스 토큰을 생성
        return createToken(memberId, accessTokenExpirationInSeconds);
    }

    /*
    작성자 : 김은비
    작성내용 : 멤버아이디와 커플아이디를 담은 액세스토큰 생성
     * @param memberId (payload)
     * @param coupleId (payload)
     * @return accessToken
     */
    public String createAccessTokenWithCoupleId(Integer memberId, Integer coupleId){
        return createTokenWithCoupleId(memberId, coupleId, accessTokenExpirationInSeconds);
    }

    /*
    작성자 : 김은비
    작성내용 : 멤버아이디만 담은 리프레시토큰 생성
     * @param memberId (payload)
     * @return refreshToken
     */
    public String createRefreshToken(Integer memberId){
        //페이로드를 포함한 리프레시 토큰을 생성
        return createToken(memberId, refreshTokenExpirationInSeconds);
    }

    /*
    작성자 : 김은비
    작성내용 : 멤버아이디와 커플아이디를 담은 리프레시 토큰 생성
     * @param memberId (payload)
     * @param coupleId (payload)
     * @return accessToken
     */
    public String createRefreshTokenWithCoupleId(Integer memberId, Integer coupleId){
        return createTokenWithCoupleId(memberId, coupleId, refreshTokenExpirationInSeconds);
    }

    /*
    작성자 : 김은비
    작성내용 : 멤버아이디를 포함한 토큰을 생성
     * @param memberId (payload)
     * @param expireLength
     * @return Jwts
     */
    public String createToken(Integer payload, long expireLength) {
        //1. 현재 시간 나타내는 Date 객체 생성 (토큰의 발급 시간)
        Date now = new Date();
        //2. 현재시간에 만료기간을 더해 토큰의 유효기간 객체 생성
        Date validity = new Date(now.getTime() + expireLength * 1000);
        //3. 토큰을 build (페이로드 클레임, 발급 시간, 만료 시간)
        return Jwts.builder()
                //header 설정 (토큰 타입)
                .setHeaderParam(Header.TYPE, Header.JWT_TYPE)
                //페이로드를 포함한 클레임
                .claim(MEMBER_ID, payload)
                .setIssuedAt(now)
                .setExpiration(validity)
                //설정한 정보로 토큰을 서명.(key 사용, 서명 알고리즘 선택)
                .signWith(SignatureAlgorithm.HS256, secretKey.getBytes(StandardCharsets.UTF_8))
                .compact();
    }

    /*
    작성자 : 김은비
    작성내용 : 멤버아이디와 커플아이디를 포함한 토큰을 생성
     * @param memberId (payload)
     * @param coupleId (payload)
     * @param expireLength
     * @return Jwts
     */
    public String createTokenWithCoupleId(Integer memberId, Integer coupleId, long expireLength){
        //1. 현재 시간 나타내는 Date 객체 생성 (토큰의 발급 시간)
        Date now = new Date();
        //2. 현재시간에 만료기간을 더해 토큰의 유효기간 객체 생성
        Date validity = new Date(now.getTime() + expireLength * 1000);
        //3. 토큰을 build (페이로드 클레임, 발급 시간, 만료 시간)
        return Jwts.builder()
                //header 설정 (토큰 타입)
                .setHeaderParam(Header.TYPE, Header.JWT_TYPE)
                //페이로드를 포함한 클레임
                .claim(MEMBER_ID, memberId)
                .claim(COUPLE_ID, coupleId)
                .setIssuedAt(now)
                .setExpiration(validity)
                //설정한 정보로 토큰을 서명.(key 사용, 서명 알고리즘 선택)
                .signWith(SignatureAlgorithm.HS256, secretKey.getBytes(StandardCharsets.UTF_8))
                .compact();
    }

    /*
    작성자 : 김은비
    작성내용 : 주어진 토큰에서 페이로드를 추출하여 서브젝트를 반환
     * @param String token
     * @return Claims
     */
    public Claims getPayload(String token){
        try{
            //1. 토큰을 파싱하여 토큰의 페이로드에서 서브젝트(사용자 식별 정보)를 추출
            return Jwts.parserBuilder()
                    .setSigningKey(secretKey.getBytes(StandardCharsets.UTF_8))
                    .build()
                    .parseClaimsJws(token.substring(7))
                    .getBody();
            //2. 토큰이 만료되었을 경우
        }catch (ExpiredJwtException e){
            // 만료된 토큰의 서브젝트를 반환 (만료되었더라도 만료 전에 설정한 서브젝트를 반환하기 위함 -> 정보를 최대한 활용하기 위함으로 만료된 토큰에서 사용자의 아이디나 권한 등을 가져와서 작업 수행이 가능함)
            return e.getClaims();
        }catch(JwtException e){
            //예외 발생 시 예외 알리기
            log.info("페이로드 반환 시 예외 발생");
            log.error(e.getMessage());
            throw new OauthException(OauthErrorCode.UNAUTHORIZED);
        }
    }

    /*
    작성자 : 김은비
    작성내용 : 토큰이 유효한지를 검증하고 유효한 경우에는 만료 여부를 확인하여 결과를 반환
     * @param String token
     * @return boolean
     */
    public boolean validateToken(String token){
        try{
            //1. 토큰을 파싱하여 클레임을 가져옴 (서명이 올바를 경우 클레임 추출)
            Jws<Claims> claimsJws = Jwts.parserBuilder()
                    .setSigningKey(secretKey.getBytes(StandardCharsets.UTF_8))
                    .build()
                    .parseClaimsJws(token);
            //2. 현재 시간이 토큰의 만료 시간 이전이면 true 반환하고 아니면 false 반환
            return !claimsJws.getBody().getExpiration().before(new Date());
        }catch (JwtException | IllegalArgumentException exception){
            return false;
        }
    }

    /*
    작성자 : 김은비
    작성내용 : redis에 리프레시토큰 저장
     * @param refreshToken
     */
    public void saveRefreshTokenToRedis(Integer memberId, String refreshToken){
        //refreshToken 저장
        authRepository.save(RefreshToken.builder()
                        .memberId(memberId)
                        .refreshToken(refreshToken)
                        .build());
    }

    /*
    작성자 : 김은비
    작성내용 : 페이로드에 커플아이디 있는지 확인
     * @param Claims
     * @return 있으면 true, 없으면 false
     */
    public boolean hasCoupleId(String accessToken){
        return getPayload(accessToken).containsKey(COUPLE_ID);
    }

    /*
    작성자 : 김은비
    작성내용 : 액세스토큰에서 memberId 뽑아서 반환
     * @param accessToken
     * @return memberId
     */
    public Integer getMemberId(String accessToken){
        return (Integer) getPayload(accessToken).get(MEMBER_ID);
    }

    /**
     * 커플 ID 추출 함수
     * @author 이경태
     * @param accessToken   accessToken of current user
     * @return coupleId     exported from accessToken
     */
    public Integer getCoupleId(String accessToken) {
        return (Integer) getPayload(accessToken).get(COUPLE_ID);
    }
}
