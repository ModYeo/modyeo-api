package com.co.kr.modyeo.member.repository.custom;

import com.co.kr.modyeo.member.domain.dto.search.CrewSearch;
import com.co.kr.modyeo.member.domain.entity.Crew;

import java.util.List;

public interface CrewCategoryCustomRepository {
    List<Crew> searchCrew(CrewSearch crewSearch);
}
