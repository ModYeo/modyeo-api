package com.co.kr.modyeo.team.service;

import com.co.kr.modyeo.team.domain.entity.link.MemberTeam;
import com.co.kr.modyeo.team.domain.entity.enumerate.JoinStatus;


public interface TeamApplicationService {
    MemberTeam applicantCrew(Long memberId, Long crewId);

    MemberTeam updateJoinStatus(Long memberCrewId, JoinStatus joinStatus);
}
