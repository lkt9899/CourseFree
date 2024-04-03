package com.a603.ofcourse.domain.schedule.repository;

import com.a603.ofcourse.domain.schedule.domain.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ScheduleRepository extends JpaRepository<Schedule, Integer> {
    @Query("SELECT s FROM Schedule s where s.couple.id = :coupleId AND MONTH(s.scheduleDate) = :month")
    List<Schedule> findByCoupleIdAndMonth(int coupleId, int month);
}
