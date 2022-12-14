package com.co.kr.modyeo.api.team.domain.dto.search;

import com.co.kr.modyeo.common.dto.SearchDto;
import lombok.*;
import org.springframework.data.domain.Sort;

@Getter
@Setter
@NoArgsConstructor
public class TeamSearch extends SearchDto {

    private String name;

    private Long categoryId;

    private Long memberId;

    @Builder
    public TeamSearch(String name, Long categoryId,Long memberId) {
        this.name = name;
        this.categoryId = categoryId;
        this.memberId = memberId;
    }
}
