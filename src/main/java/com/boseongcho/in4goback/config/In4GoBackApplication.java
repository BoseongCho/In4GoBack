package com.boseongcho.in4goback.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.boseongcho.in4goback")
public class In4GoBackApplication {

	public static void main(String[] args) {
		SpringApplication.run(In4GoBackApplication.class, args);
	}

}

/*
@ComponentScan 이란?
base-package로 설정된 경로 하위에 특정 어노테이션을 가지고 있는 클래스를 이용하여 bean으로 등록.
목적이 따라 @Controller, @Service, @repositry, @Configuration 등을 인식함.

//ComponentScan을 통해 basePackage를 지정해주지 않으면 해당 config파일이 들어있는
패키지를 default로 설정함.
* */
