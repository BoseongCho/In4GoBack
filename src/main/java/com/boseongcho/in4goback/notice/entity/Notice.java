package com.boseongcho.in4goback.notice.entity;


import com.boseongcho.in4goback.approval.entity.DocAttachment;
import com.boseongcho.in4goback.common.StringPrefixSequenceGenerator;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;
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
public class Notice {

    @Id
    @Column(name = "NOTICE_NO")
    private String noticeNo;

    @OneToOne
    @JoinColumn(name = "MEM_CODE")
    private NoticeMem noticeMem;

    @Column(name = "TITLE")
    private String title; //제목

    @Column(name = "CONTENT")
    private String content; //내용

    @Column(name ="WRITE_DATE")
    private String writeDate; //작성일

    @Column(name = "READ_COUNT")
    private int readCount; // 조회수

    @Column(name = "IS_PINNED")
    private int isPinned; // 상단고정 0,1

    @OneToMany
    @JoinColumn(name = "DOC_CODE")
    private List<DocAttachment> docAttachmentList; // 공지_첨부파일

}
