package com.boseongcho.in4goback.Duty.entity;

import com.boseongcho.in4goback.Approval.entity.ApprovalMem;
import lombok.*;

import javax.persistence.*;
import java.sql.Date;

@Entity
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class RegularDutyCriteria {

    @Id
    @Column(name = "REGULAR_DUTY_CRITERIA_CODE")
    //시퀀스 구문 추가해야함
    private String regularDutyCode;

    @ManyToOne
    @JoinColumn(name = "REGULAR_DUTY_CRITERIA_CODE")
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

}
