package com.co.kr.modyeo.api.team.domain.dto.search;

import com.co.kr.modyeo.api.team.domain.entity.enumerate.CrewLevel;
import com.co.kr.modyeo.common.dto.SearchDto;
import com.co.kr.modyeo.common.enumerate.Yn;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class CrewSearch extends SearchDto {

    private Long teamId;

    private Yn isActivated;

    private CrewLevel level;
}
