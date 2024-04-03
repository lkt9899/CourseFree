package com.a603.ofcourse.domain.course.dto.request;

import com.a603.ofcourse.global.common.Points;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class RecommendationRequest {
    //TODO: 지하철 역 좌표로 해야 함
    private Points points;
    private Double limitDist;
    private List<String> categoryList;
}
