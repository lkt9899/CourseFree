package com.a603.ofcourse.domain.post.domain;

import com.a603.ofcourse.domain.course.domain.Course;
import com.a603.ofcourse.domain.member.domain.Member;
import com.a603.ofcourse.global.common.BaseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@Table(name = "post")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Post extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_id")
    private Integer id;

    @Column(name="title")
    private String title;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "course_id")
    private Course course;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @OneToMany(mappedBy = "post")
    private List<PostContent> postContentList = new ArrayList<>();

    @Builder
    public Post(
            String title,
            Course course,
            Member member
    ){
        this.title = title;
        this.course = course;
        this.member = member;
    }

}
