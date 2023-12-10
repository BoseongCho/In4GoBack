package com.boseongcho.in4goback.approval.dto;


import lombok.*;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ApproverDTO {

    private String docCode; //  문서번호
    private String memCode; // 결재자
    private char isSign; // W/Y/N(대기,승인,반려)
    private Date processedDate; // 처리된 날짜
    private String opinion; // 결재 의견

}
