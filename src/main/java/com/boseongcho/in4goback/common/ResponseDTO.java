package com.boseongcho.in4goback.common;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.http.HttpStatus;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class ResponseDTO { // 응답 body에 담을 객체(json문자열이 될 객체)

    private int status; // 생태 코드 값
    private String message; // 응답 메세지
    private Object data; // 응답 데이터

    public ResponseDTO(HttpStatus status, String message, Object data){
        this.status = status.value(); // HttpStatus enum 타입에서 value라는 int형 상태코드 값만 추출함
        this.message = message;
        this.data = data;
    }

}
