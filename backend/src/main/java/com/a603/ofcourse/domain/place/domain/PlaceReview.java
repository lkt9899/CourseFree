package com.a603.ofcourse.domain.place.domain;

import com.a603.ofcourse.global.domain.BaseEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;

@Getter
@Entity
@Table(name = "place_review")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PlaceReview extends BaseEntity {
    @Id
    @Column(name = "place_review_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Lob
    @Column(name = "content")
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "place_id")
    private Place place;

    public PlaceReview(String content, Place place) {
        this.content = content;
        addRelatedPlace(place);
    }

    private void addRelatedPlace(Place place) {
        this.place = place;
        place.getPlaceReviewList().add(this);
    }
}