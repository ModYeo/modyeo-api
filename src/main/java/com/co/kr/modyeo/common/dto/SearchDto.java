package com.co.kr.modyeo.common.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

@Getter
@Setter
@NoArgsConstructor
public class SearchDto {
    private Integer limit;

    private Integer offset;

    private String orderBy;

    private Sort.Direction direction;

    public SearchDto(Integer limit, Integer offset, String orderBy, Sort.Direction direction) {
        this.limit = limit == null ? 15 : limit;
        this.offset = offset == null ? 0 : offset;
        this.orderBy = orderBy == null ? "id" : orderBy;
        this.direction = direction == null ? Sort.Direction.DESC : direction;
    }

    public PageRequest getPageRequest(){
        return PageRequest.of(this.getOffset(), this.getLimit(), this.getDirection(), this.getOrderBy());
    }
}
