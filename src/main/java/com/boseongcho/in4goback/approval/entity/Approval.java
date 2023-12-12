package com.boseongcho.in4goback.approval.entity;

import com.boseongcho.in4goback.common.StringPrefixSequenceGenerator;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@NamedEntityGraph(name = "Approval.fetchAll", attributeNodes = {
        @NamedAttributeNode("approverList"),
        @NamedAttributeNode("approvalMem"),
//        @NamedAttributeNode("bookmark"),
})
public class Approval {

    @Id
    @Column(name = "DOC_CODE")
//    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_DOCUMENT_CODE")
//    @GenericGenerator(name = "SEQ_DOCUMENT_CODE", strategy = "com.boseongcho.in4goback.common.StringPrefixSequenceGenerator",
//            parameters = {
//                    @org.hibernate.annotations.Parameter(name = StringPrefixSequenceGenerator.VALUE_PREFIX_PARAMETER, value= "DOC_")
//            })
    private String docCode;

    @OneToOne
    @JoinColumn(name = "MEM_CODE")
    private ApprovalMem approvalMem;

    @Column(name = "DOC_TYPE")
    private String docType; // 결재종류(ex:업무, 경영지원, 사업 등)

    @Column(name = "IS_APPROVED")
    private String isApproved; // W/Y/N

    @Column(name = "CONTENT")
    private String content; //내용

    @Column(name ="reportDate")
    private Date reportDate; //작성일

    @OneToMany
    @JoinColumn(name = "DOC_CODE")
    private List<Approver> approverList; // 결재자 리스트


    @Column(name ="TITLE")
    private String title; //제목

    @OneToOne
    @JoinColumn(name = "DOC_CODE")
    private Bookmark bookmark; //

    @OneToMany
    @JoinColumn(name = "DOC_CODE")
    private List<DocAttachment> docAttachmentList; // 결재_첨부파일

    /*
    상신함 - memCode = 나
    참조된함 - WHERE REFFEREE.memCode = 나
    결재함 - WHERE APPROVER.memCode = 나
    * */
}
