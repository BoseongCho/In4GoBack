package com.boseongcho.in4goback.approval.entity;


import lombok.*;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "APPROVAL")
@DynamicInsert
public class InsertApproval {

    @Id
    @Column(name = "DOC_CODE")
//    시퀀스 코드 작성 필
    private String docCode;

    @Column(name = "MEM_CODE")
    private String memCode; // memCode FK 상신인

    @Column(name = "DOC_TYPE")
    private String docType;  // 결재종류(ex:업무, 근태)

    @Column(name = "CONTENT")
    private String content; //내용

    @Column(name ="TITLE")
    private String title; //제목
}
