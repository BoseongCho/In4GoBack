package com.boseongcho.in4goback.member.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class AuthorityDTO {

	private int authNumber;   // 시퀀스 / 경우의 수 14개

//	private DepartmentDTO departmentCode;

	private String authName; //권한 명
}
