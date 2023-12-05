package com.boseongcho.in4goback.Approval.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Entity
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Approval {

    @Id
    @Column(name = "DOC_CODE")
    //시퀀스 추가 구문 추가 필
    private String docCode;

    @OneToOne
    @JoinColumn(name = "MEM_CODE")
    private ApprovalMem approvalMem;

    @Column(name = "DOC_TYPE")
    private String docType; // 결재종류(ex:업무, 경영지원, 사업 등)

    @Column(name = "IS_APPROVED")
    private String isApproved; // W/Y/N

    @Column(name = "CONTENT")
    private String content; //내용

    @Column(name ="reportDate")
    private Date reportDate; //작성일

    @Column(name ="TITLE")
    private String title; //제목

    /*
    상신함 - memCode = 나
    참조된함 - WHERE REFFEREE.memCode = 나
    결재함 - WHERE APPROVER.memCode = 나
    * */
}
