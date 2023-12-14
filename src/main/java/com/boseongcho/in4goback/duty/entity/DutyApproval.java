package com.boseongcho.in4goback.duty.entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.sql.Date;

@Entity
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class DutyApproval {
    @Id
    @Column(name = "DUTY_APPROVAL_CODE")
    //시퀀스 구문 추가해야함
    private String dutyApprovalCode; // 근휴결재번호

    @ManyToOne
    @Column(name = "SPECIAL_DUTY_CRITERIA_CODE")
    private SpecialDutyCriteria specialDutyCriteriaCode; // 근휴기준번호

    @ManyToOne
    @Column(name = "SPECIAL_DUTY_CODE")
    private SpecialDuty specialDutyCode; // 근휴번호

    @Column(name = "APPROVAL_MEM_CODE")
    private String approvalMemCode;  // 결재자

    @Column(name = "APPROVAL_STATUS")
    private int approvalStatus;  // 결재상태

    @Column(name = "APPROVAL_MEM_MESSAGE")
    private String approvalMemMessage;  // 결재자메시지

    @Column(name = "APPLICATION_DATE")
    private Date applicationDate;  // 신청일자

    @Column(name = "APPROVAL_DATE")
    private Date approvalDate;  // 결재완료일자

}
