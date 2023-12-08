package com.boseongcho.in4goback.approval.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class DocAttachmentDTO {

    private int attachNo; // 시퀀스

    private String docCode;

    private String memCode; // 상신인

    private String fileName; // 파일이름

    private String url; // 파일주소

    private String pathName; //저장이름
}
