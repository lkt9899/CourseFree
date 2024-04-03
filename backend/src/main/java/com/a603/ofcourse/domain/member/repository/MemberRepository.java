package com.a603.ofcourse.domain.member.repository;

import com.a603.ofcourse.domain.member.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Integer> {
    Optional<Member> findBySocialId(Long socialId);
}
