package com.a603.ofcourse.domain.schedule.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Entity
@Table(name = "diary")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Diary {
    @Id
    @Column(name = "diary_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Lob
    @Column(name = "diary_content")
    private String diaryContent;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "schedule_place_id")
    private SchedulePlace schedulePlace;

    public Diary(String diaryContent, SchedulePlace schedulePlace) {
        this.diaryContent = diaryContent;
        addRelatedSchedulePlace(schedulePlace);
    }
    private void addRelatedSchedulePlace(SchedulePlace schedulePlace) {
        this.schedulePlace = schedulePlace;
        schedulePlace.getDiaryList().add(this);
    }
}