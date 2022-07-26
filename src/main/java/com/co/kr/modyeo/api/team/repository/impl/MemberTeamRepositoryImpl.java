package com.co.kr.modyeo.api.team.repository.impl;

import com.co.kr.modyeo.common.support.Querydsl4RepositorySupport;
import com.co.kr.modyeo.api.team.domain.entity.link.MemberTeam;
import com.co.kr.modyeo.api.team.repository.custom.MemberTeamCustomRepository;

public class MemberTeamRepositoryImpl extends Querydsl4RepositorySupport implements MemberTeamCustomRepository {

    public MemberTeamRepositoryImpl() {
        super(MemberTeam.class);
    }
}
