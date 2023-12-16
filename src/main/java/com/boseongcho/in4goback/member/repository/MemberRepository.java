package com.boseongcho.in4goback.member.repository;

import com.boseongcho.in4goback.approval.entity.ApprovalMem;
import com.boseongcho.in4goback.member.entity.Member;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MemberRepository extends JpaRepository<Member, String> {

    Member findByMemCode(String memCode);

//    @EntityGraph(value = "ApprovalMem.fetchAll", type = EntityGraph.EntityGraphType.LOAD)
    @Query("SELECT DISTINCT m FROM ApprovalMem m WHERE m.memName LIKE concat('%', concat(:inputValue, '%')) AND m.memCode != :memCode")
    List<ApprovalMem> getSearchInfoByName(String inputValue, String memCode);

    @EntityGraph(value = "ApprovalMem.fetchAll", type = EntityGraph.EntityGraphType.LOAD)
    @Query("SELECT m FROM ApprovalMem m WHERE m.positionCode.positionName LIKE concat('%', concat(:inputValue, '%')) AND m.memCode != :memCode")
    List<ApprovalMem> getSearchInfoByPosition(String inputValue, String memCode);
}
