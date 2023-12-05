package com.boseongcho.in4goback.Approval.repository;

import com.boseongcho.in4goback.Approval.entity.Approval;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ApprovalRepository extends JpaRepository<Approval, String> {

    @Query("SELECT DISTINCT a FROM Approval a WHERE a.approvalMem.memCode = ?1")
    List<Approval> getApprovalList(String memCode);
}
