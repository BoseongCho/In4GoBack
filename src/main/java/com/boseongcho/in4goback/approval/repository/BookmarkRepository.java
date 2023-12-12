package com.boseongcho.in4goback.approval.repository;

import com.boseongcho.in4goback.approval.entity.Bookmark;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookmarkRepository extends JpaRepository<Bookmark, String> {
    
}
