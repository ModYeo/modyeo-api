package com.co.kr.modyeo.api.team.service.impl;

import com.co.kr.modyeo.api.team.domain.dto.response.CrewResponse;
import com.co.kr.modyeo.api.team.domain.entity.link.Crew;
import com.co.kr.modyeo.api.team.repository.CrewRepository;
import com.co.kr.modyeo.api.team.service.CrewService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CrewServiceImpl implements CrewService {

    private final CrewRepository crewRepository;

    @Override
    public List<CrewResponse> getCrew(Long teamId) {
        return crewRepository.searchCrew(teamId)
                .stream()
                .map(CrewResponse::toDto)
                .collect(Collectors.toList());
    }
}
