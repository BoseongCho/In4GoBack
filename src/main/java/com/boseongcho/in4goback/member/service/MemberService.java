package com.boseongcho.in4goback.member.service;

import com.boseongcho.in4goback.member.dto.MemberDTO;
import com.boseongcho.in4goback.member.entity.Member;
import com.boseongcho.in4goback.member.repository.MemberRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class MemberService {

    private static final Logger log = LoggerFactory.getLogger(MemberService.class);
    private final MemberRepository memberRepository;
    private final ModelMapper modelMapper;


    public Object selectMemberInfo(String memCode) {
        log.info("[MemberService] selectMemberInfo Start ============");

        Member member = memberRepository.findByMemCode(memCode);

        log.info("[MemberService] {}", member);
        log.info("[MemberService] selectMemberInfo End ============");

        return modelMapper.map(member, MemberDTO.class);
    }
}
