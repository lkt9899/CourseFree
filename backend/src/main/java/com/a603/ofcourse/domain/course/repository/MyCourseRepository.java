package com.a603.ofcourse.domain.course.repository;

import com.a603.ofcourse.domain.course.domain.MyCourse;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MyCourseRepository extends JpaRepository<MyCourse, Integer> {
}
