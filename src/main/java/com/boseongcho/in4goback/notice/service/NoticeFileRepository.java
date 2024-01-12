package com.boseongcho.in4goback.notice.service;

import com.boseongcho.in4goback.approval.entity.DocAttachment;
import com.boseongcho.in4goback.notice.entity.NoticeFile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NoticeFileRepository extends JpaRepository<NoticeFile, Integer> {
}
