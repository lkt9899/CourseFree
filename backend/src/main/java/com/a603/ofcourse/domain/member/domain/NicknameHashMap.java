package com.a603.ofcourse.domain.member.domain;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@Component
@Slf4j
public class NicknameHashMap {
    private final ConcurrentHashMap<String, Integer> nicknameMap = new ConcurrentHashMap<>();

    /*
    작성자 : 김은비
    작성내용 : 해쉬맵에 해당 닉네임이 저장되어있는지 확인
     * @param nickname
     * @return 저장돼있으면 true, 없으면 false
     */
    public boolean isExistInHashMap(String nickname){
        return nicknameMap.containsKey(nickname);
    }
    /*
    작성자 : 김은비
    작성내용 : 해쉬맵에 닉네임 저장
     * @param nickname
     * @param memberId
     * @return 저장 성공 시 true, 실패 시 false
     */
    public boolean saveNicknameInHashMap(String nickname, Integer memberId){
        //저장 가능하면
        if(!isExistInHashMap(nickname)){
            nicknameMap.put(nickname, memberId);
            log.info("HashMap 저장 nickname : {}, memberId : {}", nickname, memberId);
            return true;
        }
        //저장 실패
        return false;
    }
}
