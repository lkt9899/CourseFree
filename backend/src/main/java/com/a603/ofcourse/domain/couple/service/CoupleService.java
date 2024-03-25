package com.a603.ofcourse.domain.couple.service;

import ch.qos.logback.core.testUtil.RandomUtil;
import com.a603.ofcourse.domain.couple.domain.Couple;
import com.a603.ofcourse.domain.couple.domain.MemberCouple;
import com.a603.ofcourse.domain.couple.exception.CoupleErrorCode;
import com.a603.ofcourse.domain.couple.exception.CoupleException;
import com.a603.ofcourse.domain.couple.redis.InviteLink;
import com.a603.ofcourse.domain.couple.repository.CoupleRepository;
import com.a603.ofcourse.domain.couple.repository.InviteLinkRepository;
import com.a603.ofcourse.domain.couple.repository.MemberCoupleRepository;
import com.a603.ofcourse.domain.member.exception.MemberErrorCode;
import com.a603.ofcourse.domain.member.exception.MemberException;
import com.a603.ofcourse.domain.member.repository.MemberRepository;
import com.a603.ofcourse.domain.oauth.service.JwtTokenService;
import com.a603.ofcourse.domain.oauth.service.OauthService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class CoupleService {
    @Value("${invite.link.start}")
    private String linkStart;

    private final InviteLinkRepository inviteLinkRepository;
    private final CoupleRepository coupleRepository;
    private final MemberCoupleRepository memberCoupleRepository;
    private final MemberRepository memberRepository;

    /*
    작성자 : 김은비
    작성내용 : 초대링크 생성 후 레디스에 저장후 반환
     * @param memberId
     * @return String
     */
    public String generateInviteLink(Integer memberId){
        //1. UUID 생성
        UUID uuid = UUID.randomUUID();
        //2. UUID에 하이픈을 제거하고 소문자로 변환
        String uuidStr = uuid.toString().replace("-", "").toLowerCase();
        //3. 레디스에 저장
        inviteLinkRepository.save(InviteLink.builder()
                        .UUID(uuidStr)
                        .memberId(memberId)
                        .build());
        //4, 링크 생성 후 반환
        return linkStart + uuidStr;
    }

    /*
    작성자 : 김은비
    작성내용 : 접속한 링크의 uuidStr로 레디스에서 멤버아이디(초대자) 가져오기
     * @param uuidStr
     * @return memberId
     */
    public Integer getInviterIdFromLink(String uuidStr){
        //1. uuidStr로 초대 링크의 초대주체 찾기
        return inviteLinkRepository.findById(uuidStr)
                //2. inviteLink 객체가 존재하면 멤버아이디 반환
                .map(InviteLink -> InviteLink.getMemberId())
                //3. 존재하지 않으면 오류 반환
                .orElseThrow(() -> new CoupleException(CoupleErrorCode.INVALID_INVITE_LINK));
    }

    /*
    작성자 : 김은비
    작성내용 : 멤버아이디로 커플 연동 여부 조회
     * @param memberId
     * @return boolean (커플이면 true, 아니면 false 반환)
     */
    public boolean isCouple(Integer memberId){
        return memberCoupleRepository.existsByMemberId(memberId);
    }

    /*
    작성자 : 김은비
    작성내용 : 초대된 사람(visitor)과 초대한 사람(inviter)을 커플로 연동시켜주기
    * @param
    * @return
    */
    public void connectCouple(Integer visitorId, Integer inviterId){
        //둘의 아이디가 같으면 에러
        if(visitorId == inviterId){new CoupleException(CoupleErrorCode.SAME_MEMBER);}

        //초대된 사람과 초대한 사람이 모두 커플이 아닐 때
        if(!isCouple(visitorId) && !isCouple(inviterId)){
            //커플 객체 저장 후 해당 객체 아이디 반환
            Couple couple = coupleRepository.save(Couple.builder().build());

            //초대받은 사람
            memberCoupleRepository.save(new MemberCouple(couple, memberRepository.findById(visitorId)
                    .orElseThrow(() -> new MemberException(MemberErrorCode.MEMBER_DOES_NOT_EXISTS))));
            //초대한 사람
            memberCoupleRepository.save(new MemberCouple(couple, memberRepository.findById(inviterId)
                    .orElseThrow(() -> new MemberException(MemberErrorCode.MEMBER_DOES_NOT_EXISTS))));
        }
        //둘 중 하나라도 커플이면 연동 실패 오류
        else{
            new CoupleException(CoupleErrorCode.ALREADY_COUPLE_MEMBER);
        }
    }
}
