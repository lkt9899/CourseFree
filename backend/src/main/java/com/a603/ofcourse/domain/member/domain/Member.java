package com.a603.ofcourse.domain.member.domain;

import com.a603.ofcourse.domain.couple.domain.Couple;
import com.a603.ofcourse.domain.couple.domain.MemberCouple;
import com.a603.ofcourse.domain.course.domain.CourseReview;
import com.a603.ofcourse.domain.course.domain.MyCourse;
import com.a603.ofcourse.domain.member.domain.enums.Role;
import com.a603.ofcourse.domain.member.domain.enums.Type;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@Table(name = "member")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member {
    @Id
    @Column(name = "member_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "social_id", nullable = false)
    private Long socialId;

    @Enumerated(EnumType.STRING)
    private Type type;

    @Enumerated(EnumType.STRING)
    private Role role;

    @OneToMany(mappedBy = "member", fetch = FetchType.LAZY)
    private List<MyCourse> myCourseList = new ArrayList<>();

    @OneToOne(mappedBy = "member", fetch = FetchType.LAZY)
    private Profile profile;

    @OneToOne(mappedBy = "member",fetch = FetchType.LAZY)
    private MemberCouple memberCouple;

    public void updateMemberCouple(MemberCouple memberCouple){
        this.memberCouple = memberCouple;
    }

    @Builder
    public Member(
            Long socialId,
            Type type,
            Role role
    ){
        this.socialId = socialId;
        this.type = type;
        this.role = role;
    }
}