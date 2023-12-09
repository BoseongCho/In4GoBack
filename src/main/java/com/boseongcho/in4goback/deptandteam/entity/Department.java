package com.boseongcho.in4goback.deptandteam.entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
public class Department {

    @Id
    @Column(name = "DEPARTMENT_CODE")
    private String departmentCode; // 부서코드 PK

    @Column(name = "DEPARTMENT_NAME")
    private String departmentName; // 부서명
}
