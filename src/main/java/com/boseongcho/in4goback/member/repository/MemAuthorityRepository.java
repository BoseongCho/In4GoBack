package com.boseongcho.in4goback.member.repository;

import com.boseongcho.in4goback.member.entity.MemAuthority;
import com.boseongcho.in4goback.member.entity.MemAuthorityPK;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemAuthorityRepository extends JpaRepository<MemAuthority, MemAuthorityPK> {
}
