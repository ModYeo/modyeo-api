package com.co.kr.modyeo.member.domain.dto.search;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Sort;

@Data
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class TeamSearch {

    private Long crewId;

    private String name;

    private Long categoryId;

    private Integer limit;

    private Integer offset;

    private String orderBy;

    private Sort.Direction direction;

    @Builder
    public TeamSearch(Long crewId, String name, Long categoryId, Integer limit, Integer offset, String orderBy, Sort.Direction direction) {
        this.crewId = crewId;
        this.name = name;
        this.categoryId = categoryId;
        this.limit = limit != null? limit : 20;
        this.offset = offset != null? offset : 0;
        this.orderBy = orderBy != null? orderBy : "id";
        this.direction = direction != null? direction : Sort.Direction.DESC;
    }
}
