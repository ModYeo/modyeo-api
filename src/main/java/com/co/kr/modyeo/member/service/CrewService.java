package com.co.kr.modyeo.member.service;

import com.co.kr.modyeo.member.domain.dto.request.CrewRequest;
import com.co.kr.modyeo.member.domain.entity.Crew;

public interface CrewService {
    Crew crewCreate(CrewRequest crewRequest);
}
