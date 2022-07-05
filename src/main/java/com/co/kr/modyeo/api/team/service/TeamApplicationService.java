package com.co.kr.modyeo.api.team.service;

import com.co.kr.modyeo.api.team.domain.entity.enumerate.JoinStatus;
import com.co.kr.modyeo.api.team.domain.entity.link.MemberTeam;


public interface TeamApplicationService {
    MemberTeam applicantCrew(Long memberId, Long crewId);

    MemberTeam updateJoinStatus(Long memberCrewId, JoinStatus joinStatus);
}
