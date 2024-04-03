package com.a603.ofcourse.domain.course.repository;

import com.a603.ofcourse.domain.course.domain.Course;
import com.a603.ofcourse.domain.course.domain.CourseReview;
import com.a603.ofcourse.domain.course.enums.CourseCategory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class CourseReviewRepositoryTest {
    @Autowired
    CourseReviewRepository courseReviewRepository;

    @Autowired
    CourseRepository courseRepository;

    @Test
    @DisplayName("저장 테스트")
    void saveTest() {
        // given
        Course course = Course.builder()
                .courseCategory(CourseCategory.TMP_CATEGORY)
                .title("testTitle")
                .imageUrl("testImage")
                .hashKey("1:2:3")
                .build();

        CourseReview courseReview = CourseReview.builder()
                .authorNickname("test")
                .content("testContent")
                .course(course)
                .build();

        // when
        Course savedCourse = courseRepository.save(course);
        CourseReview savedCourseReview = courseReviewRepository.save(courseReview);

        // then
        assertThat(savedCourseReview.getId()).isNotNull();
        assertThat(savedCourseReview.getAuthorNickname()).isEqualTo("test");
        assertThat(savedCourseReview.getCourse().getTitle()).isEqualTo("testTitle");
        assertThat(savedCourseReview.getAuthorNickname()).isEqualTo("test");

    }
}
