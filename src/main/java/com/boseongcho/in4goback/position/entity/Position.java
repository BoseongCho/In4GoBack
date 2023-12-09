package com.boseongcho.in4goback.position.entity;

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
@Table(name = "POSITION")
public class Position {

    @Id
    @Column(name = "POSITION_CODE")
    private String positionCode; // 직급 코드 PK
    @Column(name = "POSITION_NAME")
    private String positionName; // 직급명
}
