package com.a603.ofcourse.domain.course.domain;

import com.a603.ofcourse.domain.place.domain.Place;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table(name = "course_place")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CoursePlace {
    @Id
    @Column(name = "course_place_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "course_id")
    private Course course;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "place_id")
    private Place place;

    public CoursePlace(Course course, Place place) {
        addRelateCourse(course);
        addRelatedPlace(place);
    }

    private void addRelateCourse(Course course) {
        this.course = course;
        course.getCoursePlaceList().add(this);
    }

    private void addRelatedPlace(Place place) {
        this.place = place;
        place.getCoursePlaceList().add(this);
    }

}