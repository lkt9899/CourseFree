package com.a603.ofcourse.domain.member.dto;

import lombok.Getter;

@Getter
public class MyFavoriteCourse {
    private int courseId;
    private String title;
    private String imageUrl;

    private MyFavoriteCourse(int courseId, String title, String imageUrl){
        this.courseId = courseId;
        this.title = title;
        this.imageUrl = imageUrl;
    }

    public static MyFavoriteCourse from(int courseId, String title, String imageUrl){
        return new MyFavoriteCourse(courseId, title, imageUrl);
    }
}
