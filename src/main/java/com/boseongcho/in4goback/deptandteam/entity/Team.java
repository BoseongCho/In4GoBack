package com.boseongcho.in4goback.deptandteam.entity;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "TEAM")
public class Team {

    @Id
    @Column(name = "TEAM_CODE")
    private int teamCode;
    @Column(name = "TEAM_NAME")
    private String teamName; // 팀명
    @ManyToOne
    @JoinColumn(name = "DEPARTMENT_CODE")
    private Department departmentCode; // 부서코드


}

