package com.boseongcho.in4goback.approval.repository;

import com.boseongcho.in4goback.approval.entity.Approval;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ApprovalRepository extends JpaRepository<Approval, String> {

    @Query("SELECT a FROM Approval a WHERE a.approvalMem.memCode = ?1")
    List<Approval> getApprovalList(String memCode);
//    @EntityGraph(value = "Approval.fetchAll", type = EntityGraph.EntityGraphType.LOAD)
    @Query("SELECT a FROM Approval a where a.approvalMem.memCode = ?1")
    Page<Approval> getApprovalList(String memCode, Pageable paging);

    @Query("SELECT a FROM Approval a WHERE a.approvalMem.memCode = :memCode" +
            " AND to_char(a.reportDate, 'YYYY-MM-DD') BETWEEN :startDate AND :endDate ORDER BY a.reportDate desc ")
    List<Approval> getSearchApproval(String memCode, String startDate, String endDate);
}
