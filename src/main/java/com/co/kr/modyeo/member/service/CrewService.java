package com.co.kr.modyeo.member.service;

import com.co.kr.modyeo.member.domain.dto.request.CrewRequest;
import com.co.kr.modyeo.member.domain.dto.response.CrewResponse;
import com.co.kr.modyeo.member.domain.dto.search.CrewSearch;
import com.co.kr.modyeo.member.domain.entity.Crew;

import java.util.List;

public interface CrewService {
    Crew createCrew(CrewRequest crewRequest);

    List<CrewResponse> getCrew(CrewSearch crewSearch);
}
