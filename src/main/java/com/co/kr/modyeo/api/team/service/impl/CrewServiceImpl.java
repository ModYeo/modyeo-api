package com.co.kr.modyeo.api.team.service.impl;

import com.co.kr.modyeo.api.team.domain.dto.request.CrewUpdateRequest;
import com.co.kr.modyeo.api.team.domain.dto.response.CrewResponse;
import com.co.kr.modyeo.api.team.domain.dto.search.SearchCrew;
import com.co.kr.modyeo.api.team.domain.entity.enumerate.CrewLevel;
import com.co.kr.modyeo.api.team.domain.entity.link.Crew;
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

    @Override
    public List<CrewResponse> getCrew(SearchCrew searchCrew) {
        if (Yn.N.equals(searchCrew.getIsActivated())){
            CrewLevel crewLevel = getCrewLevel(searchCrew.getTeamId());
            if (Crew.checkAuth(crewLevel)){
                throw ApiException.builder()
                        .status(HttpStatus.BAD_REQUEST)
                        .errorCode("NOT_AUTHORIZED")
                        .errorMessage("권한이 없습니다.")
                        .build();
            }
        }

        return crewRepository.searchCrew(searchCrew)
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

        CrewLevel crewLevel = getCrewLevel(crew.getTeam().getId());
        if (Crew.checkAuth(crewLevel)){
            throw ApiException.builder()
                    .status(HttpStatus.BAD_REQUEST)
                    .errorCode("NOT_AUTHORIZED")
                    .errorMessage("권한이 없습니다.")
                    .build();
        }

        crew.changeLevel(crewUpdateRequest.getCrewLevel());
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
        if (Crew.checkAuth(crewLevel)){
            throw ApiException.builder()
                    .status(HttpStatus.BAD_REQUEST)
                    .errorCode("NOT_AUTHORIZED")
                    .errorMessage("권한이 없습니다.")
                    .build();
        }

        Crew.exit(crew);
    }

    @Override
    @Transactional
    public void updateCrewActive(Long crewId) {
        Crew crew = crewRepository.findById(crewId).orElseThrow(
                () -> ApiException.builder()
                        .status(HttpStatus.BAD_REQUEST)
                        .errorCode("NOT_FOUND_CREW_MEMBER")
                        .errorMessage("찾을 수 없는 크루원입니다.")
                        .build());

        CrewLevel crewLevel = getCrewLevel(crew.getTeam().getId());
        if (Crew.checkAuth(crewLevel)){
            throw ApiException.builder()
                    .status(HttpStatus.BAD_REQUEST)
                    .errorCode("NOT_AUTHORIZED")
                    .errorMessage("권한이 없습니다.")
                    .build();
        }

        Crew.activeCrew(crew);
    }

    @Override
    @Transactional
    public void updateCrewInActive(Long teamId) {
        String email = SecurityUtil.getCurrentEmail();
        Crew crew = crewRepository.findCrewByTeamIdAndEmail(email, teamId);
        Crew.leave(crew);
    }

    private CrewLevel getCrewLevel(Long teamId) {
        String email = SecurityUtil.getCurrentEmail();
        return crewRepository.findCrewLevelByTeamIdAndEmail(teamId,email);
    }
}
