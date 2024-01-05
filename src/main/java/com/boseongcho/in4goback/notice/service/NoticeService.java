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

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class NoticeService {

    private static final Logger log = LoggerFactory.getLogger(ApprovalService.class);
    private final NoticeRepository noticeRepository;
    private final ModelMapper modelMapper;

    @PersistenceContext
    EntityManager em;

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
        System.out.println("no : " + no);
        // 게시판 detail 이전 글, 다음 게시글 ID를 가져오는 Query
        String sql = "SELECT *" +
                       "FROM (" +
                   "  SELECT NOTICE_NO, " +
                "    LEAD(NOTICE_NO, 1) OVER (ORDER BY WRITE_DATE) AS prev, " +
                "    LAG(NOTICE_NO, 1) OVER (ORDER BY WRITE_DATE) AS next " +
                "  FROM NOTICE " +
                ") sub" +
                " WHERE NOTICE_NO = :value";
        Query nativeQuery = em.createNativeQuery(sql);
        nativeQuery.setParameter("value", no);
        Object[] obj = (Object[]) nativeQuery.getSingleResult();
        System.out.println(obj[1]);
        System.out.println(obj[2]);

        List<Notice> noticeList = noticeRepository.getNoticeDetail(no, (String)obj[1], (String)obj[2]);
        if(noticeList.size() == 2){
            // 이전 글이 없는 경우
            if(noticeList.get(0).getNoticeNo().equals(no)) noticeList.set(0, noticeList.get(1));
            // 다음 글이 없는 경우
            else noticeList.add(noticeList.get(1));
        }
        System.out.println("noticeList : " + noticeList);

        return noticeList.stream().map(notice -> modelMapper.map(notice, NoticeDTO.class)).collect(Collectors.toList());
    }
}
