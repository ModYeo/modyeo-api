package com.co.kr.modyeo.api.team.domain.dto.search;

import com.co.kr.modyeo.api.team.domain.entity.enumerate.CrewLevel;
import com.co.kr.modyeo.common.dto.SearchDto;
import com.co.kr.modyeo.common.enumerate.Yn;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.domain.Sort;

@Setter
@Getter
public class CrewSearch extends SearchDto {

    private Long teamId;

    private Yn isActivated;

    private CrewLevel level;

    public CrewSearch(Integer limit, Integer offset, String orderBy, Sort.Direction direction) {
        super(limit, offset, orderBy, direction);
    }
}
