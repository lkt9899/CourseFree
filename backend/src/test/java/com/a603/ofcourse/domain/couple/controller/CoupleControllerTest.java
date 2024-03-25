package com.a603.ofcourse.domain.couple.controller;

import com.a603.ofcourse.domain.couple.service.CoupleService;
import com.a603.ofcourse.domain.oauth.service.JwtTokenService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
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
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Map;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class CoupleControllerTest {
    @Mock
    private CoupleService coupleService;
    @Mock
    private JwtTokenService jwtTokenService;

    @InjectMocks
    private CoupleController coupleController;

    private MockMvc mockMvc;

    private ObjectMapper objectMapper;

    @BeforeEach
    void initMockMvc(){
        objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        mockMvc = MockMvcBuilders
                .standaloneSetup(coupleController)
                .build();

    }

    @Test
    @DisplayName("커플 연동 테스트")
    void connectCoupleWithMemberTest() throws Exception {
        // Given
        String accessToken = "Bearer accessToken";
        String uuid  = "uuid";
        Integer visitorId = 2;
        Integer inviterId = 1;

        Claims claims = Jwts.claims();
        claims.put("member_id", visitorId);

        //stub
        // 액세스토큰에서 초대된 사람의 아이디 가져오기
        BDDMockito.given(jwtTokenService.getPayload(accessToken))
                .willReturn(claims);
        // uuid를 이용해서 초대한 사람의 아이디 가져오기
        BDDMockito.given(coupleService.getInviterIdFromLink(uuid))
                        .willReturn(inviterId);
        // When/Then
        mockMvc.perform(post("/connect/exist/{UUID}", uuid)
                        .header("Authorization", accessToken)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        //제대로 호출되었는지 검증
        BDDMockito.verify(coupleService).connectCouple(visitorId, inviterId);
    }
}