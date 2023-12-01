package com.boseongcho.in4goback.member.repository;

import com.boseongcho.in4goback.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, String> {

    Member findByMemCode(String memCode);
}
