package com.boseongcho.in4goback.approval.dto;


import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class RefereeDTO {

    private String memCode; //PK
    private String docCode; //FK
}