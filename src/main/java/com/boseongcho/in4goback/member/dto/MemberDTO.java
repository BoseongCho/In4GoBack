package com.boseongcho.in4goback.member.dto;

import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class MemberDTO implements UserDetails {

    private String memCode; //사원번호 PK
    private String memName;
    private String password;
    private String regiNumber;
    private char gender;
    private String phone;
    private String email;
    private String memPic;
    private char isMarried;
    private char isWorking;  // 재직구분


    // 다 대 다 -> 일 대 다  + 일 대 다
    private List<MemAuthorityDTO> memAuthorityList; // 권한목록

    private Collection<GrantedAuthority> authorities;
    /* 이하 코드들을 UserDetails로부터 물려받는 추상메소드들을 오버라이딩 한 것이다.(필요한 것만 작성하자) */
    // 일단 Override해주지 않으면 에러.
    /* MemberDTO는 Member와 매핑 될 DTO이자 UserDetails로써 속성을 추가로 가짐 */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getUsername() {
        return null;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }
}
