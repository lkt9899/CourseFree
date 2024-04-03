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
    private String partnerImage;

    private MemberInfoResponse(String nickname, String image, String gender, boolean isCouple, String coupleNickname, String partnerNickname, String partnerImage) {
        this.nickname = nickname;
        this.image = image;
        this.gender = gender;
        this.isCouple = isCouple;
        this.coupleNickname = coupleNickname;
        this.partnerNickname = partnerNickname;
        this.partnerImage = partnerImage;
    }

    public static MemberInfoResponse toResponse(String nickname, String imageUrl, String gender, boolean isCouple, String coupleNickname, String partnerName, String partnerImage) {
        return new MemberInfoResponse(nickname, imageUrl, gender, isCouple, coupleNickname, partnerName, partnerImage);
    }
}
