package com.a603.ofcourse.domain.search.repository;

import com.a603.ofcourse.domain.search.document.Station;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface StationRepository extends ElasticsearchRepository<Station, Integer>, CustomStationRepository {
}
