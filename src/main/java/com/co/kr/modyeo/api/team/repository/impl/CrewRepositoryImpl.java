package com.co.kr.modyeo.api.team.repository.impl;

import com.co.kr.modyeo.api.team.domain.dto.search.SearchCrew;
import com.co.kr.modyeo.api.team.domain.entity.enumerate.CrewLevel;
import com.co.kr.modyeo.api.team.domain.entity.link.Crew;
import com.co.kr.modyeo.api.team.repository.custom.CrewCustomRepository;
import com.co.kr.modyeo.common.enumerate.Yn;
import com.co.kr.modyeo.common.support.Querydsl4RepositorySupport;
import com.querydsl.core.types.dsl.BooleanExpression;

import java.util.List;
import java.util.Objects;

import static com.co.kr.modyeo.api.member.domain.entity.QMember.member;
import static com.co.kr.modyeo.api.team.domain.entity.QTeam.team;
import static com.co.kr.modyeo.api.team.domain.entity.link.QCrew.crew;

public class CrewRepositoryImpl extends Querydsl4RepositorySupport implements CrewCustomRepository {

    public CrewRepositoryImpl() {
        super(Crew.class);
    }

    @Override
    public List<Crew> searchCrew(SearchCrew searchCrew) {
        return select(crew)
                .from(crew)
                .innerJoin(crew.team, team)
                .innerJoin(crew.member, member)
                .where(eqTeamId(searchCrew.getTeamId())
                        , eqIsActivated(searchCrew.getIsActivated())
                        , eqCrewLevel(searchCrew.getLevel()))
                .fetchJoin()
                .fetch();
    }

    @Override
    public CrewLevel findCrewLevelByTeamIdAndEmail(Long teamId, String email) {
        return select(crew.crewLevel)
                .from(crew)
                .innerJoin(crew.member, member)
                .where(eqEmail(email),
                        eqTeamId(teamId))
                .fetchOne();
    }

    @Override
    public Crew findCrewByTeamIdAndEmail(String email, Long teamId) {
        return select(crew)
                .from(crew)
                .innerJoin(crew.member, member)
                .where(eqEmail(email),
                        eqTeamId(teamId))
                .fetchOne();
    }

    private BooleanExpression eqEmail(String email) {
        return email != null && !Objects.equals(email, "") ? member.email.eq(email) : null;
    }

    private BooleanExpression eqTeamId(Long teamId) {
        return teamId != null ? crew.team.id.eq(teamId) : null;
    }

    private BooleanExpression eqIsActivated(Yn isActivated) {
        return isActivated != null ? crew.isActivated.eq(isActivated) : null;
    }

    private BooleanExpression eqCrewLevel(CrewLevel level) {
        return level != null ? crew.crewLevel.eq(level) : null;
    }
}
