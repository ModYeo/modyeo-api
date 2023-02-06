package com.co.kr.modyeo.api.team.service;

import com.co.kr.modyeo.api.team.domain.dto.request.TeamUpdateRequest;
import com.co.kr.modyeo.api.team.domain.dto.response.TeamDetail;
import com.co.kr.modyeo.api.team.domain.dto.response.TeamResponse;
import com.co.kr.modyeo.api.team.domain.dto.request.TeamCreateRequest;
import com.co.kr.modyeo.api.team.domain.dto.search.TeamSearch;
import org.springframework.data.domain.Slice;

public interface TeamService {
    Long createTeam(TeamCreateRequest teamCreateRequest);

    Slice<TeamResponse> getTeams(TeamSearch teamSearch);

    Long updateTeam(TeamUpdateRequest teamUpdateRequest);

    void deleteTeam(Long crewId);

    TeamDetail getTeam(Long teamId);
}
