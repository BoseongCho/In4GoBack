package com.boseongcho.in4goback.notice.entity;


import lombok.*;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name ="NOTICE_FILE")
@DynamicInsert
public class NoticeFile {

    @Id
    @Column(name = "FILE_NO")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_ATTACH_CODE")
    @SequenceGenerator(sequenceName = "SEQ_ATTACHMENT_CODE", allocationSize = 1, name = "SEQ_ATTACH_CODE")
    private int fileNo; // 시퀀스

    @Column(name = "NOTICE_NO")
    private String noticeNo;

    @Column(name = "FILENAME")
    private String fileName; // 파일이름

    @Column(name = "URL")
    private String url; // 파일주소
}
