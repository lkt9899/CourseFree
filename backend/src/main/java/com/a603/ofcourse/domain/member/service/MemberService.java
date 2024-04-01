package com.a603.ofcourse.domain.member.service;

import com.a603.ofcourse.domain.member.domain.Member;
import com.a603.ofcourse.domain.member.exception.MemberErrorCode;
import com.a603.ofcourse.domain.member.exception.MemberException;
import com.a603.ofcourse.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;

    public Member findById(Integer memberId) {
        return memberRepository.findById(memberId).orElseThrow(
                () -> new MemberException(MemberErrorCode.MEMBER_DOES_NOT_EXISTS));
    }
}
