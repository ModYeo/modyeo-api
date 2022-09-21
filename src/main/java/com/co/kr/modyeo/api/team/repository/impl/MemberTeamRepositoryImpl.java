package com.co.kr.modyeo.api.team.repository.impl;

import com.co.kr.modyeo.api.member.domain.dto.response.ApplicationMemberDetail;
import com.co.kr.modyeo.common.support.Querydsl4RepositorySupport;
import com.co.kr.modyeo.api.team.domain.entity.link.MemberTeam;
import com.co.kr.modyeo.api.team.repository.custom.MemberTeamCustomRepository;
import com.querydsl.core.types.Projections;

import java.util.List;

import static com.co.kr.modyeo.api.member.domain.entity.QMember.member;
import static com.co.kr.modyeo.api.team.domain.entity.QTeam.team;
import static com.co.kr.modyeo.api.team.domain.entity.link.QMemberTeam.memberTeam;

public class MemberTeamRepositoryImpl extends Querydsl4RepositorySupport implements MemberTeamCustomRepository {

    public MemberTeamRepositoryImpl() {
        super(MemberTeam.class);
    }

    @Override
    public List<MemberTeam> findByTeamId(Long teamId) {
        return select(memberTeam)
                .from(memberTeam)
                .innerJoin(memberTeam.member, member)
                .innerJoin(memberTeam.team, team)
                .where(memberTeam.team.id.eq(teamId))
                .fetchJoin()
                .fetch();
    }

    @Override
    public ApplicationMemberDetail findApplicationMemberByMemberId(Long memberId) {
        return select(Projections.constructor(ApplicationMemberDetail.class,
                member.id,
                memberTeam.id,
                member.nickname,
                member.sex,
                member.birthDay,
                memberTeam.createdDate))
                .from(memberTeam)
                .innerJoin(memberTeam.member, member)
                .fetchOne();
    }
}
