package com.boseongcho.in4goback.Approval.paging;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class PageDTOAP {

    private int pageStart;			// 페이지 시작 번호
    private int pageEnd;			// 페이지 끝 번호
    private boolean next, prev;		// 이전, 다음 버튼 존재 유무
    private int total;			    // 행 전체 개수

    /* 현재페이지 번호(pageNum), 행 표시 수(amount), 검색 키워드(keyword), 검색 종류(type) 등*/
    private CriteriaAP cri;			// 검색 정보

    public PageDTOAP(int total, CriteriaAP cri) {
        this.total = total;
        this.cri = cri;
        System.out.println("cri = " + cri);

        this.pageEnd = (int) (Math.ceil(cri.getPageNum() / 5.0)) * 5;

        System.out.println("pageEnd = " + pageEnd);
        this.pageStart = this.pageEnd - 4;
        System.out.println("pageStart = " + pageStart);

        /* 전체 마지막 페이지 번호(행개수 / 페이지 당 개시글 수 ) */
        int realEnd = (int) (Math.ceil(total * 1.0 / cri.getAmount()));

        /* 페이지 끝 번호 유효성 체크 */
        if( realEnd < pageEnd) {
            this.pageEnd = realEnd;
        }

        /* 이전 버튼 값 초기화 */
        this.prev = this.pageStart > 1;
        // 자바 연산자 순서 비교먼저(<, >) 가장 마지막 대입 (=)
        this.next = this.pageEnd < realEnd;
    }
}
