package com.boseongcho.in4goback.notice.repository;

import com.boseongcho.in4goback.notice.entity.InsertNotice;
import com.boseongcho.in4goback.notice.entity.Notice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface NoticeRepository extends JpaRepository<InsertNotice, String> {

    @Query("SELECT n FROM Notice n ORDER BY n.isPinned DESC, n.writeDate DESC ")
    List<Notice> getNotice();

    @Query("SELECT n FROM Notice n WHERE n.noticeNo IN (:no,:no2, :no3)")
    List<Notice> getNoticeDetail(String no, String no2, String no3);
}
