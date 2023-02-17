package com.co.kr.modyeo.api.team.domain.dto.search;

import com.co.kr.modyeo.common.dto.SearchDto;
import lombok.*;
import org.springframework.data.domain.Sort;

import javax.validation.constraints.Min;

@Getter
@Setter
public class SomeoneTeamSearch extends SearchDto {
    @Min(value=0)
    private Long emdId;
    private Long memberId;
    private Long categoryId;

    @Builder
    public SomeoneTeamSearch(Integer limit, Integer offset, String orderBy, Sort.Direction direction,
                            Long emdId, Long memberId, Long categoryId){
        super(limit, offset, orderBy, direction);
        this.emdId = emdId;
        this.memberId = memberId;
        this.categoryId = categoryId;
    }
}
