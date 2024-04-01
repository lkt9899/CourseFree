package com.a603.ofcourse.domain.place.domain;

import com.a603.ofcourse.global.domain.Points;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table(name = "subway")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Subway {
    @Id
    @Column(name = "subway_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Embedded
    private Points points;

    public Subway(Points points) {
        this.points = points;
    }
}