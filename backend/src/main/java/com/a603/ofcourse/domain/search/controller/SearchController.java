package com.a603.ofcourse.domain.search.controller;

import com.a603.ofcourse.domain.search.dto.response.SubwayDetailResponseDto;
import com.a603.ofcourse.domain.search.service.SearchService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class SearchController {
    private final SearchService searchService;

    @GetMapping("/subway")
    public ResponseEntity<List<SubwayDetailResponseDto>> autocomplete(@RequestParam String searchKeyword) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(searchService.findSubwayListByKeyword(searchKeyword));
    }

    @GetMapping("/subways")
    public ResponseEntity<List<SubwayDetailResponseDto>> findAll() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(searchService.findList());
    }
}
