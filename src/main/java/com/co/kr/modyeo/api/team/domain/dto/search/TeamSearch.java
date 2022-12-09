package com.co.kr.modyeo.api.team.domain.dto.search;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Sort;

@Data
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class TeamSearch {

    private String name;

    private Long categoryId;

    private Long memberId;

    private Integer limit;

    private Integer offset;

    private String orderBy;

    private Sort.Direction direction;

    @Builder
    public TeamSearch(String name, Long categoryId,Long memberId, Integer limit, Integer offset, String orderBy, Sort.Direction direction) {
        this.name = name;
        this.categoryId = categoryId;
        this.memberId = memberId;
        this.limit = limit != null? limit : 20;
        this.offset = offset != null? offset : 0;
        this.orderBy = orderBy != null? orderBy : "id";
        this.direction = direction != null? direction : Sort.Direction.DESC;
    }
}
