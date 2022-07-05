package com.co.kr.modyeo.team.service;

import com.co.kr.modyeo.team.domain.dto.request.TeamRequest;
import com.co.kr.modyeo.team.domain.dto.response.TeamResponse;
import com.co.kr.modyeo.team.domain.dto.search.TeamSearch;
import com.co.kr.modyeo.team.domain.entity.Team;
import org.springframework.data.domain.Slice;

public interface TeamService {
    Team createTeam(TeamRequest teamRequest);

    Slice<TeamResponse> getTeam(TeamSearch teamSearch);

    Team updateTeam(TeamRequest teamRequest);

    void deleteTeam(Long crewId);
}
