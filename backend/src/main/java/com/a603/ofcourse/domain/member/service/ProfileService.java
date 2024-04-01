package com.a603.ofcourse.domain.member.service;

import com.a603.ofcourse.domain.member.dto.request.ProfileInfoRequest;
import com.a603.ofcourse.domain.member.repository.MemberRepository;
import com.a603.ofcourse.domain.member.repository.ProfileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProfileService {
    private final ProfileRepository profileRepository;
    private final MemberRepository memberRepository;

    /*

     */
    public boolean checkNickName(String nickName){
        return profileRepository.existsByNickname(nickName);
    }

    /*
    작성자 : 김은비
    작성내용 : 멤버 프로필 설정
     * @parma
     * @return
     */
    public void saveMemberProfile(Integer memberId, ProfileInfoRequest profileInfoRequest){
        //
    }
}
