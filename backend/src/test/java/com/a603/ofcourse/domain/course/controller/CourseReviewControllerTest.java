package com.a603.ofcourse.domain.course.controller;

import com.a603.ofcourse.domain.course.dto.request.AddCourseReviewRequestDto;
import com.a603.ofcourse.domain.course.dto.response.CourseReviewResponseDto;
import com.a603.ofcourse.domain.course.service.CourseReviewService;
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
import java.util.ArrayList;
import java.util.List;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;

@ExtendWith(MockitoExtension.class)
class CourseReviewControllerTest {

    @Mock
    private CourseReviewService courseReviewService;

    @InjectMocks
    private CourseReviewController courseReviewController;

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
                .standaloneSetup(courseReviewController)
                .build();
    }

    @Test
    @DisplayName("코스 리뷰 작성 테스트")
    void addNewCourseReview() throws Exception {
        // given
        String request = objectMapper.writeValueAsString(AddCourseReviewRequestDto.builder()
                .courseId(1)
                .content("test")
                .build());

        // stub
        BDDMockito.given(courseReviewService.addNewCourseReview(BDDMockito.anyString(), BDDMockito.any())).willReturn(BDDMockito.anyInt());

        // when
        mockMvc.perform(MockMvcRequestBuilders.post("/api/course-review/write")
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("Authorization", BDDMockito.anyString())
                        .content(request)
                        .with(csrf()))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andReturn();

        // then
        BDDMockito.verify(courseReviewService).addNewCourseReview(BDDMockito.anyString(), BDDMockito.any());
    }

    @Test
    @DisplayName("코스 리뷰 조회 테스트")
    void findCourseReviewList() throws Exception {
        // given
        List<CourseReviewResponseDto> reviews = new ArrayList<>();
        reviews.add(getReview(1));
        reviews.add(getReview(2));
        reviews.add(getReview(3));

        // stub
        BDDMockito.given(courseReviewService.findCourseReviewList(BDDMockito.anyInt())).willReturn(reviews);

        // when
        mockMvc.perform(MockMvcRequestBuilders.get("/api/course-review")
                        .param("courseId", "1")
                        .with(csrf()))
                .andExpect(MockMvcResultMatchers.status().isOk());

        // then
        BDDMockito.verify(courseReviewService).findCourseReviewList(BDDMockito.anyInt());
    }

    @Test
    @DisplayName("리뷰 수정 테스트")
    void updateCourseReview() throws Exception {

    }

    @Test
    void deleteCourseReview() {
        // given

        // stub

        // when

        // then

    }

    CourseReviewResponseDto getReview(Integer id) {
        return CourseReviewResponseDto.builder()
                .id(id)
                .authorNickname("test")
                .content("test")
                .regDate(LocalDateTime.now())
                .build();
    }
}