package com.boseongcho.in4goback.approval.entity;


import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="APPROVER")
public class Approver {

    @Id
    @Column(name = "DOC_CODE")
    private String docCode;

    @Column(name = "MEM_CODE")
    private String memCode; // 결재자 memCode

    @Column(name = "IS_SIGN")
    private char isSign; // W/Y/N(대기,승인,반려)

    @Column(name = "PROCESSED_DATE")
    private Date processedDate; // 처리된 날짜

    @Column(name = "OPINION")
    private String opinion; // 결재 의견
}
