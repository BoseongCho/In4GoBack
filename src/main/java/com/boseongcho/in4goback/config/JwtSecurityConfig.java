package com.boseongcho.in4goback.config;

import com.boseongcho.in4goback.jwt.JwtFilter;
import com.boseongcho.in4goback.jwt.TokenProvider;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/* jwt관련 필터를 UsernamePasswordAuthenticationFilter 앞쪽에 추가 */
public class JwtSecurityConfig extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {

    private final TokenProvider tokenProvider;

    // 매개변수 있는 생성자
    public JwtSecurityConfig(TokenProvider tokenProvider) {this.tokenProvider = tokenProvider;}

@Override
public void configure(HttpSecurity http) {
    JwtFilter customFilter = new JwtFilter(tokenProvider);							// JwtFilter를 jwt 패키지에 추가
    http.addFilterBefore(customFilter, UsernamePasswordAuthenticationFilter.class); // JwtFilter를 Filterchain 상에 추가
}
}
