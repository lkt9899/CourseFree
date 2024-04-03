package com.a603.ofcourse.domain.member.dto.response;

import com.a603.ofcourse.domain.member.dto.CoursePlaceDetails;
import lombok.Getter;

import java.util.List;

@Getter
public class CoursePlaceListResponse {
    private List<CoursePlaceDetails> coursePlaceDetailsList;

    private CoursePlaceListResponse(List<CoursePlaceDetails> coursePlaceDetailsList){
        this.coursePlaceDetailsList = coursePlaceDetailsList;
    }

    public static CoursePlaceListResponse toResponse(List<CoursePlaceDetails> coursePlaceDetailsList){
        return new CoursePlaceListResponse(coursePlaceDetailsList);
    }
}
