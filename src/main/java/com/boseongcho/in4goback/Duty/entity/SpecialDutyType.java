package com.boseongcho.in4goback.Duty.entity;

import lombok.*;

import javax.persistence.*;
import java.sql.Date;

@Entity
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class SpecialDutyType {

    @Id
    @Column(name = "SPECIAL_DUTY_TYPE_CODE")
    //시퀀스 구문 추가해야함
    private String specialDutyTypeCode; // 근휴유형번호

    @Column(name ="SPECIAL_DUTY_TYPE")
    private String specialDutyType  ; // 근휴유형명

    @Column(name ="SPECIAL_DUTY_PAY")
    private int specialDutyPay; // 기본수당 (*무급=0)

}
