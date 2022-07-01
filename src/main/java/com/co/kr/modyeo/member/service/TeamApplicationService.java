package com.co.kr.modyeo.member.service;

import com.co.kr.modyeo.member.domain.entity.link.MemberTeam;
import com.co.kr.modyeo.member.domain.enumerate.JoinStatus;


public interface TeamApplicationService {
    MemberTeam applicantCrew(Long memberId, Long crewId);

    MemberTeam updateJoinStatus(Long memberCrewId, JoinStatus joinStatus);
}
