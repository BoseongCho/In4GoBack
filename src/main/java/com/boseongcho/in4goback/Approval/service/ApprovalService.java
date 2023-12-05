package com.boseongcho.in4goback.Approval.service;


import com.boseongcho.in4goback.Approval.repository.ApprovalRepository;
import com.boseongcho.in4goback.Approval.repository.InsertApprovalRepository;
import com.boseongcho.in4goback.member.repository.MemberRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;


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
}
