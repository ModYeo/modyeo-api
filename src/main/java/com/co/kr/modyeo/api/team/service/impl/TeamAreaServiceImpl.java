package com.co.kr.modyeo.api.team.service.impl;

import com.co.kr.modyeo.api.geo.domain.entity.EmdArea;
import com.co.kr.modyeo.api.geo.repository.EmdAreaRepository;
import com.co.kr.modyeo.api.team.domain.dto.request.TeamCreateActivAreaRequest;
import com.co.kr.modyeo.api.team.domain.dto.request.TeamUpdateLimitMeterRequest;
import com.co.kr.modyeo.api.team.domain.entity.Team;
import com.co.kr.modyeo.api.team.domain.entity.TeamActiveArea;
import com.co.kr.modyeo.api.team.repository.TeamActivAreaRepository;
import com.co.kr.modyeo.api.team.repository.TeamRepository;
import com.co.kr.modyeo.api.team.service.TeamAreaService;
import com.co.kr.modyeo.common.exception.ApiException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

        TeamActiveArea teamArea = TeamActiveArea.createTeamArea(team, emdArea, teamCreateActivAreaRequest.getLimitMeters());
        teamAreaRepository.save(teamArea);
        team.getAreaList().add(teamArea);

        return team.getId();
    }

    @Override
    public void deleteTeamActivArea(Long teamActiveAreaId) {
        TeamActiveArea teamArea = teamAreaRepository.findById(teamActiveAreaId).
                orElseThrow(()-> ApiException.builder()
                        .status(HttpStatus.BAD_REQUEST)
                        .errorCode("")
                        .errorMessage("")
                        .build());

        teamAreaRepository.deleteById(teamArea.getActiveId());
    }

    @Override
    public Long updateTeamActivArea(TeamUpdateLimitMeterRequest teamUpdateLimitMeterRequest) {
        TeamActiveArea teamArea = teamAreaRepository.findById(teamUpdateLimitMeterRequest.getActiveAreaId())
                .orElseThrow(()-> ApiException.builder()
                    .status(HttpStatus.BAD_REQUEST)
                    .errorCode("") //::TODO 이넘으로 만들어서 넣기.
                    .errorMessage("")
                    .build());

        if (teamArea.getTeam().getId()==teamUpdateLimitMeterRequest.getTeamId()){
            teamArea.updateTeamActiveArea(teamUpdateLimitMeterRequest.getLimitMeters());
        } else ApiException.builder()
                .status(HttpStatus.BAD_REQUEST)
                .errorCode("")
                .errorMessage("")
                .build();

        return teamArea.getTeam().getId();
    }
}
