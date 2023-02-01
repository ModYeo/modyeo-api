package com.co.kr.modyeo.api.team.repository.custom.impl;

import com.co.kr.modyeo.api.team.domain.dto.response.TeamResponse;
import com.co.kr.modyeo.api.team.domain.entity.Team;
import com.co.kr.modyeo.common.support.Querydsl4RepositorySupport;
import com.co.kr.modyeo.api.team.domain.dto.search.TeamSearch;
import com.co.kr.modyeo.api.team.repository.custom.TeamCustomRepository;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.util.List;

import static com.co.kr.modyeo.api.member.domain.entity.QMember.member;
import static com.co.kr.modyeo.api.team.domain.entity.QTeam.team;
import static com.co.kr.modyeo.api.team.domain.entity.QTeamActivArea.teamActivArea;
import static com.co.kr.modyeo.api.team.domain.entity.link.QCrew.crew;
import static com.co.kr.modyeo.api.team.domain.entity.link.QTeamCategory.teamCategory;

@Repository
public class TeamRepositoryImpl extends Querydsl4RepositorySupport implements TeamCustomRepository {

    public TeamRepositoryImpl() {
        super(Team.class);
    }

    @Override
    public Slice<Team> searchTeam(TeamSearch teamSearch, Pageable pageable) {
        return applySlicing(pageable, contentQuery ->
                contentQuery.select(team)
                        .from(team)
                        .innerJoin(team.categoryList, teamCategory)
                        .innerJoin(team.crewList, crew)
                        .innerJoin(team.areaList, teamActivArea)
                        .where(teamNameLike(teamSearch.getName()),
                                memberIdEq(teamSearch.getMemberId()),
                                categoryIdEq(teamSearch.getCategoryId())));

    }

    private BooleanExpression memberIdEq(Long memberId) {
        return memberId != null && memberId > 0 ? crew.member.id.eq(memberId): null;
    }

    @Override
    public List<TeamResponse> findMyTeam(String email) {
        return select(Projections.constructor(TeamResponse.class,
                team.id, team.name))
                .from(team)
                .innerJoin(team.crewList, crew)
                .innerJoin(crew.member, member)
                .where(member.email.eq(email))
                .fetch();
    }

    private BooleanExpression crewIdEq(Long id) {
        return id != null ? team.id.eq(id) : null;
    }

    private BooleanExpression teamNameLike(String name) {
        return StringUtils.hasText(name)  ? team.name.contains(name) : null;
    }

    private BooleanExpression categoryIdEq(Long id) {
        return id != null ? teamCategory.category.id.eq(id) : null;
    }
}
