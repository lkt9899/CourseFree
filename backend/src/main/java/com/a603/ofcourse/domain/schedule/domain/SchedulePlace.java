package com.a603.ofcourse.domain.schedule.domain;

import com.a603.ofcourse.domain.place.domain.Place;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@Table(name = "schedule_place")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SchedulePlace {
    @Id
    @Column(name = "schedule_place_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "schedule_id")
    private Schedule schedule;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "place_id")
    private Place place;

    @OneToMany(mappedBy = "schedulePlace", fetch = FetchType.LAZY)
    private List<Picture> pictureList = new ArrayList<>();

    @OneToMany(mappedBy = "schedulePlace", fetch = FetchType.LAZY)
    private List<Diary> diaryList = new ArrayList<>();

    public SchedulePlace(Schedule schedule, Place place) {
        addRelatedSchedule(schedule);
        addRelatedPlace(place);
    }

    private void addRelatedSchedule(Schedule schedule) {
        this.schedule = schedule;
        schedule.getSchedulePlaceList().add(this);
    }

    private void addRelatedPlace(Place place) {
        this.place = place;
        place.getSchedulePlaceList().add(this);
    }
}