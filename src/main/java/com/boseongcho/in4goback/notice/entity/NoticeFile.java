package com.boseongcho.in4goback.notice.entity;


import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import javax.transaction.Transactional;

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

    @Column(name = "FILE_SIZE")
    private String fileSize; // 파일 사이즈
}
