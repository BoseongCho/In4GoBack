package com.boseongcho.in4goback.member.entity;


import javax.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@ToString
@Table(name = "MEMBER")
public class Member {

    @Id
    @Column(name = "MEM_CODE")
    private String memCode; //사원번호 PK
    @Column(name = "MEM_NAME")
    private String memName;
    @Column(name = "PASSWORD")
    private String password;
    @Column(name = "REGI_NUMBER")
    private String regiNumber;
    @Column(name = "GENDER")
    private char gender;
    @Column(name = "PHONE")
    private String phone;
    @Column(name = "EMAIL")
    private String email;
    @Column(name = "NATIONALITY")
    private String nationality;
    @Column(name = "MEM_PIC")
    private String memPic;
    @Column(name = "IS_MARRIED")
    private char isMarried;
    @Column(name = "IS_WORING")
    private char isWorking;  // 재직구분
    @Column(name = "ADDRESS")
    private String address;

    @OneToMany
    @JoinColumn(name = "MEM_CODE")
    private List<MemAuthority> memAuthorityList; //권한 목록
}
