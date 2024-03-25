package com.a603.ofcourse.domain.oauth.filter;

import com.a603.ofcourse.domain.oauth.models.MemberPrincipal;
import com.a603.ofcourse.domain.oauth.service.JwtTokenService;
import com.a603.ofcourse.domain.oauth.exception.OauthException;
import com.a603.ofcourse.domain.oauth.exception.OauthErrorCode;
import com.a603.ofcourse.domain.member.domain.Member;
import com.a603.ofcourse.domain.member.domain.Profile;
import com.a603.ofcourse.domain.member.exception.MemberErrorCode;
import com.a603.ofcourse.domain.member.exception.MemberException;
import com.a603.ofcourse.domain.member.repository.MemberRepository;
import com.a603.ofcourse.domain.member.repository.ProfileRepository;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.GenericFilterBean;

import java.io.IOException;

/*
Api 요청이 올 때마다 액세스토큰이 유효한지 확인 후 securityContext에 유저 정보 저장하는 Filter
 */
@RequiredArgsConstructor
public class JwtFilter extends GenericFilterBean {
    public static final String AUTHORIZATION_HEADER = "Authorization";
    private final JwtTokenService jwtTokenService;
    private final MemberRepository memberRepository;
    private final ProfileRepository profileRepository;

    /*
    작성자 : 김은비
    작성내용 : HTTP 요청을 필터링하고 처리. 이 Filter에서 액세스토큰이 유효한지 확인 후 securityContext에 계정정보 저장
     * @param ServletRequest
     * @param ServletResponse
     * @param FilterChain
     */
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        //토큰 payload에 있는 memberId 가져오기
        //1. HttpServletRequestfh 캐스팅된 servletRequest를 사용하여 현재 요청의 URL을 로깅 -> 필터가 적용되는 요청을 추적할 수 있음
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        logger.info("[JwtFilter] : " + httpServletRequest.getRequestURL().toString());
        //2. 요청에서 JWT 추출
        String jwt = resolveToken(httpServletRequest);

        //3. 토큰이 존재하고 유효하면
        if (StringUtils.hasText(jwt) && jwtTokenService.validateToken(jwt)) {
            //3-1. 토큰 payload에 있는 memberId 가져오기
            Long memberId = (Long)jwtTokenService.getPayload(jwt).get("member_id");
            //3-2. memberId로 member 객체 가져오기
            Member member = memberRepository.findBySocialId(memberId)
                    .orElseThrow(() ->
                            new MemberException(MemberErrorCode.MEMBER_DOES_NOT_EXISTS));
            //3-3. profile 객체 가져오기
            Profile profile = profileRepository.findByMemberId(member.getId());
            //3-4. userDetails 객체 생성
            UserDetails userDetails = MemberPrincipal.create(member, profile);
            //3-5. 인증 객체 생성 + 사용자의 권한 정보 설정
            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
            //3-6. 생성된 인증 객체를 SecurityContextHolder에 설정하여 인증된 사용자로서의 상태를 설정
            SecurityContextHolder.getContext().setAuthentication(authentication);
        } else {
            //4. 유효한 토큰이 아닌 경우 에러 발생
            throw new OauthException(OauthErrorCode.INVALID_ACCESS_TOKEN);
        }
        //5. 다음 필터로 요청을 전달
        filterChain.doFilter(servletRequest, servletResponse);
    }

    /*
    작성자 : 김은비
    작성일자 : 2024-03-13
    작성내용 : HTTP 요청에서 토큰을 추출. 인증된 사용자의 인증 토큰을 검증하거나 처리하는 데 사용
    * @param HttpServletRequest
    * @return String (token 값)
    */
    private String resolveToken(HttpServletRequest request){
        //1. HTTP 요청 헤더에서 AUTHORIZATION_HEADER 값을 가져옴 (보통 인증 토큰을 포함하고 있음)
        String bearerToken = request.getHeader(AUTHORIZATION_HEADER);

        //2. 가져온 헤더 값이 비어있지 않고 && Bearer로 시작하면(JWT 토큰 형식)
        if(StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")){
            //3. Bearer를 제외한 나머지 부분(실제 토큰 값)만 반환 -> subString 사용 이유
            return bearerToken.substring(7);
        }
        //3. 아니면 null 반환
        return null;
    }
}
