package com.a603.ofcourse.domain.schedule.domain;

import com.a603.ofcourse.domain.couple.domain.Couple;
import com.a603.ofcourse.domain.course.domain.Course;
import com.a603.ofcourse.domain.schedule.domain.enums.ScheduleState;
import com.a603.ofcourse.domain.schedule.dto.request.UpdateScheduleRequestDto;
import com.a603.ofcourse.domain.schedule.dto.response.ScheduleDetailResponseDto;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.*;

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

    @Size(max = 20)
    @Column(name = "schedule_title", length = 20)
    private String scheduleTitle;

    @Size(max = 20)
    @Column(name = "appointment_place", length = 20)
    private String appointmentPlace;

    @Column(name = "schedule_date")
    private LocalDateTime scheduleDate;

    @Enumerated(EnumType.STRING)
    private ScheduleState scheduleState;

    // course 변경
    @Setter
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
        this.scheduleState = ScheduleState.TODO;
        addRelatedScheduleCourse(course);
        addRelatedScheduleCouple(couple);
    }


    public ScheduleDetailResponseDto toResponse() {
        return ScheduleDetailResponseDto.builder()
                .scheduleId(id)
                .scheduleTitle(scheduleTitle)
                .courseId(course.getId())
                .appointmentPlace(appointmentPlace)
                .scheduleDate(scheduleDate)
                .scheduleState(scheduleState)
                .build();
    }

    public void update(UpdateScheduleRequestDto updateScheduleRequestDto) {
        this.scheduleTitle = updateScheduleRequestDto.getScheduleTitle();
        this.appointmentPlace = updateScheduleRequestDto.getAppointmentPlace();
        this.scheduleDate = updateScheduleRequestDto.getScheduleDate();
    }

    public void complete() {
        this.scheduleState = ScheduleState.COMPLETE;
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