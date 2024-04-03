package com.a603.ofcourse.domain.search.repository;

import com.a603.ofcourse.domain.search.document.Station;
import lombok.RequiredArgsConstructor;
import org.springframework.data.elasticsearch.client.elc.NativeQuery;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class CustomStationRepositoryImpl implements CustomStationRepository {
    private final ElasticsearchOperations elasticsearchOperations;

    public List<Station> searchWithAutoComplete(String searchKeyword) {
        Query query = NativeQuery.builder()
                .withQuery(q -> q
                        .multiMatch(m -> m
                                .query(searchKeyword)
                                .fields("stationName",
                                        "stationName.chosung",
                                        "stationName.jamo",
                                        "stationName.nori",
                                        "stationName.engtohan"
                                )
                        )
                ).build();

        SearchHits<Station> searchHits = elasticsearchOperations.search(query, Station.class);
        return searchHits.stream()
                .map(SearchHit::getContent)
                .toList();
    }
}
