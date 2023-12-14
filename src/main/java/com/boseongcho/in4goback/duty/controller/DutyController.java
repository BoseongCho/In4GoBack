package com.boseongcho.in4goback.duty.controller;

import com.boseongcho.in4goback.duty.service.DutyService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
@AllArgsConstructor
public class DutyController {

    private final DutyService dutyService;



}
