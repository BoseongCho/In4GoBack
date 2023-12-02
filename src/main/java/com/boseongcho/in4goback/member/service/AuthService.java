package com.boseongcho.in4goback.member.service;

import com.boseongcho.in4goback.exception.LoginFailedException;
import com.boseongcho.in4goback.jwt.TokenProvider;
import com.boseongcho.in4goback.member.dto.MemberDTO;
import com.boseongcho.in4goback.member.dto.TokenDTO;
import com.boseongcho.in4goback.member.entity.Member;
import com.boseongcho.in4goback.member.repository.MemAuthorityRepository;
import com.boseongcho.in4goback.member.repository.MemberRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
@AllArgsConstructor
public class AuthService {

    private static final Logger log = LoggerFactory.getLogger(AuthService.class);
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    private final TokenProvider tokenProvider;
    private final ModelMapper modelMapper;
    private final MemAuthorityRepository memAuthorityRepository;

    public Object login(MemberDTO memberDTO){
        log.info("[AuthService] Login Start ============================");
        log.info("[AuthService] {}", memberDTO);
        
        /* 1. 아이디 조회 */
        Member member = memberRepository.findByMemCode(memberDTO.getMemCode());
        System.out.println("member들어왔니..??= " + member);
        if(member == null){
            throw new LoginFailedException(memberDTO.getMemCode() + "를 찾을 수 없습니다.");
        } // 예외처리를 통해 밑으로 내려가지 않게함.
        
        /* 2. 비밀번호 매칭(BCrypt 암호화 라이브러리 bean을 의존성 주입받아 처리하는 부분부터 security 설정 부분을 추가) */
        /* matches(평문, 다이제스트) */
        if(!passwordEncoder.matches(memberDTO.getPassword(), member.getPassword())){
            log.info("[AuthService] Password Match Fail!!!");
            throw new LoginFailedException("잘못된 비밀번호 입니다.");
        }
        
        /* 3. 토큰 발급 */
        TokenDTO tokenDTO = tokenProvider.generateTokenDTO(member);
        log.info("[AuthService] tokenDTO {}", tokenDTO);

        log.info("[AuthService] Login End ===================");
        return tokenDTO;
    }
}










