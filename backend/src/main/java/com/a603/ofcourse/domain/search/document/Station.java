package com.a603.ofcourse.domain.search.document;

import com.a603.ofcourse.domain.search.dto.response.SubwayDetailResponseDto;
import com.a603.ofcourse.global.common.Points;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.*;

@Getter
@Document(indexName = "station")
@Setting(settingPath = "/elasticsearch/settings.json")
@Mapping(mappingPath = "/elasticsearch/mappings.json")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Station {

    @Id
    @Field(type = FieldType.Integer)
    private String id;

    @Field(type = FieldType.Text)
    private String stationName;

    @Field(type = FieldType.Keyword)
    private String line;

    @Field(type = FieldType.Object)
    private Points point;

    @Builder
    public Station(String stationName, String line, Points point) {
        this.stationName = stationName;
        this.line = line;
        this.point = point;
    }

    public SubwayDetailResponseDto toResponse() {
        return SubwayDetailResponseDto.builder()
                .line(line)
                .stationName(stationName)
                .point(point)
                .build();
    }
}
