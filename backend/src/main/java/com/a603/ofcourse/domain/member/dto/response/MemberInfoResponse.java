package com.a603.ofcourse.domain.member.dto.response;

import lombok.Getter;

@Getter
public class MemberInfoResponse {
    private String nickname;
    private String image;
    private String gender;
    private boolean isCouple;
    private String coupleNickname;
    private String partnerNickname;

    private MemberInfoResponse(String nickname, String image, String gender, boolean isCouple, String coupleNickname, String partnerNickname) {
        this.nickname = nickname;
        this.image = image;
        this.gender = gender;
        this.isCouple = isCouple;
        this.coupleNickname = coupleNickname;
        this.partnerNickname = partnerNickname;
    }

    public static MemberInfoResponse toResponse(String nickname, String imageUrl, String gender, boolean isCouple, String coupleNickname, String partnerName) {
        return new MemberInfoResponse(nickname, imageUrl, gender, isCouple, coupleNickname, partnerName);
    }
}
