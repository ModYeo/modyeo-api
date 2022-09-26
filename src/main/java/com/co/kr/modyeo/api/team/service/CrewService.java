package com.co.kr.modyeo.api.team.service;

import com.co.kr.modyeo.api.team.domain.dto.request.CrewUpdateRequest;
import com.co.kr.modyeo.api.team.domain.dto.response.CrewResponse;

import java.util.List;

public interface CrewService {
    List<CrewResponse> getCrew(Long teamId);

    void updateCrewLevel(CrewUpdateRequest crewUpdateRequest);

    void deleteCrew(Long crewId);
}
