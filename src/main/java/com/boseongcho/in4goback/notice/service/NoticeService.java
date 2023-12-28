package com.boseongcho.in4goback.notice.service;

import com.boseongcho.in4goback.approval.dto.ApprovalDTO;
import com.boseongcho.in4goback.approval.service.ApprovalService;
import com.boseongcho.in4goback.notice.dto.InsertNoticeDTO;
import com.boseongcho.in4goback.notice.dto.NoticeDTO;
import com.boseongcho.in4goback.notice.entity.InsertNotice;
import com.boseongcho.in4goback.notice.entity.Notice;
import com.boseongcho.in4goback.notice.repository.NoticeRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class NoticeService {

    private static final Logger log = LoggerFactory.getLogger(ApprovalService.class);
    private final NoticeRepository noticeRepository;
    private final ModelMapper modelMapper;


    @Transactional
    public Object insertNotice(InsertNoticeDTO insertNoticeDTO) {

        System.out.println("Notice ====== insertNoticeDTO = " + insertNoticeDTO);

        int result = 0;
        String noticeNo = "";

        try{
            InsertNotice insertNotice = modelMapper.map(insertNoticeDTO, InsertNotice.class);
            noticeRepository.save(insertNotice);
            noticeNo = insertNotice.getNoticeNo();
            result = 1;
        } catch(Exception e){
            log.info("[Notice insert] Failed!!");
        }
        return (result > 0) ? noticeNo : "실패";
    }

    public Object getNoticeList() {
        log.info("[NoticeService] getNoticeList Start =============== ");
        List<Notice> noticeList = noticeRepository.getNotice();
        return noticeList.stream().map(notice -> modelMapper.map(notice, NoticeDTO.class)).collect(Collectors.toList());
    }

    public Object getNoticeDetail(String no) {
        Notice notice = noticeRepository.getNoticeDetail(no);

        return modelMapper.map(notice, NoticeDTO.class);
    }
}
