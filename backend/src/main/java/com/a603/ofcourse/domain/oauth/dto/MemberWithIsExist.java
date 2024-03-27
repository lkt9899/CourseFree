package com.a603.ofcourse.domain.oauth.dto;


import com.a603.ofcourse.domain.member.domain.Member;
import lombok.Getter;
import lombok.Setter;
import lombok.With;

@Getter
@Setter
public class MemberWithIsExist {
    private Member member;
    private boolean isExist;

    private MemberWithIsExist(Member member, boolean isExist){
        this.member = member;
        this.isExist = isExist;
    }

    public static MemberWithIsExist from(Member member, boolean isExist){
        return new MemberWithIsExist(member, isExist);
    }
}
