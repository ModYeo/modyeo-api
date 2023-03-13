package com.co.kr.modyeo.api.bbs.repository.custom.impl;

import com.co.kr.modyeo.api.bbs.domain.dto.response.ReplyResponse;
import com.co.kr.modyeo.api.bbs.domain.dto.response.TeamReplyResponse;
import com.co.kr.modyeo.api.bbs.domain.entity.TeamReply;
import com.co.kr.modyeo.api.bbs.repository.custom.TeamReplyCustomRepository;
import com.co.kr.modyeo.common.support.Querydsl4RepositorySupport;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;

import java.util.List;

import static com.co.kr.modyeo.api.bbs.domain.entity.QReply.reply;
import static com.co.kr.modyeo.api.bbs.domain.entity.QTeamReply.teamReply;
import static com.co.kr.modyeo.api.member.domain.entity.QMember.member;

public class TeamReplyCustomRepositoryImpl  extends Querydsl4RepositorySupport implements TeamReplyCustomRepository {
    public TeamReplyCustomRepositoryImpl() {
        super(TeamReply.class);
    }

    @Override
    public List<TeamReplyResponse> findByTeamArticleId(Long teamArticleId) {
        return select(Projections.constructor(TeamReplyResponse.class,
                teamReply.id,
                teamReply.teamArticle.id,
                teamReply.content,
                teamReply.replyDepth,
                teamReply.replyGroup,
                teamReply.createdBy,
                Projections.constructor(TeamReplyResponse.Member.class,
                        member.id,
                        member.email,
                        member.nickname),
                teamReply.createdDate
        )).from(teamReply)
                .innerJoin(member).on(teamReply.createdBy.eq(member.id))
                .where(teamArticleIdEq(teamArticleId))
                .fetch();
    }

    private BooleanExpression teamArticleIdEq(Long teamArticleId) {
        return teamArticleId  != null && teamArticleId > 0 ? teamReply.teamArticle.id.eq(teamArticleId) : null;
    }
}
