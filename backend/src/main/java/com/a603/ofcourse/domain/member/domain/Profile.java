package com.a603.ofcourse.domain.member.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table(name = "profile")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Profile {
    @Id
    @Column(name = "profile_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Size(max = 10)
    @Column(name = "nickname", length = 10)
    private String nickname;

    @Size(max = 8)
    @Column(name = "age_range", length = 8)
    private String ageRange;

    @Size(max = 256)
    @Column(name = "image", length = 256)
    private String image;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @Builder
    public Profile(
            String nickname,
            String ageRange,
            String image,
            Member member) {
        this.nickname = nickname;
        this.ageRange = ageRange;
        this.image = image;
        this.member = member;
    }
}