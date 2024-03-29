package com.a603.ofcourse.domain.schedule.domain;

import com.a603.ofcourse.domain.couple.domain.Couple;
import com.a603.ofcourse.domain.course.domain.Course;
import com.a603.ofcourse.domain.schedule.domain.enums.ScheduleState;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@Table(name = "schedule")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Schedule {
    @Id
    @Column(name = "schedule_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(columnDefinition = "VARCHAR(10) DEFAULT 'todo'")
    @Enumerated(EnumType.STRING)
    private ScheduleState scheduleState;

    @Size(max = 20)
    @Column(name = "schedule_title", length = 20)
    private String scheduleTitle;

    @Size(max = 20)
    @Column(name = "appointment_place", length = 20)
    private String appointmentPlace;

    @Column(name = "shedule_date")
    private LocalDateTime scheduleDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "course_id")
    private Course course;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "couple_id")
    private Couple couple;

    @OneToMany(mappedBy = "schedule", fetch = FetchType.LAZY)
    private List<SchedulePlace> schedulePlaceList = new ArrayList<>();

    @Builder
    public Schedule(
            ScheduleState scheduleState,
            String scheduleTitle,
            String appointmentPlace,
            LocalDateTime scheduleDate,
            Course course,
            Couple couple) {
        this.scheduleState = scheduleState;
        this.scheduleTitle = scheduleTitle;
        this.appointmentPlace = appointmentPlace;
        this.scheduleDate = scheduleDate;
        addRelatedScheduleCourse(course);
        addRelatedScheduleCouple(couple);
    }

    private void addRelatedScheduleCourse(Course course) {
        this.course = course;
        course.getScheduleList().add(this);
    }

    private void addRelatedScheduleCouple(Couple couple) {
        this.couple = couple;
        couple.getScheduleList().add(this);
    }
}