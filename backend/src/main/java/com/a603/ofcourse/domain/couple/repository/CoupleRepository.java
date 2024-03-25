package com.a603.ofcourse.domain.couple.repository;

import com.a603.ofcourse.domain.couple.domain.Couple;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CoupleRepository extends JpaRepository<Couple, Integer> {
}
