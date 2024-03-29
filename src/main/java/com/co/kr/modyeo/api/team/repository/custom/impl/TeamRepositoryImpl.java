package com.co.kr.modyeo.api.team.repository.custom.impl;

import com.co.kr.modyeo.api.team.domain.dto.response.TeamResponse;
import com.co.kr.modyeo.api.team.domain.dto.search.SomeoneTeamSearch;
import com.co.kr.modyeo.api.team.domain.dto.search.TeamSearch;
import com.co.kr.modyeo.api.team.domain.entity.Team;
import com.co.kr.modyeo.api.team.repository.custom.TeamCustomRepository;
import com.co.kr.modyeo.common.support.Querydsl4RepositorySupport;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.util.List;

import static com.co.kr.modyeo.api.member.domain.entity.QMember.member;
import static com.co.kr.modyeo.api.team.domain.entity.QTeam.team;
import static com.co.kr.modyeo.api.team.domain.entity.QTeamActiveArea.teamActiveArea;
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
                        .innerJoin(team.areaList, teamActiveArea)
                        .where(
                                teamNameLike(teamSearch.getName()),
                                categoryIdEq(teamSearch.getCategoryId()),
                                emdIdEq(teamSearch.getEmdId())
                              )
        );
    }

    @Override
    public Slice<Team> searchSomeoneTeam(SomeoneTeamSearch memberTeamSearch, Pageable pageable) {
        return applySlicing(pageable, contentQuery ->
                contentQuery.select(team)
                        .from(team)
                        .innerJoin(team.crewList, crew)
                        .innerJoin(team.categoryList, teamCategory)
                        .innerJoin(team.areaList, teamActiveArea)
                        .where(
                                memberIdEq(memberTeamSearch.getMemberId()),
                                categoryIdEq(memberTeamSearch.getCategoryId()),
                                emdIdEq(memberTeamSearch.getEmdId())
                        )
                );
    }

    private BooleanExpression emdIdEq(Long emdId){
        return emdId != null && emdId > 0 ? teamActiveArea.emdArea.id.eq(emdId) : null;
    }
    private BooleanExpression memberIdEq(Long memberId) {
        return memberId != null && memberId > 0 ? crew.member.id.eq(memberId) : null;
    }

    @Override
    public List<Team> findMyTeam(Long memberId) {
        return select(team)
                .from(team)
                .innerJoin(team.crewList, crew)
                .where(memberIdEq(memberId))
                .fetch();
    }

    @Override
    public List<Team> getRecommendTeams(Long emdId, List<Long> categoryIdList) {
        return select(team)
                .from(team)
                .innerJoin(team.categoryList, teamCategory)
                .innerJoin(team.areaList, teamActiveArea)
                .where(
                        teamCategory.category.id.in(categoryIdList),
                        emdIdEq(emdId)
                ).fetch();
    }

    private BooleanExpression teamNameLike(String name) {
        return StringUtils.hasText(name)  ? team.name.contains(name) : null;
    }

    private BooleanExpression categoryIdEq(Long id) {
        return id != null ? teamCategory.category.id.eq(id) : null;
    }
}
