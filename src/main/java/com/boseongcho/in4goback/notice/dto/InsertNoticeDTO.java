package com.boseongcho.in4goback.notice.dto;

import com.boseongcho.in4goback.approval.dto.DocAttachmentDTO;
import lombok.*;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class InsertNoticeDTO {

    private String noticeNo;
    private String memCode; // memCode FK+PK 상신인
    private String title; //제목
    private String content; //내용
    private Date writeDate; //작성일
    private int readCount; // 조회수
    private int isPinned; // 상단고정 0,1
    private List<DocAttachmentDTO> docAttachmentList; // 공지_첨부파일
}
