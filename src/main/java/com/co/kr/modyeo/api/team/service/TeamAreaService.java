package com.co.kr.modyeo.api.team.service;

import com.co.kr.modyeo.api.team.domain.dto.request.TeamCreateActivAreaRequest;
import com.co.kr.modyeo.api.team.domain.dto.request.TeamUpdateLimitMeterRequest;

public interface TeamAreaService {
    Long createTeamActivArea(TeamCreateActivAreaRequest teamCreateActivAreaRequest);
    void deleteTeamActivArea(Long teamActivAreaId);
    Long updateTeamActivArea(TeamUpdateLimitMeterRequest teamUpdateLimitMeterRequest);
}
