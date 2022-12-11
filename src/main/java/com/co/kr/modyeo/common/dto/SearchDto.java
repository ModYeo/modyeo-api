package com.co.kr.modyeo.common.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.Sort;

@Getter
@Setter
public class SearchDto {
    private Integer limit;

    private Integer offset;

    private String orderBy;

    private Sort.Direction direction;
}
