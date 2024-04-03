package com.a603.ofcourse.domain.member.dto.request;

import com.a603.ofcourse.domain.member.domain.enums.Gender;
import lombok.Getter;

@Getter
public class CoupleInfoRequest {
    private String nickname;
    private String image;
    private Gender gender;
    private String coupleNickname;
}
