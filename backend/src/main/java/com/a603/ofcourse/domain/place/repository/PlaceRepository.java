package com.a603.ofcourse.domain.place.repository;

import com.a603.ofcourse.domain.place.domain.Place;
import com.a603.ofcourse.domain.place.domain.PlaceCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PlaceRepository extends JpaRepository<Place, Integer> {
    @Query("""
            SELECT p
            FROM Place p
            WHERE p.points.lat BETWEEN LEAST(:lat1, :lat2) AND GREATEST(:lat1, :lat2)
            AND p.points.lng BETWEEN LEAST(:lng1, :lng2) AND GREATEST(:lng1, :lng2)
            AND p.placeCategory = :placeCategory
            """)
    List<Place> findPlaceListByBoundary(
            PlaceCategory placeCategory,
            double lat1,
            double lng1,
            double lat2,
            double lng2
    );
}
