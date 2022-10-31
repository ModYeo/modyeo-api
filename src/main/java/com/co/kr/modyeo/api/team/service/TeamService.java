package com.co.kr.modyeo.api.team.service;

import com.co.kr.modyeo.api.team.domain.dto.response.TeamDetail;
import com.co.kr.modyeo.api.team.domain.dto.response.TeamResponse;
import com.co.kr.modyeo.api.team.domain.entity.Team;
import com.co.kr.modyeo.api.team.domain.dto.request.TeamRequest;
import com.co.kr.modyeo.api.team.domain.dto.search.TeamSearch;
import org.springframework.data.domain.Slice;

import java.util.List;

public interface TeamService {
    Long createTeam(TeamRequest teamRequest);

    Slice<TeamResponse> getTeams(TeamSearch teamSearch);

    Long updateTeam(TeamRequest teamRequest);

    void deleteTeam(Long crewId);

    TeamDetail getTeam(Long teamId);

    List<TeamResponse> getMyTeam(String email);
}
