package com.co.kr.modyeo.api.team.service;

import com.co.kr.modyeo.api.team.domain.dto.response.TeamResponse;
import com.co.kr.modyeo.api.team.domain.entity.Team;
import com.co.kr.modyeo.api.team.domain.dto.request.TeamRequest;
import com.co.kr.modyeo.api.team.domain.dto.search.TeamSearch;
import org.springframework.data.domain.Slice;

public interface TeamService {
    Team createTeam(TeamRequest teamRequest);

    Slice<TeamResponse> getTeam(TeamSearch teamSearch);

    Team updateTeam(TeamRequest teamRequest);

    void deleteTeam(Long crewId);
}
