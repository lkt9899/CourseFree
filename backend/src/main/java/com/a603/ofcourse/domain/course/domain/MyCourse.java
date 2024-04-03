package com.a603.ofcourse.domain.course.domain;

import com.a603.ofcourse.domain.member.domain.Member;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "my_course")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MyCourse {
    @Id
    @Column(name = "my_course_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "course_id")
    private Course course;

    public MyCourse(Member member, Course course) {
        addRelatedMember(member);
        addRelatedCourse(course);
    }

    private void addRelatedMember(Member member) {
        this.member = member;
        member.getMyCourseList().add(this);
    }

    private void addRelatedCourse(Course course) {
        this.course = course;
        course.getMyCourseList().add(this);
    }
}