package com.co.kr.modyeo.api.team.repository.custom;

import com.co.kr.modyeo.api.team.domain.dto.response.TeamResponse;
import com.co.kr.modyeo.api.team.domain.dto.search.MemberTeamSearch;
import com.co.kr.modyeo.api.team.domain.dto.search.TeamSearch;
import com.co.kr.modyeo.api.team.domain.entity.Team;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

import java.util.List;

public interface TeamCustomRepository {
    Slice<Team> searchTeam(TeamSearch teamSearch, Pageable pageable);

    Slice<Team> searchMemberTeam(MemberTeamSearch memberTeamSearch, Pageable pageable);
    List<TeamResponse> findMyTeam(String email);
}
