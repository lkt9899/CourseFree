package com.a603.ofcourse.domain.place.domain;

import com.a603.ofcourse.domain.course.domain.CoursePlace;
import com.a603.ofcourse.domain.schedule.domain.SchedulePlace;
import com.a603.ofcourse.global.domain.Points;
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

    private String name;

    @Embedded
    private Points points;

    private String address;

    private String imageUrl;

    @Enumerated(EnumType.STRING)
    private PlaceCategory placeCategory;

    private Integer reviewCount;

    @OneToMany(mappedBy = "place", fetch = FetchType.LAZY)
    private List<CoursePlace> coursePlaceList = new ArrayList<>();

    @OneToMany(mappedBy = "place", fetch = FetchType.LAZY)
    private List<PlaceReview> placeReviewList = new ArrayList<>();

    @OneToMany(mappedBy = "place", fetch = FetchType.LAZY)
    private List<SchedulePlace> schedulePlaceList = new ArrayList<>();

    @Builder
    public Place(
            String name,
            Points points,
            String address,
            String imageUrl,
            PlaceCategory placeCategory) {
        this.reviewCount = 0;
        this.name = name;
        this.points = points;
        this.address = address;
        this.imageUrl = imageUrl;
        this.placeCategory = placeCategory;
    }
}