package com.co.kr.modyeo.api.bbs.repository.impl;

import com.co.kr.modyeo.api.bbs.domain.dto.search.TeamArticleSearch;
import com.co.kr.modyeo.api.bbs.domain.entity.TeamArticle;
import com.co.kr.modyeo.api.bbs.domain.entity.TeamReply;
import com.co.kr.modyeo.api.bbs.repository.custom.TeamArticleCustomRepository;
import com.co.kr.modyeo.common.support.Querydsl4RepositorySupport;
import com.querydsl.core.types.dsl.BooleanExpression;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;

import java.util.List;

import static com.co.kr.modyeo.api.bbs.domain.entity.QArticle.article;
import static com.co.kr.modyeo.api.bbs.domain.entity.QTeamArticle.teamArticle;
import static com.co.kr.modyeo.api.bbs.domain.entity.QTeamReply.teamReply;
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
                                categoryIdEq(teamArticleSearch.getTeamId())));
    }

    @Override
    public List<TeamArticle> findArticleByEmail(String email) {
        return selectFrom(teamArticle)
                .where(teamArticle.createdBy.eq(email))
                .fetch();
    }

    @Override
    public List<TeamReply> findReplyByEmail(String email) {
        return selectFrom(teamReply)
                .where(teamReply.createdBy.eq(email))
                .fetch();
    }

    private BooleanExpression categoryIdEq(Long categoryId) {
        return categoryId != null ? team.id.eq(categoryId) : null;
    }

    private BooleanExpression articleContentLike(String content) {
        return content != null ? article.content.contains(content) : null;
    }

    private BooleanExpression articleTitleLike(String title) {
        return title != null ? article.title.contains(title) : null;
    }
}
