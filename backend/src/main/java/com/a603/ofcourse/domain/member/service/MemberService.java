package com.a603.ofcourse.domain.member.service;

import com.a603.ofcourse.domain.member.domain.Member;
import com.a603.ofcourse.domain.member.exception.MemberErrorCode;
import com.a603.ofcourse.domain.member.exception.MemberException;
import com.a603.ofcourse.domain.member.repository.MemberRepository;
import com.a603.ofcourse.domain.oauth.service.JwtTokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;
    private final JwtTokenService jwtTokenService;

    public Member findById(Integer memberId) {
        return memberRepository.findById(memberId).orElseThrow(
                () -> new MemberException(MemberErrorCode.MEMBER_DOES_NOT_EXISTS));
    }


    public Member getMemberByToken(String token) {
        return findById((Integer) jwtTokenService.getPayload(token).get("member_id"));
    }
}
