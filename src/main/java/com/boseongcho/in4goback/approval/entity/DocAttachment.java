package com.boseongcho.in4goback.approval.entity;


import lombok.*;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name ="DOC_ATTACHMENT")
@DynamicInsert
public class DocAttachment {

    @Id
    @Column(name = "ATTACH_NO")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_ATTACH_CODE")
    @SequenceGenerator(sequenceName = "SEQ_ATTACHMENT_CODE", allocationSize = 1, name = "SEQ_ATTACH_CODE")
    private int attachNo; // 시퀀스

    @Column(name = "DOC_CODE")
    private String docCode;

    @Column(name = "FILE_NAME")
    private String fileName; // 파일이름

    @Column(name = "URL")
    private String url; // 파일주소

}
