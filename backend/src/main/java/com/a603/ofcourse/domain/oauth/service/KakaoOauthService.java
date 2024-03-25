package com.a603.ofcourse.domain.oauth.service;

import com.a603.ofcourse.domain.member.domain.Member;
import com.a603.ofcourse.domain.member.domain.enums.Role;
import com.a603.ofcourse.domain.member.domain.enums.Type;
import com.a603.ofcourse.domain.member.repository.MemberRepository;
import com.a603.ofcourse.domain.oauth.dto.KakaoUserInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Map;
import java.util.Optional;

/*
카카오 AccessToken으로 유저 정보 가져와서 DB에 저장
 */
@RequiredArgsConstructor
@Service
public class KakaoOauthService {

    @Value("${spring.security.oauth2.client.registration.kakao.client-id}")
    private String KAKAO_CLIENT_ID;

    @Value("${spring.security.oauth2.client.registration.kakao.client-secret}")
    private String KAKAO_CLIENT_SECRET;

    @Value("${spring.security.oauth2.client.registration.kakao.redirect-uri}")
    private String KAKAO_REDIRECT_URL;

    @Value("${spring.security.oauth2.client.provider.kakao.token-uri}")
    private String ACCESS_TOKEN_URI;

    @Value("${spring.security.oauth2.client.provider.kakao.user-info-uri}")
    private String USER_INFO_URI;

    private final MemberRepository memberRepository;

    private final WebClient webClient;

    /*
    작성자 : 김은비
    작성내용 : AccessToken을 사용하여 KaKao API의 사용자 정보 엔드포인트를 호출하고, 응답 본문을 Map<String, Object>로 변환하여 반환
     * @param String accessToken
     * @return Map<String, Object>
     */
    public Map<String, Object> getMemberAttributesByToken(String accessToken){
        //1. WebClient 생성 -> 리액티브 웹 요청을 만들고 소비할 수 있는 클라이언트
        return webClient
                .get()
                //2. 요청할 URI 설정 -> KaKao API의 사용자 정보를 가져오는 엔드포인트
                .uri(USER_INFO_URI)
                //3. 요청에 헤더를 추가하여 인증 처리.(액세스 토큰을 Bearer 토근으로 설정하여 인증 수행)
                .headers(httpHeaders -> httpHeaders.setBearerAuth(accessToken))
                //4. 설정된 요청을 실행하고 응답을 받음
                .retrieve()
                //5. 응답 본문을 Mono(0 또는 1개의 요소를 갖는 비동기 시퀀스)로 변환. 여기서는 Map<String, Object> 타입으로 변환
                .bodyToMono(new ParameterizedTypeReference<Map<String, Object>>() {})
                //6. Mono의 값을 동기적으로 블록킹하여 결과를 반환
                .block();
    }

    /*
    작성자 : 김은비
    작성내용 : 카카오API에서 가져온 유저정보를 이미 회원이면 그냥 반환, 아직 회원이 아니면 DB에 저장
     * @param String accessToken
     * @return Member
     */
    public Member getMemberProfileByToken(String accessToken){
        Map<String, Object> memberAttributesByToken = getMemberAttributesByToken(accessToken);
        KakaoUserInfo kakaoUserInfo = new KakaoUserInfo(memberAttributesByToken);
        Long longSocialId = kakaoUserInfo.getId();
        Optional<Member> member = memberRepository.findBySocialId(longSocialId);
        //회원이면 반환, 회원 아니면 DB저장 후 반환
        return member.orElseGet(() -> memberRepository.save(Member.builder()
                .socialId(longSocialId)
                .type(Type.KAKAO)
                .role(Role.MEMBER)
                .build()));
    }

    /*
    작성자 : 김은비
    작성내용 : 인가코드를 활용해서 Kakao API의 사용자 인증 엔드포인트를 호출하여 반환되는 값 중 accessToken 뽑아서 반환
    * @param code(인가코드)
    * @return accessToken
    */
    public String getKakaoAccessTokenByCode(String code){
        //x-www-form-urlencoded 형식으로 보내기 위해 생성
        MultiValueMap<String, String> bodyParams = new LinkedMultiValueMap<>();
        bodyParams.add("grant_type", "authorization_code");
        bodyParams.add("client_id", KAKAO_CLIENT_ID);
        bodyParams.add("redirect_uri", KAKAO_REDIRECT_URL);
        bodyParams.add("code", code);

        Map<String, Object> attributesByCode = webClient.post()
                //1. 요청할 uri 설정
                .uri(ACCESS_TOKEN_URI)
                //2.보낼 값들 설정
                .body(BodyInserters.fromFormData(bodyParams))
                .header("Content-type", "application/x-www-form-urlencoded;charset=utf-8")
                //3. 요청 실행 후 응답 받기
                .retrieve()
                //4. 응답 본문을 Mono -> Map<String. Object>로 변환해서 가져옴
                .bodyToMono(new ParameterizedTypeReference<Map<String, Object>>() {})
                //5. 결과 반환
                .block();
        //Map 중 access_token 값 빼서 반환
        return String.valueOf(attributesByCode.get("access_token"));
    }
}
