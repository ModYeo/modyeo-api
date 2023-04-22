package com.co.kr.modyeo.api.bbs.repository.custom.impl;

import com.co.kr.modyeo.api.bbs.domain.dto.response.ReplyResponse;
import com.co.kr.modyeo.api.bbs.domain.entity.Reply;
import com.co.kr.modyeo.api.bbs.repository.custom.ReplyCustomRepository;
import com.co.kr.modyeo.common.support.Querydsl4RepositorySupport;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;

import java.util.List;

import static com.co.kr.modyeo.api.bbs.domain.entity.QReply.reply;
import static com.co.kr.modyeo.api.member.domain.entity.QMember.member;
public class ReplyRepositoryImpl extends Querydsl4RepositorySupport implements ReplyCustomRepository {

    public ReplyRepositoryImpl() {
        super(Reply.class);
    }

    @Override
    public List<Reply> findReplyByEmail(Long memberId) {
        return selectFrom(reply)
                .where(reply.createdBy.eq(memberId))
                .fetch();
    }

    @Override
    public List<ReplyResponse> findByArticleId(Long articleId) {
        return select(Projections.constructor(ReplyResponse.class,
                reply.id,
                reply.article.id,
                reply.content,
                reply.replyDepth,
                reply.replyGroup,
                reply.createdBy,
                Projections.constructor(ReplyResponse.Member.class,
                        member.id,
                        member.email,
                        member.nickname),
                reply.deleteYn,
                reply.createdDate
                )).from(reply)
                .innerJoin(member).on(reply.createdBy.eq(member.id))
                .where(articleIdEq(articleId))
                .fetch();
    }

    private BooleanExpression articleIdEq(Long articleId) {
        return articleId  != null && articleId > 0 ? reply.article.id.eq(articleId) : null;
    }
}
