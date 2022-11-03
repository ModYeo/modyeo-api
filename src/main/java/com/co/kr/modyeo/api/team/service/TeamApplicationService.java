package com.co.kr.modyeo.api.team.service;

import com.co.kr.modyeo.api.team.domain.dto.request.ApplicationFormRequest;
import com.co.kr.modyeo.api.team.domain.dto.request.TeamApplicationRequest;
import com.co.kr.modyeo.api.team.domain.dto.response.ApplicationFormDetail;
import com.co.kr.modyeo.api.team.domain.dto.response.MemberTeamResponse;
import com.co.kr.modyeo.api.team.domain.entity.enumerate.JoinStatus;
import com.co.kr.modyeo.api.team.domain.entity.link.MemberTeam;

import java.util.List;


public interface TeamApplicationService {
    Long applicantCrew(TeamApplicationRequest teamApplicationRequest);

    Long updateJoinStatus(Long memberCrewId, JoinStatus joinStatus);

    Long createApplicationForm(ApplicationFormRequest applicationFormRequest);

    ApplicationFormDetail getApplicationForm(Long teamId);

    Long updateApplicationForm(Long applicationFromId, ApplicationFormRequest applicationFormRequest);

    void deleteApplicationForm(Long applicationFromId);

    List<MemberTeamResponse> getTeamApplication(Long teamId);

    void deleteTeamApplication(Long memberTeamId);
}
