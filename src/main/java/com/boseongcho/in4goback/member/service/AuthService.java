package com.boseongcho.in4goback.member.service;

import com.boseongcho.in4goback.jwt.TokenProvider;
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
    private static final Logger log = LoggerFactory.getLogger(AuthService.class);
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    private final TokenProvider  tokenProvider;
    private final ModelMapper modelMapper;
    private final MemAuthorityRepository memAuthorityRepository;
}
