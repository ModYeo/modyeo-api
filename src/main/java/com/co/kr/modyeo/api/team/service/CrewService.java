package com.co.kr.modyeo.api.team.service;

import com.co.kr.modyeo.api.team.domain.dto.response.CrewResponse;

import java.util.List;

public interface CrewService {
    List<CrewResponse> getCrew(Long teamId);
}
