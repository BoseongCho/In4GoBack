package com.boseongcho.in4goback.Duty.controller;

import com.boseongcho.in4goback.Duty.service.DutyService;
import com.boseongcho.in4goback.approval.service.ApprovalService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
@AllArgsConstructor
public class DutyController {

    private final DutyService dutyService;





}
