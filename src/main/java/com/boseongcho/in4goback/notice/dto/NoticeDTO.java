package com.boseongcho.in4goback.notice.dto;

import com.boseongcho.in4goback.approval.dto.DocAttachmentDTO;
import com.boseongcho.in4goback.notice.entity.NoticeFile;
import lombok.*;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class NoticeDTO {

    private String noticeNo;
    private NoticeMemDTO noticeMem; // memCode FK+PK 상신인
    private String title; //제목
    private String content; //내용
    private String writeDate; //작성일
    private int readCount; // 조회수
    private int isPinned; // 상단고정 0,1
    private List<NoticeFile> noticeFileList; // 공지_첨부파일
}
