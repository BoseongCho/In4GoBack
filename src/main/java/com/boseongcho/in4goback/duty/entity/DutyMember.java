package com.boseongcho.in4goback.duty.entity;


import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "MEMBER")
public class DutyMember {

    @Id
    @Column(name = "MEM_CODE")
    private String memCode; // 사원번호 PK

    @Column(name = "MEM_NAME")
    private String memName;
    
    @Column(name = "IS_WORKING")
    private char isWorking;  // 재직구분 
}
