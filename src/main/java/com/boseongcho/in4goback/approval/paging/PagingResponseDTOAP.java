package com.boseongcho.in4goback.approval.paging;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class PagingResponseDTOAP {

    private Object data;
    private PageDTOAP pageInfo;
}
