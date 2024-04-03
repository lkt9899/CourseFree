package com.a603.ofcourse.domain.course.repository;

import com.a603.ofcourse.domain.course.domain.MyCourse;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MyCourseRepository extends JpaRepository<MyCourse, Integer> {
    List<MyCourse> findAllByMemberId(Integer memberId);

    Boolean existsByMemberIdAndCourseId(Integer memberId, Integer courseId);
}
