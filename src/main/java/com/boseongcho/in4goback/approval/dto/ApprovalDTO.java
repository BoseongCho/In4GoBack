package com.boseongcho.in4goback.approval.dto;


import lombok.*;

import java.util.Date;

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

    private String title; //제목

}
