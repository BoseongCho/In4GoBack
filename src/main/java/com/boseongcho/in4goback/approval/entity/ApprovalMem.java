package com.boseongcho.in4goback.approval.entity;


import com.boseongcho.in4goback.deptandteam.entity.Department;
import com.boseongcho.in4goback.position.entity.Position;
import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "MEMBER")
@NamedEntityGraph(name = "ApprovalMem.fetchAll", attributeNodes = {
        @NamedAttributeNode("departmentCode"),
        @NamedAttributeNode("positionCode"),
})
public class ApprovalMem {

    @Id
    @Column(name = "MEM_CODE")
    private String memCode; // 사원번호 PK
    @Column(name = "MEM_NAME")
    private String memName;
    @Column(name = "IS_WORKING")
    private char isWorking;  // 재직구분

    @ManyToOne
    @JoinColumn(name = "DEPARTMENT_CODE")
    private Department departmentCode; // 부서코드

    @ManyToOne
    @JoinColumn(name = "POSITION_CODE")
    private Position positionCode; // 직급코드

}
