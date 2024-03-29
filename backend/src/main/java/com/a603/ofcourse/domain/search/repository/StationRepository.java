package com.a603.ofcourse.domain.search.repository;

import com.a603.ofcourse.domain.search.document.Station;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

public interface StationRepository extends ElasticsearchRepository<Station, String> {
    List<Station> findByStationNameContainsIgnoreCase(String stationName);
}