package com.co.kr.modyeo.api.team.service;

import com.co.kr.modyeo.api.team.domain.dto.request.ApplicationFormRequest;
import com.co.kr.modyeo.api.team.domain.dto.request.TeamApplicationRequest;
import com.co.kr.modyeo.api.team.domain.dto.response.ApplicationFormDetail;
import com.co.kr.modyeo.api.team.domain.entity.enumerate.JoinStatus;
import com.co.kr.modyeo.api.team.domain.entity.link.MemberTeam;


public interface TeamApplicationService {
    MemberTeam applicantCrew(TeamApplicationRequest teamApplicationRequest);

    MemberTeam updateJoinStatus(Long memberCrewId, JoinStatus joinStatus);

    void createApplicationForm(ApplicationFormRequest applicationFormRequest);

    ApplicationFormDetail getApplicationForm(Long teamId);

    void updateApplicationForm(Long applicationFromId, ApplicationFormRequest applicationFormRequest);

    void deleteApplicationForm(Long applicationFromId);
}
