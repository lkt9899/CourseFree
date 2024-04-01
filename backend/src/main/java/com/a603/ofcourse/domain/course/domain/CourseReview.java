package com.a603.ofcourse.domain.course.domain;

import com.a603.ofcourse.domain.course.dto.response.CourseReviewResponseDto;
import com.a603.ofcourse.global.domain.BaseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CourseReview extends BaseEntity {
    @Id
    @Column(name = "course_review_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String authorNickname;

    @Lob
    @Column(name = "content")
    private String content;
    // TODO: 게시글 수정 기능용 setter 방식 정해야함

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "course_id")
    private Course course;

    @Builder
    public CourseReview(String content, String authorNickname, Course course) {
        this.authorNickname = authorNickname;
        this.content = content;
        addRelatedCourse(course);
    }

    public CourseReviewResponseDto toResponse() {
        return CourseReviewResponseDto.builder()
                .id(id)
                .authorNickname(authorNickname)
                .content(content)
                .regDate(getCreateDate())
                .build();
    }

    public void updateContent(String content) {
        this.content = content;
    }

    private void addRelatedCourse(Course course) {
        this.course = course;
        course.getCourseReviewList().add(this);
    }

}