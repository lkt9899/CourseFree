package com.a603.ofcourse.domain.search.service;

import com.a603.ofcourse.domain.search.document.Station;
import com.a603.ofcourse.domain.search.dto.response.SubwayDetailResponseDto;
import com.a603.ofcourse.domain.search.repository.CustomStationRepository;
import com.a603.ofcourse.domain.search.repository.StationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.StreamSupport;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class SearchService {

    private final StationRepository stationRepository;
    public List<SubwayDetailResponseDto> findSubwayListByKeyword(String searchKeyword) {
        return stationRepository.searchWithAutoComplete(searchKeyword).stream()
                .map(Station::toResponse)
                .toList();
    }

    public List<SubwayDetailResponseDto> findList() {
        return StreamSupport.stream(stationRepository.findAll().spliterator(), false)
                .map(Station::toResponse)
                .toList();
    }
}
