package com.boseongcho.in4goback.jwt;

import com.boseongcho.in4goback.exception.TokenException;
import com.boseongcho.in4goback.member.dto.TokenDTO;
import com.boseongcho.in4goback.member.entity.MemAuthority;
import com.boseongcho.in4goback.member.entity.Member;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.*;
import java.util.stream.Collectors;

@Component
public class TokenProvider {

    private static final Logger log = LoggerFactory.getLogger(TokenProvider.class);
    private static final String AUTHORITIES_KEY = "auth";
    private static final String BEARER_TYPE = "Bearer"; // bearer를 사용하라고 명시되어있음.
    private static final long ACCESS_TOKEN_EXPIRE_TIME = 10000 * 60 * 30; // 30분(ms 단위)

    private final UserDetailsService userDetailsService;

    private final Key key; // java.security.Key
    public TokenProvider(@Value("${jwt.secret}") String secretKey, UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        this.key = Keys.hmacShaKeyFor(keyBytes);
    }

    // 1번은 처음 로그인할 때 쓰이고, 인증이 필요한 페이지를 갈 때는 JwtFilter에서 요청이 걸러짐
    // JwtFilter에서 if문 내부에서 5번메소드인 validation체크를 호출하고, true값이 들어오면 4번메소드인 getAuthentication를 호출한다.
    // 2~3번 메소드는 4번 메소드를 보조함.
    
    
    /* 1. 토큰 생성 메소드 */
    public TokenDTO generateTokenDTO(Member member){

        log.info("[TokenProvider] generateTokenDTo Start ====================");
        List<String> roles = new ArrayList<>();
        for(MemAuthority memAuthority : member.getMemAuthorityList()){
            roles.add(memAuthority.getAuthNumber().getAuthName());
        }

        log.info("[TokenProvider] authorities {}", roles); // SLF4J에서 제공하는 치환문자 활용(+(덧셈)같은 연산처리 작업 생략

        /* 1-1. memCode를 "sub(토큰제목-subject)"라는 클레임으로 토큰에 추가 */
        Claims claims = Jwts.claims().setSubject(member.getMemCode());
        
        /* 1-2. 회원의 권한들을 "auth"라는 클레임으로 토큰에 추가 */
        claims.put(AUTHORITIES_KEY, roles);

        long now = System.currentTimeMillis();

        Date accessTokenExpiresIn = new Date(now + ACCESS_TOKEN_EXPIRE_TIME);
        String accessToken = Jwts.builder()
                .setClaims(claims)
                
                /* 1-3. 토큰의 만료 기간을 DATE형으로 토큰에 추가("exp"라는 클레임으로 long 형으로 토큰에 추가 */
                .setExpiration(accessTokenExpiresIn)
                .signWith(key, SignatureAlgorithm.HS512) // HS512:암호화방식
                .compact();
        log.info("[TokenProvider] generateTokenDTO end ==========================");
            // [ROLE_ADMIN,  ROLE_USER]
        return new TokenDTO(BEARER_TYPE, member.getMemName(), accessToken, accessTokenExpiresIn.getTime());
    }


    /* 2. 토큰의 등록된 클레임의 subject에서 해당 회원의 아이디를 추출 */
    public String getUserId(String token){
//        return Jwts.parserBuilder()
//                .setSigningKey(key).build()
//                .parseClaimsJws(token)
//                .getBody() // payload의 Claims 추출
//                .getSubject(); //Claims중에 등록 클레임에 해당하는 sub값 추출(회원 아이디)
        return parseClaims(token).getSubject();
    }

    /* 3. AccessToken에서 클레임 추출하는 메소드 */
    private Claims parseClaims(String token) {
        try {
            return Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).getBody();
        } catch (ExpiredJwtException e){
            return e.getClaims();
        }
    }

    /* 4. AccessToken으로 인증 객체 추출(이 클래스의 2번과 3번에 해당하는 메소드를 사용) */
    public Authentication getAuthentication(String token){

        log.info("[TokenProvider] getAuthentication Start ====================");

        /* 토큰에서 cliam들 추출(토큰 복호화) */
        Claims claims = parseClaims(token);

        if(claims.get(AUTHORITIES_KEY) == null){
            throw new RuntimeException("권한 정보가 없는 토큰입니다.");
        }

        /* 클레임에서 권한 정보 가져오기 */
        // 얘는 권한 설정이 걸려있는 페이지에 갈 때 실행된다.
        Collection<? extends GrantedAuthority> authorities =
                Arrays.stream(claims.get(AUTHORITIES_KEY).toString().split(",")) // ex: "ROLE_ADMIN"이랑 "ROLE_MEMBER"같은 문자열이 들어있는 문자열 배열
                        .map(role -> new SimpleGrantedAuthority(role)) // 문자 배열에 들어있는 권한 문자열 마다 SimpleGrantedAuthoriy 객체로 만듬.
                        .collect(Collectors.toList());
        log.info("[TokenProvider111] authorities {}", authorities);
//            [[ROLE_ADMIN,  ROLE_USER]]
        UserDetails userDetails = userDetailsService.loadUserByUsername(this.getUserId(token));

        log.info("[TokenProvider] getAuthentication End ====================");

        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }

    /* 5. 토큰 유효성 검사 */
    public boolean validateToken(String token){
        try {
            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
            return true;
        } catch(io.jsonwebtoken.security.SecurityException | MalformedJwtException e){
            log.info("[TokenProvider] 잘못된 JWT 서명입니다. ");
            throw new TokenException("잘못된 JWT 서명입니다.");
        } catch(ExpiredJwtException e){
            log.info("[TokenProvider] 만료된 JWT 토큰입니다.");
            throw new TokenException("만료된 JWT 토큰입니다.");
        } catch(IllegalArgumentException e){
            log.info("[TokenProvider] JWT 토큰이 잘못되었습니다.");
            throw new TokenException("JWT 토큰이 잘못되었습니다.");
        }
    }

}



































