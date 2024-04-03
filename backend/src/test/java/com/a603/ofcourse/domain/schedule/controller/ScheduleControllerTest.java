package com.a603.ofcourse.domain.schedule.controller;

import com.a603.ofcourse.domain.schedule.dto.request.AddScheduleRequestDto;
import com.a603.ofcourse.domain.schedule.dto.response.ScheduleListResponseDto;
import com.a603.ofcourse.domain.schedule.service.ScheduleService;
import com.fasterxml.jackson.core.JsonProcessingException;
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
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalDateTime;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;

@ExtendWith(MockitoExtension.class)
class ScheduleControllerTest {

    @Mock
    private ScheduleService scheduleService;

    @InjectMocks
    private ScheduleController scheduleController;

    private MockMvc mockMvc;

    private ObjectMapper objectMapper;

    @BeforeEach
    void init() {
        objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        mockMvc = MockMvcBuilders
                .standaloneSetup(scheduleController)
                .build();
    }


    @Test
    @DisplayName("스케쥴 등록 테스트")
    void addNewSchedule() throws Exception {
        // given
        String request = objectMapper.writeValueAsString(AddScheduleRequestDto.builder()
                .courseId(1)
                .scheduleTitle("test")
                .appointmentPlace("test")
                .scheduleDate(LocalDateTime.now())
                .build());

        // stub
        BDDMockito.given(scheduleService.addNewSchedule(BDDMockito.anyString(), BDDMockito.any()))
                .willReturn(BDDMockito.anyInt());

        // when
        mockMvc.perform(MockMvcRequestBuilders.post("/api/schedule")
                .header("Authorization", BDDMockito.anyString())
                .contentType(MediaType.APPLICATION_JSON)
                .content(request)
                .with(csrf()))
                .andExpect(MockMvcResultMatchers.status().isCreated());

        // then
        BDDMockito.verify(scheduleService).addNewSchedule(BDDMockito.anyString(), BDDMockito.any());
    }

    @Test
    @DisplayName("스케쥴 조회 테스트")
    void findScheduleList() throws Exception {
        // given
        int month = 3;

        // stub
        BDDMockito.given(scheduleService.findScheduleList(BDDMockito.anyString(), BDDMockito.anyInt()))
                .willReturn(BDDMockito.any(ScheduleListResponseDto.class));

        // when
        mockMvc.perform(MockMvcRequestBuilders.get("/api/schedules")
                .header("Authorization", BDDMockito.anyString())
                .param("month", String.valueOf(month))
                        .with(csrf()))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        // then
        BDDMockito.verify(scheduleService).findScheduleList(BDDMockito.anyString(), BDDMockito.anyInt());
    }

    @Test
    @DisplayName("스케쥴 수정 테스트")
    void updateSchedule() throws JsonProcessingException {
        // given
        String request = objectMapper.writeValueAsString(AddScheduleRequestDto.builder()
                .courseId(1)
                .scheduleTitle("test")
                .appointmentPlace("test")
                .scheduleDate(LocalDateTime.now())
                .build());

        // when


        // then

    }
}