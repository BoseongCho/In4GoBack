package com.boseongcho.in4goback.notice.dto;

import lombok.*;
import org.springframework.lang.Nullable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class NoticeFileDTO {

    private int fileNo; // 시퀀스

    private String noticeNo;

    private String fileName; // 파일이름

    private String url; // 파일주소

    private String fileSize; // 파일 사이즈
}
