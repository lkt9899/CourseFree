package com.a603.ofcourse.domain.oauth.controller;

import com.a603.ofcourse.domain.oauth.service.JwtTokenService;
import com.a603.ofcourse.domain.oauth.service.KakaoOauthService;
import com.a603.ofcourse.domain.oauth.service.OauthService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;

@ExtendWith(MockitoExtension.class)
class OauthControllerTest {

    @Mock
    private OauthService oauthService;
    @Mock
    private JwtTokenService jwtTokenService;
    @Mock
    private KakaoOauthService kakaoOauthService;

    @InjectMocks
    private OauthController oauthController;

    private MockMvc mockMvc;

    private ObjectMapper objectMapper;

    /**
     * mockMVC 컨트럴로 객체로 초기화
     */
    @BeforeEach
    void initMockMVC() {
        objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        mockMvc = MockMvcBuilders
                .standaloneSetup(oauthController)
                .build();
    }

    @Test
    @DisplayName("로그인 테스트")
    void login() throws Exception{
        //given

        //stub
        BDDMockito.given(kakaoOauthService.getKakaoAccessTokenByCode(
                BDDMockito.anyString()
        )).willReturn(BDDMockito.anyString());

//        BDDMockito.given(oauthService.loginWithKakao(
//                BDDMockito.anyString()
//        )).willReturn(BDDMockito.anyString());

        //when
        mockMvc.perform(MockMvcRequestBuilders.post("/login/oauth/kakao")
                .contentType(MediaType.TEXT_PLAIN)
                .content("BDDMockito.anyString()")
                .with(csrf()))
                .andExpect(MockMvcResultMatchers.status().isOk());

        //then
        BDDMockito.verify(kakaoOauthService).getKakaoAccessTokenByCode(BDDMockito.anyString());
        BDDMockito.verify(oauthService).loginWithKakao(BDDMockito.anyString());
    }

    @Test
    void autoLogin() {
    }
}