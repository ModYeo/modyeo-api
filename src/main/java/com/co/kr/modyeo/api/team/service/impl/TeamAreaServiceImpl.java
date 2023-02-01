package com.co.kr.modyeo.api.team.service.impl;

import com.co.kr.modyeo.api.geo.domain.entity.EmdArea;
import com.co.kr.modyeo.api.geo.repository.EmdAreaRepository;
import com.co.kr.modyeo.api.team.domain.dto.request.TeamCreateActivAreaRequest;
import com.co.kr.modyeo.api.team.domain.dto.request.TeamUpdateLimitMeterRequest;
import com.co.kr.modyeo.api.team.domain.entity.Team;
import com.co.kr.modyeo.api.team.domain.entity.TeamActivArea;
import com.co.kr.modyeo.api.team.repository.TeamActivAreaRepository;
import com.co.kr.modyeo.api.team.repository.TeamRepository;
import com.co.kr.modyeo.api.team.service.TeamAreaService;
import com.co.kr.modyeo.common.exception.ApiException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class TeamAreaServiceImpl implements TeamAreaService {
    private final TeamActivAreaRepository teamAreaRepository;
    private final EmdAreaRepository emdAreaRepository;
    private final TeamRepository teamRepository;

    public Long createTeamActivArea(TeamCreateActivAreaRequest teamCreateActivAreaRequest){
        Team team = teamRepository.findById(teamCreateActivAreaRequest.getTeamId())
                .orElseThrow(() -> ApiException.builder()
                        .status(HttpStatus.BAD_REQUEST)
                        .errorCode("")
                        .errorMessage("")
                        .build());

        EmdArea emdArea = emdAreaRepository.findById(teamCreateActivAreaRequest.getEmdId())
                .orElseThrow(() -> ApiException.builder()
                        .status(HttpStatus.BAD_REQUEST)
                        .errorCode("")
                        .errorMessage("")
                        .build());

        TeamActivArea teamArea = TeamActivArea.createTeamArea(team, emdArea, teamCreateActivAreaRequest.getLimitMeters());
        teamAreaRepository.save(teamArea);
        team.getAreaList().add(teamArea);

        return team.getId();
    }

    @Override
    public void deleteTeamActivArea(Long teamActivAreaId) {
        TeamActivArea teamArea = teamAreaRepository.findById(teamActivAreaId).
                orElseThrow(()-> ApiException.builder()
                        .status(HttpStatus.BAD_REQUEST)
                        .errorCode("")
                        .errorMessage("")
                        .build());

        teamAreaRepository.deleteById(teamArea.getActiveId());
    }

    @Override
    public Long updateTeamActivArea(TeamUpdateLimitMeterRequest teamUpdateLimitMeterRequest) {
        Optional<TeamActivArea> teamArea = teamAreaRepository.findById(teamUpdateLimitMeterRequest.getTeamId());
        teamArea.get().updateTeamActiveArea(teamUpdateLimitMeterRequest.getLimitMeters());
        return teamArea.get().getTeam().getId();
    }
}
