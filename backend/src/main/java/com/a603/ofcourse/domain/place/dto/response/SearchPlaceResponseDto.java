package com.a603.ofcourse.domain.place.dto.response;

import com.a603.ofcourse.domain.place.dto.PlaceDto;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Getter
public class SearchPlaceResponseDto {
    private List<PlaceDto> placeDtoList;

    public static SearchPlaceResponseDto of(List<PlaceDto> list) {
        return new SearchPlaceResponseDto(list);
    }

}
