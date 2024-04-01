package com.a603.ofcourse.global.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Embeddable
@NoArgsConstructor
@AllArgsConstructor
public class Points {

    public static final double EARTH_RADIUS = 6371e3; // in meters

    @Column(name = "latitude")
    private double lat;   // 위도
    @Column(name = "longitude")
    private double lng;   // 경도

    private Points calculatePoints(double distance, boolean isMax) {
        double deltaLat = Math.toDegrees(distance / EARTH_RADIUS);
        double deltaLon = Math.toDegrees(distance / (EARTH_RADIUS * Math.cos(Math.toRadians(lat))));
        double newLat = lat + (isMax ? deltaLat : -deltaLat);
        double newLon = lng + (isMax ? deltaLon : -deltaLon);
        return new Points(newLat, newLon);
    }

    public Points getMinPoints(double distance) {
        return calculatePoints(distance, false);
    }

    public Points getMaxPoints(double distance) {
        return calculatePoints(distance, true);
    }

}