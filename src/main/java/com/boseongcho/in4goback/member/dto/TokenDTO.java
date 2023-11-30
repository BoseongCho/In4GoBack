package com.boseongcho.in4goback.member.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class TokenDTO {

    private String grantType; //토큰 타입
    private String memberName; // 인증받은 회원 이름
    //이름을 사용하는 이유는 userDetail에 이름으로 load하는 메소드가 정의되어있기 때문.
    //userDetailsService.loadUserByUsername
    private String accessToken; // 액세스 토큰
    private Long accessTokenExpiresIn; // Long 형의 만료 시간
}
