package com.co.kr.modyeo.api.team.repository.custom;

import com.co.kr.modyeo.api.team.domain.entity.link.Crew;

import java.util.List;

public interface CrewCustomRepository {
    List<Crew> searchCrew(Long teamId);
}
