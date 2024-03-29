package com.a603.ofcourse.domain.place.domain;

import com.a603.ofcourse.domain.course.domain.CoursePlace;
import com.a603.ofcourse.domain.schedule.domain.SchedulePlace;
import com.a603.ofcourse.global.common.Points;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@Table(name = "place")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Place {
    @Id
    @Column(name = "place_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String url;

    private String name;

    @Embedded
    private Points points;

    private String address;

    private String placeType;

    @Enumerated(EnumType.STRING)
    private PlaceCategory placeCategory;

    private Integer reviewCount;

    private String vector;

    @OneToMany(mappedBy = "place", fetch = FetchType.LAZY)
    private List<CoursePlace> coursePlaceList = new ArrayList<>();

    @OneToMany(mappedBy = "place", fetch = FetchType.LAZY)
    private List<PlaceReview> placeReviewList = new ArrayList<>();

    @OneToMany(mappedBy = "place", fetch = FetchType.LAZY)
    private List<SchedulePlace> schedulePlaceList = new ArrayList<>();

    @Builder

    public Place(
            String url,
            String name,
            Points points,
            String address,
            String placeType,
            PlaceCategory placeCategory,
            Integer reviewCount,
            String vector) {
        this.url = url;
        this.name = name;
        this.points = points;
        this.address = address;
        this.placeType = placeType;
        this.placeCategory = placeCategory;
        this.reviewCount = reviewCount;
        this.vector = vector;
    }
}