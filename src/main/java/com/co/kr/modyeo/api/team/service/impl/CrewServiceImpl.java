package com.co.kr.modyeo.api.team.service.impl;

import com.co.kr.modyeo.api.team.domain.dto.request.CrewUpdateRequest;
import com.co.kr.modyeo.api.team.domain.dto.response.CrewResponse;
import com.co.kr.modyeo.api.team.domain.dto.search.CrewSearch;
import com.co.kr.modyeo.api.team.domain.entity.enumerate.CrewLevel;
import com.co.kr.modyeo.api.team.domain.entity.link.Crew;
import com.co.kr.modyeo.api.team.domain.spec.CrewSpecification;
import com.co.kr.modyeo.api.team.repository.CrewRepository;
import com.co.kr.modyeo.api.team.service.CrewService;
import com.co.kr.modyeo.common.enumerate.Yn;
import com.co.kr.modyeo.common.exception.ApiException;
import com.co.kr.modyeo.common.util.SecurityUtil;
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

    private final CrewSpecification crewSpecification;

    @Override
    public List<CrewResponse> getCrew(CrewSearch crewSearch) {
        if (Yn.N.equals(crewSearch.getIsActivated())){
            CrewLevel crewLevel = getCrewLevel(crewSearch.getTeamId());
            crewSpecification.check(crewLevel);
        }

        return crewRepository.searchCrew(crewSearch)
                .stream()
                .map(CrewResponse::toDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public Long updateCrewLevel(CrewUpdateRequest crewUpdateRequest) {
        Crew crew = crewRepository.findById(crewUpdateRequest.getCrewId()).orElseThrow(
                () -> ApiException.builder()
                        .status(HttpStatus.BAD_REQUEST)
                        .errorCode("NOT_FOUND_CREW_MEMBER")
                        .errorMessage("찾을 수 없는 크루원입니다.")
                        .build());

        CrewLevel crewLevel = getCrewLevel(crew.getTeam().getId());
        crewSpecification.check(crewLevel);

        crew.changeLevel(crewUpdateRequest.getCrewLevel());
        return crew.getId();
    }

    @Override
    @Transactional
    public void deleteCrew(Long crewId) {
        Crew crew = crewRepository.findById(crewId).orElseThrow(
                () -> ApiException.builder()
                        .status(HttpStatus.BAD_REQUEST)
                        .errorCode("NOT_FOUND_CREW_MEMBER")
                        .errorMessage("찾을 수 없는 크루원입니다.")
                        .build());

        CrewLevel crewLevel = getCrewLevel(crew.getTeam().getId());
        crewSpecification.check(crewLevel);

        Crew.exit(crew);
    }

    @Override
    @Transactional
    public Long updateCrewActive(Long crewId) {
        Crew crew = crewRepository.findById(crewId).orElseThrow(
                () -> ApiException.builder()
                        .status(HttpStatus.BAD_REQUEST)
                        .errorCode("NOT_FOUND_CREW_MEMBER")
                        .errorMessage("찾을 수 없는 크루원입니다.")
                        .build());

        CrewLevel crewLevel = getCrewLevel(crew.getTeam().getId());
        crewSpecification.check(crewLevel);

        Crew.activeCrew(crew);
        return crew.getId();
    }

    @Override
    @Transactional
    public Long updateCrewInActive(Long teamId) {
        Long memberId = SecurityUtil.getCurrentMemberId();
        Crew crew = crewRepository.findCrewByTeamIdAndMemberId(memberId, teamId);
        Crew.leave(crew);
        return crew.getId();
    }

    private CrewLevel getCrewLevel(Long teamId) {
        Long memberId = SecurityUtil.getCurrentMemberId();
        return crewRepository.findCrewLevelByTeamIdAndMemberId(teamId,memberId);
    }
}
