package com.co.kr.modyeo.api.team.repository.custom;

import com.co.kr.modyeo.api.team.domain.entity.link.MemberTeam;

import java.util.List;

public interface MemberTeamCustomRepository {

    List<MemberTeam> findByTeamId(Long teamId);
}
