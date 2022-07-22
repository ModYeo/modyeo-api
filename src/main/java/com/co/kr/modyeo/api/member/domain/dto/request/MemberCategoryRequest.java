package com.co.kr.modyeo.api.member.domain.dto.request;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class MemberCategoryRequest {

    private Long memberId;

    private List<Long> categoryIdList = new ArrayList<>();

    public MemberCategoryRequest(Long memberId, List<Long> categoryIdList) {
        this.memberId = memberId;
        this.categoryIdList = categoryIdList;
    }
}
