package com.boseongcho.in4goback.member.controller;

import com.boseongcho.in4goback.common.ResponseDTO;
import com.boseongcho.in4goback.member.dto.MemberDTO;
import com.boseongcho.in4goback.member.service.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    @Autowired
    public AuthController(AuthService authService) {this.authService = authService;}

    /* @RequestBody를 통해 RequestBody로 넘어온 Json 문자열을 파싱해서 MemberDTO 속성으로 매핑해 객체로 받아낸다.(회원 아이디, 비밀번호) */
    @Operation(summary = "로그인 요청", description = "로그인 및 인증이 진행됩니다.", tags = {"AuthController"})
    @PostMapping("/login")
    public ResponseEntity<ResponseDTO> login(@RequestBody MemberDTO memberDTO){
        return ResponseEntity
                .ok()
                .body(new ResponseDTO(HttpStatus.OK, "로그인 성공", authService.login(memberDTO)));
        /*
        ResponseEntity의 body메소드를 통해 Response객체의 body에 담기는 ResponseDTO는 JSON문자열이 되고
        화면단이 React인 곳으로 가면 Store에 해당 리듀서가 관리하는 state 값이 된다.
        * */
    }
}
