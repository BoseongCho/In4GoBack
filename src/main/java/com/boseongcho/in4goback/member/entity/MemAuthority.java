package com.boseongcho.in4goback.member.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "MEM_AUTHORITY")
@IdClass(MemAuthorityPK.class)
public class MemAuthority {

    /* 다 대 다를 해소하기 위한 매핑 table.
    ManyToMany를 해소하기 위해 ManyToOne, oneToMany로 바꿔줬다.
    *  */

    @Id
    @Column(name = "MEM_CODE")
    private String memCode; // 사원번호 PK+FK

    @Id
    @ManyToOne
    @JoinColumn(name = "AUTH_NUMBER")
    private Authority authNumber; // 시퀀스/경우의 수 14개

}
