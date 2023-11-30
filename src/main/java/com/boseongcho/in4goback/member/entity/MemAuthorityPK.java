package com.boseongcho.in4goback.member.entity;

import lombok.*;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
public class MemAuthorityPK implements Serializable {
                        // 복합키를 구현할 때에는 반드시 Serializable인터페이스를 상속받아야함
    private String memCode; // 사원번호 PK+FK

    private Authority authNumber; // 시퀀스/경우의 수 14개
}
