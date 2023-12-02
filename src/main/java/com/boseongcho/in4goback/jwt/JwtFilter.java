package com.boseongcho.in4goback.jwt;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;


import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/* OncePerRequestFilter : 사용자의 요청에 한번만 동작하는 필터 */
public class JwtFilter extends OncePerRequestFilter{

    private static final Logger log = LoggerFactory.getLogger(JwtFilter.class);

    public static final String AUTHORIZATION_HEADER = "Authorization"; // 사용자가 request header에 Authorization 속성으로 token을 던짐
    public static final String BEARER_PREFIX = "Bearer";    // 사용자가 던지는 토큰 값만 파싱하기 위한 접두사 저장용 변수
                                                            // (접두사는 Bearer라는 표준으로 정의됨)
    private final TokenProvider tokenProvider;

    // JwtFilter에서 if문 내부에서 5번메소드인 validation체크를 호출하고, true값이 들어오면 4번메소드인 getAuthentication를 호출한다.
    public JwtFilter(TokenProvider tokenProvider) {this.tokenProvider = tokenProvider;}

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        String jwt = resolveToken(request); // 요청에서 토큰 값 추출

        /* 추출한 토큰의 유효성 검사 후 인증을 위해 Authentication 객체를 SecurityContextHolder에 담는다.(인증) */
        if(StringUtils.hasText(jwt) && tokenProvider.validateToken(jwt)){
            Authentication authentication = tokenProvider.getAuthentication(jwt);
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
        filterChain.doFilter(request, response); // 다음 filterchain 진행
    }

    /* Request Header에서 토큰 정보 꺼내기(위에 정의한 static final 변수 두 개 사용) */
    private String resolveToken(HttpServletRequest request) {
        String bearerToken = request.getHeader(AUTHORIZATION_HEADER); // "Authorization"
        if(StringUtils.hasText(bearerToken) && bearerToken.startsWith(BEARER_PREFIX)){ 
            // Authorization Header에서 bearer로 시작하는애들  뽑아옴
            return bearerToken.substring(7);
                // 토큰생성 패턴이 bearer  eyJhbGc.. 와 같이 생성되어서 생성된 문자열에서 [bearer]부분 제거.
            //Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhZG1pbiIsImF1dGgiOlsiUk9MRV9BRE1J ..... ->여기서 Bearer 빼주고 위에서 Application/Local Storage에 담음

        }
        return null;
    }
}









