package com.boseongcho.in4goback.approval.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Table(name = "APPROVAL")
public class ApprovalForTotal {

    @Id
    @Column(name = "DOC_CODE")
    private String docCode;

    @Column(name = "MEM_CODE")
    private String memCode;

}
