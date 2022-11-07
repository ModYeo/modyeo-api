package com.co.kr.modyeo.api.team.service;

import com.co.kr.modyeo.api.team.domain.dto.request.CrewUpdateRequest;
import com.co.kr.modyeo.api.team.domain.dto.response.CrewResponse;
import com.co.kr.modyeo.api.team.domain.dto.search.SearchCrew;

import java.util.List;

public interface CrewService {
    List<CrewResponse> getCrew(SearchCrew searchCrew);

    Long updateCrewLevel(CrewUpdateRequest crewUpdateRequest);

    void deleteCrew(Long crewId);

    Long updateCrewActive(Long crewId);

    Long updateCrewInActive(Long teamId);
}
