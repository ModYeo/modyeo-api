package com.co.kr.modyeo.api.team.repository.custom;

import com.co.kr.modyeo.api.member.domain.dto.response.ApplicationMemberDetail;
import com.co.kr.modyeo.api.team.domain.entity.link.MemberTeam;

import java.util.List;

public interface MemberTeamCustomRepository {

    List<MemberTeam> findByTeamId(Long teamId);

    ApplicationMemberDetail findApplicationMemberByMemberId(Long memberId);
}
