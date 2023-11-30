package com.boseongcho.in4goback.member.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "AUTHORITY")
public class Authority {


    @Id
    @Column(name = "AUTH_NUMBER")
    private int authNumber;   // 시퀀스 / 경우의 수 14개

//    @ManyToOne
//    @JoinColumn(name = "DEPARTMENT_CODE")
//    private Department departmentCode;

    @Column(name = "AUTH_NAME")
    private String authName; //권한 명

    //ManyToOne
    //에서는
    //One이 필요하면 클래스 변수로,
    //필요없으면 기본 자료형으로 받아줌.
    //
    //oneToMany에서 Many가 필요하면 List로 받고, 아니면 안써줌
}
