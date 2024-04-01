package com.a603.ofcourse.domain.course.repository;

import com.a603.ofcourse.domain.course.domain.CoursePlace;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CoursePlaceRepository extends JpaRepository<CoursePlace, Integer> {
    List<CoursePlace> findAllByCourseId(int courseId);

    @Query("select cp " +
            "from CoursePlace cp " +
            "join fetch cp.place " +
            "where cp.course.id = :courseId")
    List<CoursePlace> findByCourseId(Integer courseId);
}
