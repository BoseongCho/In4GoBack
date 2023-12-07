package com.boseongcho.in4goback.approval.dto;


import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ApprovalMemDTO {

    private String memCode; //사원번호 PK
    private String memName;

    // FK
//    private DepartmentDTO departmentCode; // 부서코드
//    private PositionDTO positionCode; // 직급코드
}
