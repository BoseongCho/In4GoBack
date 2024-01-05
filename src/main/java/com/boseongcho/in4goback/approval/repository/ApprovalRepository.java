package com.boseongcho.in4goback.approval.repository;

import com.boseongcho.in4goback.approval.entity.Approval;
import com.boseongcho.in4goback.approval.entity.ApprovalForTotal;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ApprovalRepository extends JpaRepository<Approval, String> {

    @Query("SELECT a FROM ApprovalForTotal a WHERE a.memCode = ?1")
    List<ApprovalForTotal> getSubmitList(String memCode);
//    @EntityGraph(value = "Approval.fetchAll", type = EntityGraph.EntityGraphType.LOAD)
    @Query("SELECT a FROM Approval a where a.approvalMem.memCode = ?1")
    Page<Approval> getSubmitList(String memCode, Pageable paging);

    @Query("SELECT a FROM ApprovalForTotal a JOIN Approver v ON(a.docCode = v.docCode) WHERE v.memCode = ?1")
    List<ApprovalForTotal> getApproverList(String memCode);
    @Query("SELECT a FROM Approval a JOIN Approver v ON(a.docCode = v.docCode) WHERE v.memCode = ?1")
    Page<Approval> getApproverList(String memCode, Pageable paging);



    @Query("SELECT a FROM ApprovalForTotal a JOIN Bookmark b ON(a.docCode = b.docCode) WHERE b.memCode = ?1")
    List<ApprovalForTotal> getBookmarkList(String memCode);
    @Query("SELECT a FROM Approval a JOIN Bookmark b ON(a.docCode = b.docCode) WHERE b.memCode = ?1")
    Page<Approval> getBookmarkList(String memCode, Pageable paging);

    @Query("SELECT a FROM ApprovalForTotal a JOIN Referee r ON(a.docCode = r.docCode) WHERE r.memCode = ?1")
    List<ApprovalForTotal> getReferredList(String memCode);

    @Query("SELECT a FROM Approval a JOIN Referee r ON(a.docCode = r.docCode) WHERE r.memCode = ?1")
    Page<Approval> getReferredList(String memCode, Pageable paging);

    ///////////////////////////////////////////////

    @Query("SELECT a FROM ApprovalForTotal a WHERE a.memCode = ?1 AND a.docType = ?2")
    List<ApprovalForTotal> getSubmitList(String memCode, String docType);
    //    @EntityGraph(value = "Approval.fetchAll", type = EntityGraph.EntityGraphType.LOAD)
    @Query("SELECT a FROM Approval a where a.approvalMem.memCode = ?1 AND a.docType = ?2")
    Page<Approval> getSubmitList(String memCode, Pageable paging, String docType);

    @Query("SELECT a FROM ApprovalForTotal a JOIN Approver v ON(a.docCode = v.docCode) WHERE v.memCode = ?1 AND a.docType = ?2")
    List<ApprovalForTotal> getApproverList(String memCode, String docType);
    @Query("SELECT a FROM Approval a JOIN Approver v ON(a.docCode = v.docCode) WHERE v.memCode = ?1 AND a.docType = ?2")
    Page<Approval> getApproverList(String memCode, Pageable paging, String docType);

    @Query("SELECT a FROM ApprovalForTotal a JOIN Referee r ON(a.docCode = r.docCode) WHERE r.memCode = ?1 AND a.docType = ?2")
    List<ApprovalForTotal> getReferredList(String memCode, String docType);

    @Query("SELECT a FROM Approval a JOIN Referee r ON(a.docCode = r.docCode) WHERE r.memCode = ?1 AND a.docType = ?2")
    Page<Approval> getReferredList(String memCode, Pageable paging, String docType);

    @Query("SELECT a FROM Approval a WHERE a.approvalMem.memCode = :memCode" +
            " AND to_char(a.reportDate, 'YYYY-MM-DD') BETWEEN :startDate AND :endDate ORDER BY a.reportDate desc ")
    List<Approval> getSearchApproval(String memCode, String startDate, String endDate);
}
