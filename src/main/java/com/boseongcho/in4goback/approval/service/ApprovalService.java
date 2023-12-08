package com.boseongcho.in4goback.approval.service;


import com.boseongcho.in4goback.approval.dto.ApprovalDTO;
import com.boseongcho.in4goback.approval.dto.ApprovalMemDTO;
import com.boseongcho.in4goback.approval.entity.Approval;
import com.boseongcho.in4goback.approval.entity.ApprovalMem;
import com.boseongcho.in4goback.approval.paging.CriteriaAP;
import com.boseongcho.in4goback.approval.repository.ApprovalRepository;
import com.boseongcho.in4goback.approval.repository.InsertApprovalRepository;
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

    public Object getSearchInfoAPI(String nameOrPosition, String inputValue) {
        log.info("[ApprovalService] getSearchInfoAPI Start =============== ");

        List<ApprovalMem> foundMembers = null;
        if(nameOrPosition.equals("이름")){
            foundMembers = memberRepository.getSearchInfoByName(inputValue);
        }
//        else if (nameOrPosition.equals("직급")) {
//            foundMembers = memberRepository.getSearchInfoByPosition(inputValue);
//        }


        return foundMembers.stream().map(foundMember -> modelMapper.map(foundMember, ApprovalMemDTO.class)).collect(Collectors.toList());
    }
}
