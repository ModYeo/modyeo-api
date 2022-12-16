package com.co.kr.modyeo.api.team.repository.custom.impl;

import com.co.kr.modyeo.api.team.domain.dto.search.CrewSearch;
import com.co.kr.modyeo.api.team.domain.entity.enumerate.CrewLevel;
import com.co.kr.modyeo.api.team.domain.entity.link.Crew;
import com.co.kr.modyeo.api.team.repository.custom.CrewCustomRepository;
import com.co.kr.modyeo.common.enumerate.Yn;
import com.co.kr.modyeo.common.support.Querydsl4RepositorySupport;
import com.querydsl.core.types.dsl.BooleanExpression;
import org.springframework.util.StringUtils;

import java.util.List;

import static com.co.kr.modyeo.api.member.domain.entity.QMember.member;
import static com.co.kr.modyeo.api.team.domain.entity.QTeam.team;
import static com.co.kr.modyeo.api.team.domain.entity.link.QCrew.crew;

public class CrewRepositoryImpl extends Querydsl4RepositorySupport implements CrewCustomRepository {

    public CrewRepositoryImpl() {
        super(Crew.class);
    }

    @Override
    public List<Crew> searchCrew(CrewSearch crewSearch) {
        return select(crew)
                .from(crew)
                .innerJoin(crew.team, team)
                .innerJoin(crew.member, member)
                .where(eqTeamId(crewSearch.getTeamId())
                        , eqIsActivated(crewSearch.getIsActivated())
                        , eqCrewLevel(crewSearch.getLevel()))
                .fetchJoin()
                .fetch();
    }

    @Override
    public CrewLevel findCrewLevelByTeamIdAndMemberId(Long teamId, Long memberId) {
        return select(crew.crewLevel)
                .from(crew)
                .innerJoin(crew.member, member)
                .where(memberIdEq(memberId),
                        eqTeamId(teamId))
                .fetchOne();
    }

    @Override
    public Crew findCrewByTeamIdAndMemberId(Long memberId, Long teamId) {
        return select(crew)
                .from(crew)
                .where(memberIdEq(memberId),
                        eqTeamId(teamId))
                .fetchOne();
    }

    private BooleanExpression memberIdEq(Long memberId) {
        return memberId != null && memberId > 0 ? crew.member.id.eq(memberId) : null;
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
