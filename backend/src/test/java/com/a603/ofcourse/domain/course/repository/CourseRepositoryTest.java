package com.a603.ofcourse.domain.course.repository;

import com.a603.ofcourse.domain.course.domain.Course;
import com.a603.ofcourse.domain.course.enums.CourseCategory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class CourseRepositoryTest {
    @Autowired
    CourseRepository courseRepository;

    @Test
    @DisplayName("코스 저장 테스트")
    void saveCourse() {
        // given
        Course course = Course.builder()
                .courseCategory(CourseCategory.TMP_CATEGORY)
                .title("title")
                .hashKey("1:2:3")
                .imageUrl("testImage")
                .build();

        // when
        Course saved = courseRepository.save(course);

        // then
        assertThat(saved.getId()).isNotNull();
        assertThat(saved.getTitle()).isEqualTo(course.getTitle());
        assertThat(saved.getHashKey()).isEqualTo(course.getHashKey());
        assertThat(saved.getCourseCategory().getValue()).isEqualTo(course.getCourseCategory().getValue());
    }
}
