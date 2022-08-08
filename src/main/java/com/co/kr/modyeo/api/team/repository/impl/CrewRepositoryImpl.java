package com.co.kr.modyeo.api.team.repository.impl;

import com.co.kr.modyeo.api.team.domain.entity.link.Crew;
import com.co.kr.modyeo.api.team.repository.custom.CrewCustomRepository;
import com.co.kr.modyeo.common.support.Querydsl4RepositorySupport;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.BooleanExpression;
import org.springframework.data.domain.Slice;

import java.util.List;

import static com.co.kr.modyeo.api.member.domain.entity.QMember.member;
import static com.co.kr.modyeo.api.team.domain.entity.QTeam.team;
import static com.co.kr.modyeo.api.team.domain.entity.link.QCrew.crew;

public class CrewRepositoryImpl extends Querydsl4RepositorySupport implements CrewCustomRepository {

    public CrewRepositoryImpl() {
        super(Crew.class);
    }

    @Override
    public List<Crew> searchCrew(Long teamId) {
        return select(crew)
                .from(crew)
                .innerJoin(crew.team, team)
                .innerJoin(crew.member, member)
                .where(eqTeamId(teamId))
                .fetchJoin()
                .fetch();
    }

    private BooleanExpression eqTeamId(Long teamId) {
        return teamId != null? team.id.eq(teamId) : null;
    }
}
