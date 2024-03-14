package com.a603.ofcourse.domain.course.domain;

import com.a603.ofcourse.domain.schedule.domain.Schedule;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@Table(name = "course")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Course {
    @Id
    @Column(name = "course_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Size(max = 8)
    @Column(name = "course_category", length = 8)
    private String courseCategory;

    @Size(max = 30)
    @Column(name = "title", length = 30)
    private String title;

    @Size(max = 256)
    @Column(name = "image_url", length = 256)
    private String imageUrl;

    @Size(max = 256)
    @Column(name = "hash_key", length = 256)
    private String hashKey;

    @Size(max = 10)
    @Column(name = "represent_station", length = 10)
    private String representStation;

    @OneToMany(mappedBy = "course", fetch = FetchType.LAZY)
    private List<MyCourse> myCourseList = new ArrayList<>();

    @OneToMany(mappedBy = "course", fetch = FetchType.LAZY)
    private List<CourseReview> courseReviewList = new ArrayList<>();

    @OneToMany(mappedBy = "course", fetch = FetchType.LAZY)
    private List<CoursePlace> coursePlaceList = new ArrayList<>();

    @OneToMany(mappedBy = "course", fetch = FetchType.LAZY)
    private List<Schedule> scheduleList = new ArrayList<>();

    @Builder
    public Course(String courseCategory,
                  String title,
                  String imageUrl,
                  String hashKey,
                  String representStation) {
        this.courseCategory = courseCategory;
        this.title = title;
        this.imageUrl = imageUrl;
        this.hashKey = hashKey;
        this.representStation = representStation;
    }
}