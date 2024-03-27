package com.a603.ofcourse.global.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("*") // 모든 원본 허용
                .allowedMethods("*") // 허용할 HTTP 메서드
//                .allowedHeaders("Authorization", "Content-Type")
                .allowCredentials(false) // 인증정보 포함 허용
                ; // 최대 유효시간 설정
    }
}
