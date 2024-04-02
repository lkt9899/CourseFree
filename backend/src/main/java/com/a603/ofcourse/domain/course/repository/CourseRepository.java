package com.a603.ofcourse.domain.course.repository;

import com.a603.ofcourse.domain.course.domain.Course;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface CourseRepository extends JpaRepository<Course, Integer> {
    Optional<Course> findByHashKey(String hashKey);

    @Query("select distinct c " +
            "from Course c " +
            "join fetch c.postList " +
            "order by c.useCount desc")
    List<Course> findCourseAndPost(Pageable pageable);
}
