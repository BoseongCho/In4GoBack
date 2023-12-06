package com.boseongcho.in4goback.Approval.service;


import com.boseongcho.in4goback.Approval.dto.ApprovalDTO;
import com.boseongcho.in4goback.Approval.entity.Approval;
import com.boseongcho.in4goback.Approval.paging.CriteriaAP;
import com.boseongcho.in4goback.Approval.repository.ApprovalRepository;
import com.boseongcho.in4goback.Approval.repository.InsertApprovalRepository;
import com.boseongcho.in4goback.member.repository.MemberRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;


@Service
@AllArgsConstructor
public class ApprovalService {

    private static final Logger log = LoggerFactory.getLogger(ApprovalService.class);
    private final ApprovalRepository approvalRepository;
    private final MemberRepository memberRepository;
    private final ModelMapper modelMapper;
    private final InsertApprovalRepository insertApprovalRepository;

    @PersistenceContext
    EntityManager em;

    public int getApprovalList(String memCode){
        /* total을 구하기 위한 메소드 */

        int result = approvalRepository.getApprovalList(memCode).size();

        return result;
    }

    @Transactional
    public List<ApprovalDTO> getApprovalList(String memCode, CriteriaAP cri){

        int index = cri.getPageNum() - 1;
        int count = cri.getAmount();
        Pageable paging = PageRequest.of(index, count, Sort.by("docCode").descending());
        Page<Approval> result = approvalRepository.getApprovalList(memCode, paging);
        List<Approval> approvalList = (List<Approval>) result.getContent();

        return approvalList.stream().map(approval -> modelMapper.map(approval, ApprovalDTO.class)).collect(Collectors.toList());
    }
}
