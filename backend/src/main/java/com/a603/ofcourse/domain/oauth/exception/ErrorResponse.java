package com.a603.ofcourse.domain.oauth.exception;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.Getter;
/*
JSON 형식의 에러 응답
 */
@Getter
@AllArgsConstructor
public class ErrorResponse {
    //JSON 형식의 문자열로 객체를 변환하기 위해 사용
    private static final ObjectMapper objectMapper = new ObjectMapper();
    private String errMsg;

    /*
    작성자 : 김은비
    작성일자 : 2024-03-13
    작성내용 : JSON 형식의 문자열로 변환하여 반환
     */
    public String convertToJson() throws JsonProcessingException{
        //현재 객체를 JSON으로 직렬화
        return objectMapper.writeValueAsString(this);
    }
}
