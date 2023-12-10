package com.boseongcho.in4goback.approval.entity;


import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity(name = "Referee")
@Table(name ="REFEREE")
@DynamicInsert
@DynamicUpdate
public class Referee {

    @Id
    @Column(name ="DOC_CODE")
    private String docCode;

    @Column(name ="MEM_CODE")
    private String memCode; // 참조인 memCode

}


