package com.co.kr.modyeo.member.repository.impl;

import com.co.kr.modyeo.common.support.Querydsl4RepositorySupport;
import com.co.kr.modyeo.member.domain.entity.link.MemberTeam;
import com.co.kr.modyeo.member.repository.custom.MemberTeamCustomRepository;

public class MemberTeamRepositoryImpl extends Querydsl4RepositorySupport implements MemberTeamCustomRepository {

    public MemberTeamRepositoryImpl() {
        super(MemberTeam.class);
    }
}
