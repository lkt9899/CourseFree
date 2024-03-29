package com.a603.ofcourse.domain.member.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ProfileInfoRequest {
    private String nickName;
    private String gender;
    private Preference preference;
}
