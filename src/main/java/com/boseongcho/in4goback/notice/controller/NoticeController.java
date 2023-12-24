package com.boseongcho.in4goback.notice.controller;

import com.boseongcho.in4goback.common.ResponseDTO;
import com.boseongcho.in4goback.notice.dto.InsertNoticeDTO;
import com.boseongcho.in4goback.notice.dto.NoticeDTO;
import com.boseongcho.in4goback.notice.service.NoticeService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
@AllArgsConstructor
public class NoticeController {

    private final NoticeService noticeService;

    @Operation(summary = "공지사항 업로드", description = "공지사항 업로드", tags = { "NoticeController" })
    @PostMapping(value = "/notice/insert")
    public ResponseEntity<Object> insertApproval(@RequestBody InsertNoticeDTO insertNoticeDTO){

        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "결제문서 상신 완료", noticeService.insertNotice(insertNoticeDTO)));
    }

}

