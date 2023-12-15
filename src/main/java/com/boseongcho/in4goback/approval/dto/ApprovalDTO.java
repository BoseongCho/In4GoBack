package com.boseongcho.in4goback.approval.dto;


import lombok.*;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ApprovalDTO {


    private String docCode;
    private ApprovalMemDTO approvalMem; // memCode FK+PK 상신인
    private String docType;  // 결재종류(ex:업무, 근태)
    private String isApproved; // W/Y/N
    private String content; //내용
    private Date reportDate; //작성일

    private List<ApproverDTO> approverList; // 결재자 리스트
    private List<RefereeDTO> refereeList; // 참조인 리스트

    private String typeToMe; // 상신/결재/참조

    private String title; //제목
    private BookmarkDTO bookmark; //
    private List<DocAttachmentDTO> docAttachmentList; // 결재_첨부파일

}
