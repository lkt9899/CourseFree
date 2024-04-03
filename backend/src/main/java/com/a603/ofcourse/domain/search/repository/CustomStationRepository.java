package com.a603.ofcourse.domain.search.repository;

import com.a603.ofcourse.domain.search.document.Station;

import java.util.List;


public interface CustomStationRepository {
    List<Station> searchWithAutoComplete(String searchKeyword);
}