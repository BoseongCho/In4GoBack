package com.boseongcho.in4goback.approval.dto;

import lombok.*;

import java.util.List;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class InsertApprovalDTO {

    private String docCode;
    private String memCode; // 상신인
    private String docType;  // 결재종류(ex:업무, 근태) default 업무
    private String title; //제목
    private String content; //내용

    private List<ApproverDTO> approverList; // 결재자 리스트
    private List<RefereeDTO> refereeList; // 참조인 리스트
}
