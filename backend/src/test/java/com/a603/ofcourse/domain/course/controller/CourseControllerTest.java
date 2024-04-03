package com.a603.ofcourse.domain.course.controller;

import com.a603.ofcourse.domain.course.dto.request.AddCourseRequestDto;
import com.a603.ofcourse.domain.course.service.CourseService;
import com.a603.ofcourse.domain.member.domain.Member;
import com.fasterxml.jackson.databind.ObjectMapper;
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

import java.util.Arrays;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;

@ExtendWith(MockitoExtension.class)
class CourseControllerTest {
    @Mock
    private CourseService courseService;

    @InjectMocks
    private CourseController courseController;

    private MockMvc mockMvc;

    private final ObjectMapper objectMapper = new ObjectMapper();

    /**
     * MockMvc Controller 객체로 초기화
     */
    @BeforeEach
    void initMockMvc() {
        mockMvc = MockMvcBuilders
                .standaloneSetup(courseController)
                .build();
    }

    @Test
    @DisplayName("데이트 코스 제작 테스트")
    void addCourseTest() throws Exception {
        // given
        String request = objectMapper.writeValueAsString(
                new AddCourseRequestDto(
                        "course1",
                        Arrays.asList(1,2,3,4)
                ));

        // stub
        BDDMockito.given(courseService.addCourse(BDDMockito.any(Member.class), BDDMockito.any(AddCourseRequestDto.class))).willReturn(BDDMockito.anyInt());

        // when
        mockMvc.perform(MockMvcRequestBuilders.post("/api/course/add")
                        .header("Authorization", BDDMockito.anyString())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(request)
                        .with(csrf()))
                .andExpect(MockMvcResultMatchers.status().isOk());

        // then
        BDDMockito.verify(courseService).addCourse(BDDMockito.any(Member.class), BDDMockito.any(AddCourseRequestDto.class));
    }
}