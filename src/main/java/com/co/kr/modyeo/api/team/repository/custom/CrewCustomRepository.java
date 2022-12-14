package com.co.kr.modyeo.api.team.repository.custom;

import com.co.kr.modyeo.api.team.domain.dto.search.CrewSearch;
import com.co.kr.modyeo.api.team.domain.entity.enumerate.CrewLevel;
import com.co.kr.modyeo.api.team.domain.entity.link.Crew;

import java.util.List;

public interface CrewCustomRepository {
    List<Crew> searchCrew(CrewSearch crewSearch);

    CrewLevel findCrewLevelByTeamIdAndEmail(Long teamId, String email);

    Crew findCrewByTeamIdAndEmail(String email, Long teamId);
}
