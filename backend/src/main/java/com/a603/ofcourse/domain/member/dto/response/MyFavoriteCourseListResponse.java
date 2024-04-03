package com.a603.ofcourse.domain.member.dto.response;

import com.a603.ofcourse.domain.member.dto.MyFavoriteCourse;
import lombok.Getter;

import java.util.List;

@Getter
public class MyFavoriteCourseListResponse {
    private List<MyFavoriteCourse> myFavoriteCourseList;

    private MyFavoriteCourseListResponse(List<MyFavoriteCourse> myFavoriteCourseList){
        this.myFavoriteCourseList = myFavoriteCourseList;
    }

    public static MyFavoriteCourseListResponse toResponse(List<MyFavoriteCourse> myFavoriteCourseList){
        return new MyFavoriteCourseListResponse(myFavoriteCourseList);
    }
}
