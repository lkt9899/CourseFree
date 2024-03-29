package com.a603.ofcourse.domain.member.service;

import com.a603.ofcourse.domain.member.domain.NicknameHashMap;
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
    private final NicknameHashMap nicknameHashMap;

    /*
    작성자 : 김은비
    작성내용 : 닉네임 중복 체크
     * @param nickname, memberId
     * @return 중복 아니면 false, 중복이면 true
     */
    public boolean isDuplicateNickName(String nickName){
        //데이터베이스에 없고 해쉬맵에도 없으면
        if(!profileRepository.existsByNickname(nickName) && !nicknameHashMap.isExistInHashMap(nickName)){
            return false;
        }
        return false;
    }

    /*
    작성자 : 김은비
    작성내용 : 닉네임 해쉬맵에 저장
     * @prama nickname
     * @param memberId
     * @return 저장 성공하면 true, 실패하면 false
     */
    public boolean saveNicknameToHashMap(String nickname, Integer memberId){
        return nicknameHashMap.saveNicknameInHashMap(nickname, memberId);
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
