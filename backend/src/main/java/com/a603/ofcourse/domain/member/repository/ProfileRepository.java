package com.a603.ofcourse.domain.member.repository;

import com.a603.ofcourse.domain.member.domain.Profile;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProfileRepository extends JpaRepository<Profile, Integer> {
    Profile findByMemberId(int memberId);
    Boolean existsByNickname(String nickName);
}
