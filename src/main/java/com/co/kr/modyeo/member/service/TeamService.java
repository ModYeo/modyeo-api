package com.co.kr.modyeo.member.service;

import com.co.kr.modyeo.member.domain.dto.request.TeamRequest;
import com.co.kr.modyeo.member.domain.dto.response.TeamResponse;
import com.co.kr.modyeo.member.domain.dto.search.TeamSearch;
import com.co.kr.modyeo.member.domain.entity.Team;
import org.springframework.data.domain.Slice;

public interface TeamService {
    Team createTeam(TeamRequest teamRequest);

    Slice<TeamResponse> getTeam(TeamSearch teamSearch);

    Team updateTeam(TeamRequest teamRequest);

    void deleteTeam(Long crewId);
}
