package com.a603.ofcourse.domain.course.repository;

import com.a603.ofcourse.domain.course.domain.CoursePlace;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CoursePlaceRepository extends JpaRepository<CoursePlace, Integer> {
    List<CoursePlace> findAllByCourseId(int courseId);
}
