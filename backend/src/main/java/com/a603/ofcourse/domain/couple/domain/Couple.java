package com.a603.ofcourse.domain.couple.domain;

import com.a603.ofcourse.domain.member.domain.Member;
import com.a603.ofcourse.domain.place.domain.PlaceReview;
import com.a603.ofcourse.domain.schedule.domain.Schedule;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.springframework.boot.context.properties.bind.DefaultValue;

import java.util.ArrayList;
import java.util.List;


@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Couple {
    @Id
    @Column(name = "couple_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Size(max = 10)
    @Column(name = "couple_nickname", length = 10)
    private String coupleNickname;

    @Column(name = "d_day")
    private int dDay;

    @OneToMany(mappedBy = "couple", fetch = FetchType.LAZY)
    private List<Schedule> scheduleList = new ArrayList<>();

    @OneToMany(mappedBy = "couple",fetch = FetchType.LAZY)
    private List<MemberCouple> memberCoupleList = new ArrayList<>();

    @Builder
    public Couple(
            String coupleNickname,
            int dDay) {
        this.coupleNickname = coupleNickname;
        this.dDay = dDay;
    }
}