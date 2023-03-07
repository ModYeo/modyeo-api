package com.co.kr.modyeo.api.team.service;

import com.co.kr.modyeo.api.team.domain.dto.request.TeamUpdateRequest;
import com.co.kr.modyeo.api.team.domain.dto.response.TeamDetail;
import com.co.kr.modyeo.api.team.domain.dto.response.TeamResponse;
import com.co.kr.modyeo.api.team.domain.dto.request.TeamCreateRequest;
import com.co.kr.modyeo.api.team.domain.dto.search.SomeoneTeamSearch;
import com.co.kr.modyeo.api.team.domain.dto.search.TeamSearch;
import org.springframework.data.domain.Slice;

import java.util.List;

public interface TeamService {
    Long createTeam(TeamCreateRequest teamCreateRequest);

    Slice<TeamResponse> getTeams(TeamSearch teamSearch);
    Slice<TeamResponse> getSomeoneTeam(SomeoneTeamSearch memberTeamSearch);
    Long updateTeam(TeamUpdateRequest teamUpdateRequest);

    void deleteTeam(Long crewId);

    TeamDetail getTeam(Long teamId);

    List<TeamResponse> getRecommendTeams(Long emdId, List<Long> categoryIdList);
}
