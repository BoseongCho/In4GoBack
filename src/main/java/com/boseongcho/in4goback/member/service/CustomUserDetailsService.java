package com.boseongcho.in4goback.member.service;

import com.boseongcho.in4goback.member.repository.MemberRepository;
import com.boseongcho.in4goback.member.dto.MemberDTO;
import com.boseongcho.in4goback.member.entity.MemAuthority;
import com.boseongcho.in4goback.member.entity.Member;
import com.boseongcho.in4goback.member.repository.MemberRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    private final MemberRepository memberRepository;
    private final ModelMapper modelMapper; // config에 ModelMapper Bean등록된상태.

    @Autowired
    public CustomUserDetailsService(MemberRepository memberRepository, ModelMapper modelMapper) {
        this.memberRepository = memberRepository;
        this.modelMapper = modelMapper;
    }

    @Transactional
    @Override
    public UserDetails loadUserByUsername(String memCode) throws UsernameNotFoundException {

        /*
         * org.hibernate.LazyInitializationException 에러가 발생한다면...
         * 조회라도 @Transactional을 달자!!
         * 해당 에러는 영속성 컨텍스트가 도중에 종료되어 발생하는 오류이다.
         * @Transactional을 달면 해당 메소드가 끝날 때까지 하나의 영속성 컨텍스트가 유지되어 뒤늦게 연관관계에 있는
         * 엔티티를 활용함에 있어서 문제되지 않는다.
         */

        Member member = memberRepository.findByMemCode(memCode);

        /* MemberDTO는 엔티티를 옮겨 담는 DTO이자 UserDetails이다. */
        MemberDTO memberDTO = modelMapper.map(member, MemberDTO.class);

        /* 엔티티로는 MemberDTO에 추가한 Collection<GrantedAuthority> authorities 속성이 옮겨담아지지 않는다. */
        List<GrantedAuthority> authorityList = new ArrayList<>();
        for(MemAuthority memAuthority : member.getMemAuthorityList()){
            String authorityName = memAuthority.getAuthNumber().getAuthName();
            authorityList.add(new SimpleGrantedAuthority(authorityName));
        }

        memberDTO.setAuthorities(authorityList);

        return memberDTO;
    }


}
