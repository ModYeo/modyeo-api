package com.co.kr.modyeo.api.team.repository.custom;

import com.co.kr.modyeo.api.team.domain.dto.search.TeamSearch;
import com.co.kr.modyeo.api.team.domain.entity.Team;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

public interface TeamCustomRepository {
    Slice<Team> searchTeam(TeamSearch teamSearch, Pageable pageable);
}
