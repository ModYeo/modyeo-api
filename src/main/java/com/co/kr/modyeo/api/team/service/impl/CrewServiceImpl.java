package com.co.kr.modyeo.api.team.service.impl;

import com.co.kr.modyeo.api.team.domain.dto.request.CrewUpdateRequest;
import com.co.kr.modyeo.api.team.domain.dto.response.CrewResponse;
import com.co.kr.modyeo.api.team.domain.entity.link.Crew;
import com.co.kr.modyeo.api.team.repository.CrewRepository;
import com.co.kr.modyeo.api.team.service.CrewService;
import com.co.kr.modyeo.common.exception.ApiException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
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

    @Override
    @Transactional
    public void updateCrewLevel(CrewUpdateRequest crewUpdateRequest) {
        Crew crew = crewRepository.findById(crewUpdateRequest.getCrewId()).orElseThrow(
                () -> ApiException.builder()
                        .status(HttpStatus.BAD_REQUEST)
                        .errorCode("NOT_FOUND_CREW_MEMBER")
                        .errorMessage("찾을 수 없는 크루원입니다.")
                        .build());

        crew.changeLevel(crewUpdateRequest.getCrewLevel());
    }
}
