package com.boseongcho.in4goback.approval.paging;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Setter
@Getter
public class CriteriaAP {

    private int pageNum; // 현재 페이지
    private int amount; // 페이지 당 게시글

    private String searchValue;

    public CriteriaAP(){
        this(1, 8);
    }

    public CriteriaAP(int pageNum, int amount) {
        this.pageNum = pageNum;
        this.amount = amount;
    }

}
