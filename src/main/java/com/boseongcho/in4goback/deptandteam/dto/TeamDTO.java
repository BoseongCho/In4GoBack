package com.boseongcho.in4goback.deptandteam.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class TeamDTO {

    private int teamCode; // PK

    private String teamName; // 팀명
    
    private DepartmentDTO departmentCode; // 부서코드
    

}
