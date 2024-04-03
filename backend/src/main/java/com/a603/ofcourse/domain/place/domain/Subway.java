package com.a603.ofcourse.domain.place.domain;

import com.a603.ofcourse.global.common.Points;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Subway {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String line;

    private String stationName;

    @Embedded
    private Points points;

    @Builder
    public Subway(Integer id, String line, String stationName, Points points) {
        this.id = id;
        this.line = line;
        this.stationName = stationName;
        this.points = points;
    }
}