package com.boseongcho.in4goback.member.entity;


import javax.persistence.*;

import com.boseongcho.in4goback.deptandteam.entity.Department;
import com.boseongcho.in4goback.deptandteam.entity.Team;
import com.boseongcho.in4goback.position.entity.Position;
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
    @Column(name = "MEM_PIC")
    private String memPic;
    @Column(name = "IS_WORKING")
    private char isWorking;  // 재직구분
    @Column(name = "ADDRESS")
    private String address;

    // FK
    @ManyToOne
    @JoinColumn(name = "DEPARTMENT_CODE")
    private Department departmentCode; // 부서코드

    @OneToOne
    @JoinColumn(name = "TEAM_CODE")
    private Team teamCode; // 팀코드

    @ManyToOne
    @JoinColumn(name = "POSITION_CODE")
    private Position positionCode; // 직급코드

    @OneToMany
    @JoinColumn(name = "MEM_CODE")
    private List<MemAuthority> memAuthorityList; //권한 목록
}
