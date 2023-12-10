package com.boseongcho.in4goback.approval.service;

import com.boseongcho.in4goback.approval.dto.ApprovalDTO;
import com.boseongcho.in4goback.approval.dto.ApprovalMemDTO;
import com.boseongcho.in4goback.approval.dto.InsertApprovalDTO;
import com.boseongcho.in4goback.approval.entity.*;
import com.boseongcho.in4goback.approval.paging.CriteriaAP;
import com.boseongcho.in4goback.approval.repository.ApprovalRepository;
import com.boseongcho.in4goback.approval.repository.DocAttachmentRepository;
import com.boseongcho.in4goback.approval.repository.InsertApprovalRepository;
import com.boseongcho.in4goback.member.repository.MemberRepository;
import com.boseongcho.in4goback.util.FileUploadUtils;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;


@Service
@AllArgsConstructor
public class ApprovalService {

    private static final Logger log = LoggerFactory.getLogger(ApprovalService.class);
    private final ApprovalRepository approvalRepository;
    private final MemberRepository memberRepository;
    private final ModelMapper modelMapper;
    private final InsertApprovalRepository insertApprovalRepository;
    private final DocAttachmentRepository docAttachmentRepository;

    @PersistenceContext
    EntityManager em;

    public int getApprovalList(String memCode) {
        /* total을 구하기 위한 메소드 */
        /* ApproverList에 값이 많이 담기면 entitygraph 차이를 시험해 볼 것 */
//        List<Approval> result2 = approvalRepository.getApprovalList(memCode);
//
//        List<ApprovalDTO> result3 = result2.stream().map(a -> modelMapper.map(a, ApprovalDTO.class)).collect
//        (Collectors.toList());
//        for(ApprovalDTO a : result3){
//            System.out.println(a);
//        }

        int result = approvalRepository.getApprovalList(memCode).size();

//        return result2;
        return result;
    }

    @Transactional
    public List<ApprovalDTO> getApprovalList(String memCode, CriteriaAP cri) {

        int index = cri.getPageNum() - 1;
        int count = cri.getAmount();
        Pageable paging = PageRequest.of(index, count, Sort.by("docCode").descending());
        Page<Approval> result = approvalRepository.getApprovalList(memCode, paging);
        List<Approval> approvalList = result.getContent();

        return approvalList.stream().map(approval -> modelMapper.map(approval, ApprovalDTO.class)).collect(Collectors.toList());
    }

    public Object getSearchInfoAPI(String nameOrPosition, String inputValue) {
        log.info("[ApprovalService] getSearchInfoAPI Start =============== ");

        List<ApprovalMem> foundMembers = null;
        if (nameOrPosition.equals("이름")) {
            foundMembers = memberRepository.getSearchInfoByName(inputValue);
        } else if (nameOrPosition.equals("직급")) {
            foundMembers = memberRepository.getSearchInfoByPosition(inputValue);
            System.out.println("foundmembers : " + foundMembers);
        }


        return foundMembers.stream().map(foundMember -> modelMapper.map(foundMember, ApprovalMemDTO.class)).collect(Collectors.toList());
    }

    @Transactional
    public Object insertApproval(InsertApprovalDTO insertApprovalDTO) {

        System.out.println("Service ====== insertApprovalDTO = " + insertApprovalDTO);

        int result = 0;
        String docCode = "";

        try {

            InsertApproval insertApproval = modelMapper.map(insertApprovalDTO, InsertApproval.class);
            List<Approver> approverList = insertApproval.getApproverList();
            List<Referee> refereeList = insertApproval.getRefereeList();
            insertApproval.setApproverList(null);
            insertApproval.setRefereeList(null);

            insertApprovalRepository.save(insertApproval);

            if (approverList != null) {
                for (int i = 0; i < approverList.size(); i++) {
                    Approver approver = approverList.get(i);
                    String sql = "INSERT INTO APPROVER (DOC_CODE, MEM_CODE) VALUES (:value1, :value2)";
                    Query nativeQuery = em.createNativeQuery(sql);
                    nativeQuery.setParameter("value1", insertApproval.getDocCode());
                    nativeQuery.setParameter("value2", approver.getMemCode());
                    nativeQuery.executeUpdate();
                }
            }

            if (refereeList != null) {
                for (int i = 0; i < refereeList.size(); i++) {
                    Referee referee = refereeList.get(i);
                    String sql = "INSERT INTO REFEREE (DOC_CODE, MEM_CODE) VALUES (:value1, :value2)";
                    Query nativeQuery = em.createNativeQuery(sql);
                    nativeQuery.setParameter("value1", insertApproval.getDocCode());
                    nativeQuery.setParameter("value2", referee.getMemCode());
                    nativeQuery.executeUpdate();
                }
            }
            result = 1;
            docCode = insertApproval.getDocCode();
        } catch (Exception e) {
            log.info("[Approval insert] Failed!!");
        }
        log.info("[ApprovalService] insertApproval End ==============================");
        return (result > 0) ? docCode : "실패";
    }

    @Transactional
    public Object insertApprovalDoc(List<MultipartFile> docAttachments, String docCode) {

        System.out.println("Service ====== insertApprovalDoc = " + docAttachments);
        List<DocAttachment> docAttachmentList = new ArrayList<>();
        String DOC_URL = "src/main/resources/static/attachments";
        String savedName = null; // 실제 저장될 이름(+. 확장자 포함)
        int result = 0;


        try {
            for (MultipartFile file : docAttachments) {

                String fileName = UUID.randomUUID().toString().replace("-", "");
                savedName = FileUploadUtils.saveFile(DOC_URL, fileName, file);
                savedName = "/attachments/" + savedName;
                System.out.println("savedName = " + savedName);

                DocAttachment docAttachment = new DocAttachment();
                docAttachment.setUrl(savedName); // 저장경로 + 저장이름(+.확장자)
                docAttachment.setDocCode(docCode);
                docAttachment.setFileName(file.getOriginalFilename()); // 찐이름

                docAttachmentList.add(docAttachment);
            }
            System.out.println("docAttachmentList = " + docAttachmentList);
            docAttachmentRepository.saveAll(docAttachmentList);

            result = 1;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return (result > 0) ? "등록성공" : "등록 실패";
    }
}
