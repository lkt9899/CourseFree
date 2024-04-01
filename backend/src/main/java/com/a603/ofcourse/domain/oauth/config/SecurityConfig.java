package com.a603.ofcourse.domain.oauth.config;

import com.a603.ofcourse.domain.member.domain.enums.Role;
import com.a603.ofcourse.domain.oauth.filter.JwtFilter;
import com.a603.ofcourse.domain.oauth.service.JwtTokenService;
import com.a603.ofcourse.domain.oauth.exception.ExceptionHandlerFilter;
import com.a603.ofcourse.domain.member.repository.MemberRepository;
import com.a603.ofcourse.domain.member.repository.ProfileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import static org.springframework.security.config.Customizer.withDefaults;

/*
Spring Security 설정
 */
@Configuration
@RequiredArgsConstructor
@EnableWebSecurity(debug = true)
public class SecurityConfig {
    private final JwtTokenService jwtTokenService;
    private final MemberRepository memberRepository;
    private final ProfileRepository profileRepository;

    /*
    작성자 : 김은비
    작성내용 : Spring Security의 AuthenticationManager 객체 빈 생성
     * @return AuthenticationManager
     */
    @Bean
    public AuthenticationManager authenticationManager(
            final AuthenticationConfiguration authenticationConfiguration) throws Exception{
        return authenticationConfiguration.getAuthenticationManager();
    }

    /*
    작성자 : 김은비
    작성내용 : Spring Security의 보안 구성을 설정 + securityFilterChain 객체 빈 생성
     * @param HttpSecurity
     * @return RefreshTokenResponse
     */
    @Bean
    public SecurityFilterChain configure(final HttpSecurity http) throws Exception{
        //1. CORS 설정을 활성화하고 기본값을 사용
        return http
                .cors().configurationSource(corsConfigurationSource())
                .and()

                //2. CSRF 보호를 비활성화
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeRequests()
                .anyRequest().permitAll()
                .and()
                //3. HTTP 요청에 대한 권한 설정
                // .authorizeHttpRequests(authorize -> authorize
                //         //3-1. 모든 사용자에게 허용
                //         .requestMatchers("/login/**", "/token/refresh").permitAll()
                //         //3-2. 멤버 역할 사용자에게만 허용
                //         .requestMatchers("/member/**").hasAnyAuthority(Role.MEMBER.getRole())
                //         //3-3. 나머지는 인증된 사용자에게만 허용
                //         .anyRequest().authenticated())

                //4. 세션 관리를 설정 -> 세션을 사용하지 않고 상태를 유지하는 않는 세션 생성 정책 사용
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))

                //5. 폼 기반 로그인 비활성화 -> 사용자 정의된 인증 방식(JWT)을 사용하기 위해
                .formLogin(AbstractHttpConfigurer::disable)

                //6. HTTP 기본 인증을 비활성화 -> 사용자 정의된 인증 방식(JWT)을 사용하기 위해
                .httpBasic(AbstractHttpConfigurer::disable)

                //7. JWT Filer를 UsernamePasswordAuthenticationFilter 앞에 추가 -> JWT 토큰을 사용하여 인증을 수행
                .addFilterBefore(new JwtFilter(jwtTokenService, memberRepository, profileRepository), UsernamePasswordAuthenticationFilter.class)

                //8. JWTFilter 앞에 추가 -> 예외를 처리하고 적절한 에러 응답 생성
                .addFilterBefore(new ExceptionHandlerFilter(), JwtFilter.class)

                //9. HttpSecurity 객체를 사용하여 구성을 완료하고 SecurityFilterChain을 빌드
                .build();
    }

    /*
    작성자 : 김은비
    작성내용 : (추가구성)특정 경로에 대한 보안 검사 무시
     * @return WebSecurityCustomizer
    */
    @Bean
    public WebSecurityCustomizer webSecurityCustomizer(){
        return web ->
                web.ignoring()
                        .requestMatchers("/**");
//                        .requestMatchers("/login/**", "/token/refresh");
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();

        configuration.addAllowedOriginPattern("*");
        configuration.addAllowedHeader("*");
        configuration.addAllowedMethod("*");
        configuration.addExposedHeader("*");
        configuration.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}
