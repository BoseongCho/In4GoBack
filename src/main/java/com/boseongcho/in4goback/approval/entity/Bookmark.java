package com.boseongcho.in4goback.approval.entity;


import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name ="BOOKMARK")
public class Bookmark {

    @Id
    @Column(name = "DOC_CODE")
    private String docCode; //  문서번호

    @Column(name ="MEM_CODE")
    private String memCode; // 북마크 한 사람
}
