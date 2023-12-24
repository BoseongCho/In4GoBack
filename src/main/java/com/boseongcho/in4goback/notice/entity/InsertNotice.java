package com.boseongcho.in4goback.notice.entity;

import com.boseongcho.in4goback.approval.entity.DocAttachment;
import com.boseongcho.in4goback.common.StringPrefixSequenceGenerator;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name = "NOTICE")
@DynamicInsert
@Entity
public class InsertNotice {

    @Id
    @Column(name = "NOTICE_NO")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_NOTICE_CODE")
    @GenericGenerator(name = "SEQ_NOTICE_CODE", strategy = "com.boseongcho.in4goback.common.StringPrefixSequenceGenerator",
            parameters = {
                    @org.hibernate.annotations.Parameter(name = StringPrefixSequenceGenerator.VALUE_PREFIX_PARAMETER, value= "NOTICE_")
            })
    private String noticeNo;

    @Column(name = "MEM_CODE")
    private String memCode;

    @Column(name = "TITLE")
    private String title; //제목

    @Column(name = "CONTENT")
    private String content; //내용

    @Column(name ="WRITE_DATE")
    private Date writeDate; //작성일

    @Column(name = "READ_COUNT")
    private int readCount; // 조회수

    @Column(name = "IS_PINNED")
    private int isPinned; // 상단고정 0,1

    @OneToMany
    @JoinColumn(name = "DOC_CODE")
    private List<DocAttachment> docAttachmentList; // 공지_첨부파일
}
