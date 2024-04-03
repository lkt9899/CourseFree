package com.a603.ofcourse.global.common;

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



    /**
     * @author 손현조
     * @date 2024-03-19
     * @description 주 좌표의 거리를 구하는 함수, 하버사인 공식 사용
     **/
    public static double getDistance(Points p1, Points p2) {
        double dLat = Math.toRadians(p2.getLat() - p1.getLat());
        double dLon = Math.toRadians(p2.getLng() - p1.getLng());

        double tmp1 = Math.sin(dLat/2)
                * Math.sin(dLat/2)
                + Math.cos(Math.toRadians(p1.getLat()))
                * Math.cos(Math.toRadians(p2.getLat()))
                * Math.sin(dLon/2)
                * Math.sin(dLon/2);
        double tmp2 = 2 * Math.atan2(Math.sqrt(tmp1), Math.sqrt(1-tmp1));
        return Points.EARTH_RADIUS * tmp2;    // Distance in m
    }
}