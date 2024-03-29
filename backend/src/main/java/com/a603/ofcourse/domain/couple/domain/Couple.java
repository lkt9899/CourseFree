package com.a603.ofcourse.domain.couple.domain;

import com.a603.ofcourse.domain.schedule.domain.Schedule;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;


@Getter
@Setter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Couple {
    @Id
    @Column(nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, length = 20)
    private String coupleNickname;

    @Column(nullable = false)
    private int dDay;

    @Column(nullable = false)
    private boolean notCouple;

    @OneToMany(mappedBy = "couple", fetch = FetchType.LAZY)
    private List<Schedule> scheduleList = new ArrayList<>();

    @OneToMany(mappedBy = "couple",fetch = FetchType.LAZY)
    private List<MemberCouple> memberCoupleList = new ArrayList<>();

    @Builder
    public Couple(String coupleNickname) {
        this.coupleNickname = coupleNickname;
    }
}