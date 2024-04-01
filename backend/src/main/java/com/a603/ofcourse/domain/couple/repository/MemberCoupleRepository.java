package com.a603.ofcourse.domain.couple.repository;

import com.a603.ofcourse.domain.couple.domain.MemberCouple;
import com.a603.ofcourse.domain.member.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberCoupleRepository extends JpaRepository<MemberCouple, Integer> {
    Boolean existsByMemberId(Integer memberId);
    Optional<MemberCouple> findByMember(Member member);

    Optional<MemberCouple> findByMemberId(Integer memberId);
}
