package com.boseongcho.in4goback.Duty.entity;

import lombok.*;

import javax.persistence.*;
import java.sql.Date;
import java.util.List;

@Entity
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class SpecialDutyCriteria {

    @Id
    @Column(name = "SPECIAL_DUTY_CRITERIA_CODE")
    //시퀀스 구문 추가해야함
    private String specialDutyCriteriaCode; // 근휴기준번호

    @ManyToOne
    @JoinColumn(name = "SPECIAL_DUTY_TYPE_CODE")
    private SpecialDutyType specialDutyTypeCode; // 근휴유형번호

    @Column(name = "MEM_POSITION_CODE")
    private String memPositionCode; // 직급

    @Column(name = "MEM_HAS_DAYS")
    private int memHasDays; // 기준(보유) 일수

    @Column(name = "PAY_MULTIPLIER")
    private int payMultiplier; // 수당배율 (*직급별 배율)

}
