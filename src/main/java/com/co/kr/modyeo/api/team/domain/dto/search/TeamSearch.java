package com.co.kr.modyeo.api.team.domain.dto.search;

import com.co.kr.modyeo.common.dto.SearchDto;
import lombok.*;
import org.springframework.data.domain.Sort;

@Getter
@Setter
public class TeamSearch extends SearchDto {

    private String name;

    private Long categoryId;

    private Long emdId;

    @Builder
    public TeamSearch(String name, Long categoryId, Long emdId,
                      Integer limit, Integer offset, String orderBy, Sort.Direction direction) {
        super(limit, offset, orderBy, direction);
        this.name = name;
        this.categoryId = categoryId;
        this.emdId = emdId;
    }
}
