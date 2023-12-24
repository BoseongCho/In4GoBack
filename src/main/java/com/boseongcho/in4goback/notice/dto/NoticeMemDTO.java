package com.boseongcho.in4goback.notice.dto;


import com.boseongcho.in4goback.deptandteam.dto.DepartmentDTO;
import com.boseongcho.in4goback.position.dto.PositionDTO;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class NoticeMemDTO {

    private String memCode; //사원번호 PK
    private String memName;

    // FK
    private DepartmentDTO departmentCode; // 부서코드
    private PositionDTO positionCode; // 직급코드
}
