package com.boseongcho.in4goback.approval.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class BookmarkDTO {

    private String docCode; //  문서번호
    private String memCode; // 북마크 한 사람
}
