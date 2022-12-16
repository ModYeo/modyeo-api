package com.co.kr.modyeo.api.bbs.repository.custom.impl;

import com.co.kr.modyeo.api.bbs.domain.dto.search.TeamArticleSearch;
import com.co.kr.modyeo.api.bbs.domain.entity.TeamArticle;
import com.co.kr.modyeo.api.bbs.domain.entity.TeamReply;
import com.co.kr.modyeo.api.bbs.repository.custom.TeamArticleCustomRepository;
import com.co.kr.modyeo.common.enumerate.Yn;
import com.co.kr.modyeo.common.support.Querydsl4RepositorySupport;
import com.querydsl.core.types.dsl.BooleanExpression;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.util.StringUtils;

import java.util.List;

import static com.co.kr.modyeo.api.bbs.domain.entity.QArticle.article;
import static com.co.kr.modyeo.api.bbs.domain.entity.QTeamArticle.teamArticle;
import static com.co.kr.modyeo.api.bbs.domain.entity.QTeamReply.teamReply;
import static com.co.kr.modyeo.api.member.domain.entity.QMember.member;
import static com.co.kr.modyeo.api.bbs.domain.entity.link.QTeamArticleRecommend.teamArticleRecommend;
import static com.co.kr.modyeo.api.team.domain.entity.QTeam.team;

public class TeamArticleRepositoryImpl extends Querydsl4RepositorySupport implements TeamArticleCustomRepository {
    public TeamArticleRepositoryImpl() {
        super(TeamArticle.class);
    }

    @Override
    public Slice<TeamArticle> searchTeamArticle(TeamArticleSearch teamArticleSearch, PageRequest pageRequest) {
        return applySlicing(pageRequest, contentQuery ->
                contentQuery.select(teamArticle)
                        .from(teamArticle)
                        .innerJoin(teamArticle.team, team)
                        .fetchJoin()
                        .where(articleTitleLike(teamArticleSearch.getTitle()),
                                articleContentLike(teamArticleSearch.getContent()),
                                categoryIdEq(teamArticleSearch.getTeamId()),
                                memberIdEq(teamArticleSearch.getMemberId())));
    }

    private BooleanExpression memberIdEq(Long memberId) {
        return memberId != null && memberId > 0 ? member.id.eq(memberId) : null;
    }

    public List<TeamArticle> findArticleByMemberId(Long memberId) {
        return selectFrom(teamArticle)
                .where(teamArticle.createdBy.eq(memberId))
                .fetch();
    }

    @Override
    public List<TeamReply> findReplyByMemberId(Long memberId) {
        return selectFrom(teamReply)
                .where(teamReply.createdBy.eq(memberId))
                .fetch();
    }

    @Override
    public List<TeamArticle> findArticleByEmailAndRecommendY(Long memberId) {
        return select(teamArticle)
                .from(teamArticleRecommend)
                .innerJoin(teamArticleRecommend.teamArticle, teamArticle)
                .where(createdByEq(memberId),
                        teamArticleRecommend.recommendYn.eq(Yn.Y))
                .fetch();
    }

    private BooleanExpression createdByEq(Long memberId) {
        return memberId != null && memberId > 0  ? teamArticle.createdBy.eq(memberId) : null;
    }

    private BooleanExpression categoryIdEq(Long categoryId) {
        return categoryId != null ? team.id.eq(categoryId) : null;
    }

    private BooleanExpression articleContentLike(String content) {
        return StringUtils.hasText(content) ? article.content.contains(content) : null;
    }

    private BooleanExpression articleTitleLike(String title) {
        return StringUtils.hasText(title) ? article.title.contains(title) : null;
    }
}
