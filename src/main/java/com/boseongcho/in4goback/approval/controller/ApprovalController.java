package com.boseongcho.in4goback.approval.controller;


import com.boseongcho.in4goback.approval.dto.ApprovalDTO;
import com.boseongcho.in4goback.approval.dto.InsertApprovalDTO;
import com.boseongcho.in4goback.approval.paging.CriteriaAP;
import com.boseongcho.in4goback.approval.paging.PageDTOAP;
import com.boseongcho.in4goback.approval.paging.PagingResponseDTOAP;
import com.boseongcho.in4goback.approval.service.ApprovalService;
import com.boseongcho.in4goback.common.ResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @Operation(summary = "결재대상 정보 조회", description = "업무 상신 결재대상 정보 조회", tags = { "ApprovalController" })
    @GetMapping("/approval/searchInfo")
    public ResponseEntity<ResponseDTO> getSearchInfoAPI(@RequestParam String nameOrPosition, @RequestParam String inputValue){
        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "조회 성공", approvalService.getSearchInfoAPI(nameOrPosition, inputValue)));
    }

    @Operation(summary = "결재 문서 상신", description = "결재 문서 상신", tags = { "ApprovalController" })
    @PostMapping(value = "/approval/insert")
    public ResponseEntity<Object> insertApproval(@RequestBody InsertApprovalDTO insertApprovalDTO){
        System.out.println("insertApprovalDTO = " + insertApprovalDTO);

        return ResponseEntity.ok().body(new ResponseDTO(HttpStatus.OK, "결제문서 상신 완료", approvalService.insertApproval(insertApprovalDTO)));
    }
}
