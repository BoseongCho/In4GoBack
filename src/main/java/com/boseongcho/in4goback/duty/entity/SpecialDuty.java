package com.boseongcho.in4goback.duty.entity;

import lombok.*;

import javax.persistence.*;
import java.sql.Date;

@Entity
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class SpecialDuty {

    @Id
    @Column(name = "SPECIAL_DUTY_CODE")
    //시퀀스 구문 추가해야함
    private String specialDutyCode; // 근휴번호

    @ManyToOne
    @Column(name = "DUTY_MEM_CODE")
    private DutyMember dutyMemCode; // 대상사원번호

    @Column(name = "INIT_APPROVAL_CODE")
    private String initiateApprovalCode; // 신청결재번호

    @Column(name = "CORRECT_APPROVAL_CODE")
    private String correctApprovalCode; // 정정결재번호

    @Column(name = "DONE_DATE")
    private Date doneDate; // 완료처리일자

    @Column(name = "IS_DONE")
    private int isValid; // 근휴완료여부 : false=0, true=1, default=0

}
