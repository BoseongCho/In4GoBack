package com.boseongcho.in4goback.member.controller;

import com.boseongcho.in4goback.common.ResponseDTO;
import com.boseongcho.in4goback.member.service.MemberService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1")
@AllArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @Operation(summary = "회원 조회 요청", description = "회원 한명이 조회됩니다.", tags={ "MemberController" })
    @GetMapping("/member/{memCode}")
    public ResponseEntity<ResponseDTO> selectMemberInfo(@PathVariable String memCode){
        return ResponseEntity.ok().body
                (new ResponseDTO(HttpStatus.OK, "조회 성공", memberService.selectMemberInfo(memCode)));
    }
}
