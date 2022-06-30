package com.co.kr.modyeo.member.repository.custom;

import com.co.kr.modyeo.member.domain.dto.search.CrewSearch;
import com.co.kr.modyeo.member.domain.entity.Crew;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

import java.util.List;

public interface CrewCustomRepository {
    Slice<Crew> searchCrew(CrewSearch crewSearch, Pageable pageable);
}
