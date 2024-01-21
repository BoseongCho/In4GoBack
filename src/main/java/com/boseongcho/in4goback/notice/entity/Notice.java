package com.boseongcho.in4goback.notice.entity;


import lombok.*;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.List;

@Entity
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@DynamicUpdate
public class Notice {
    /* ReadCount를 update하려면 영속성에서 관리되는 엔티티를 set을 통해 update해야하는데,
    DynamicUpdate를 설정하지 않으면 모든 필드변수가 함께 업데이트가 되기 때문에
    toString이 되지 않은 writeDate를 읽어오지 못해서
    리터럴이 형식 문자열과 일치하지 않음 에러가 발생하니 주의.
    * */

    @Id
    @Column(name = "NOTICE_NO")
    private String noticeNo;

    @OneToOne
    @JoinColumn(name = "MEM_CODE")
    private NoticeMem noticeMem;

    @Column(name = "TITLE")
    private String title; //제목

    @Column(name = "CONTENT")
    private String content; //내용

    @Column(name ="WRITE_DATE")
    private String writeDate; //작성일
    @Column(name = "READ_COUNT", columnDefinition = "NUMBER")
    private int readCount; // 조회수

    @Column(name = "IS_PINNED")
    private int isPinned; // 상단고정 0,1

    @OneToMany
    @JoinColumn(name = "NOTICE_NO")
    private List<NoticeFile> noticeFileList; // 공지_첨부파일

}
