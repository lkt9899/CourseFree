package com.a603.ofcourse.domain.oauth.models;

import com.a603.ofcourse.domain.member.domain.enums.Role;
import com.a603.ofcourse.domain.member.domain.Member;
import com.a603.ofcourse.domain.member.domain.Profile;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/*
SecurityContext에 멤버 정보 저장할 때 사용하기 위한 멤버모델
SecurityContext authentication에 저장될 멤버 정보
 */
@Getter
@AllArgsConstructor
public class MemberPrincipal implements UserDetails {
    private Integer id;
    private String password;
    private String nickname;
    private Collection<? extends GrantedAuthority> authorities;

    @Setter
    private Map<String, Object> attributes;

    public static MemberPrincipal create(Member member, Profile profile){
        List<GrantedAuthority> authorities =
                Collections.singletonList(new SimpleGrantedAuthority(Role.MEMBER.getRole()));

        return new MemberPrincipal(
                member.getId(),
                "",
                profile.getNickname(),
                authorities,
                null
        );
    }

    @Override
    public String getUsername() {
        return nickname;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}


