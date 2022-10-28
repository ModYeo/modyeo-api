package com.co.kr.modyeo.api.team.domain.dto.search;

import com.co.kr.modyeo.api.team.domain.entity.enumerate.CrewLevel;
import com.co.kr.modyeo.common.enumerate.Yn;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class SearchCrew {

    private Long teamId;

    private Yn isActivated;

    private CrewLevel level;
}
