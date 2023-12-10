package com.boseongcho.in4goback.approval.entity;


import lombok.*;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;
import java.util.List;

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
    private String docCode;

    @Column(name = "MEM_CODE")
    private String memCode; // memCode FK 상신인

    @Column(name = "DOC_TYPE")
    private String docType;  // 결재종류(ex:업무, 근태)

    @Column(name = "CONTENT")
    private String content; //내용

    @OneToMany
    @JoinColumn(name = "DOC_CODE")
    private List<Approver> approverList; // 결재자 리스트

    @OneToMany
    @JoinColumn(name = "DOC_CODE")
    private List<Referee> refereeList; // 참조인 리스트

    @Column(name ="TITLE")
    private String title; //제목


}
