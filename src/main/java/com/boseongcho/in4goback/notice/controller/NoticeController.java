package com.boseongcho.in4goback.notice.controller;

import com.boseongcho.in4goback.common.ResponseDTO;
import com.boseongcho.in4goback.notice.dto.InsertNoticeDTO;
import com.boseongcho.in4goback.notice.service.NoticeService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
@AllArgsConstructor
public class NoticeController {

    private final NoticeService noticeService;


    @Operation(summary = "공지사항 목록", description = "공지사항 목록", tags = {"NoticeController"})
    @GetMapping("/notice")
    public ResponseEntity<ResponseDTO> getNoticeList() {

        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "조회 성공", noticeService.getNoticeList()));
    }

    @Operation(summary = "공지사항 상세페이지", description = "공지사항 상세페이지", tags = {"NoticeController"})
    @GetMapping("/notice/detail")
    public ResponseEntity<ResponseDTO> getNoticeDetail(@RequestParam String no) {

        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "조회 성공", noticeService.getNoticeDetail(no)));
    }

    @Operation(summary = "공지사항 업로드", description = "공지사항 업로드", tags = { "NoticeController" })
    @PostMapping(value = "/notice/insert")
    public ResponseEntity<Object> insertApproval(@RequestBody InsertNoticeDTO insertNoticeDTO){

        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "결제문서 상신 완료", noticeService.insertNotice(insertNoticeDTO)));
    }

    @Operation(summary = "공지사항 첨부파일 추가", description = "공지사항 첨부파일 추가", tags = { "NoticeController" })
    @PostMapping(value = "/notice/insertFile")
    public ResponseEntity<ResponseDTO> insertNoticeDoc(@RequestParam("file")List<MultipartFile> files
            , @RequestParam String noticeNo){

        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "첨부파일 추가   완료", noticeService.insertNoticeDoc(files, noticeNo)));
    }
}

