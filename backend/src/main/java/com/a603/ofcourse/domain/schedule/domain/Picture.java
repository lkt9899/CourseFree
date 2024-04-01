package com.a603.ofcourse.domain.schedule.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Entity
@Table(name = "picture")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Picture {
    @Id
    @Column(name = "picture_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Size(max = 256)
    @Column(name = "url", length = 256)
    private String url;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "schedule_place_id")
    private SchedulePlace schedulePlace;

    public Picture(String url, SchedulePlace schedulePlace) {
        this.url = url;
        addRelatedSchedulePlace(schedulePlace);
    }
    private void addRelatedSchedulePlace(SchedulePlace schedulePlace) {
        this.schedulePlace = schedulePlace;
        schedulePlace.getPictureList().add(this);
    }
}