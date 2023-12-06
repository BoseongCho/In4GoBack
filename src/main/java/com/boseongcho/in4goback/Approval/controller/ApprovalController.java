package com.boseongcho.in4goback.Approval.controller;


import com.boseongcho.in4goback.Approval.dto.ApprovalDTO;
import com.boseongcho.in4goback.Approval.paging.CriteriaAP;
import com.boseongcho.in4goback.Approval.paging.PageDTOAP;
import com.boseongcho.in4goback.Approval.paging.PagingResponseDTOAP;
import com.boseongcho.in4goback.Approval.service.ApprovalService;
import com.boseongcho.in4goback.common.ResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
@AllArgsConstructor
public class ApprovalController {

    private final ApprovalService approvalService;

    @Operation(summary = "결재 상신함 문서 목록", description = "상신 문서리스트", tags = {"ApprovalController"})
    @GetMapping("/approval")
    public ResponseEntity<ResponseDTO> getApprovalList(@RequestParam String memCode,
                                                       @RequestParam(name = "offset", defaultValue = "1") String offset) {
        int total = approvalService.getApprovalList(memCode);

        CriteriaAP cri = new CriteriaAP(Integer.valueOf(offset), 8);
        PagingResponseDTOAP pagingResponseDTOAP = new PagingResponseDTOAP();
        List<ApprovalDTO> approvalDTOList = approvalService.getApprovalList(memCode, cri);
        pagingResponseDTOAP.setData(approvalDTOList);
        pagingResponseDTOAP.setPageInfo(new PageDTOAP(total, cri));

        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "조회 성공", pagingResponseDTOAP));
    }
}
