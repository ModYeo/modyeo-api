package com.co.kr.modyeo.api.bbs.repository.custom.impl;

import com.co.kr.modyeo.api.bbs.domain.entity.Reply;
import com.co.kr.modyeo.api.bbs.repository.custom.ReplyCustomRepository;
import com.co.kr.modyeo.common.support.Querydsl4RepositorySupport;

import java.util.List;

import static com.co.kr.modyeo.api.bbs.domain.entity.QReply.reply;

public class ReplyRepositoryImpl extends Querydsl4RepositorySupport implements ReplyCustomRepository {

    public ReplyRepositoryImpl() {
        super(Reply.class);
    }

    @Override
    public List<Reply> findReplyByEmail(String email) {
        return selectFrom(reply)
                .where(reply.createdBy.eq(email))
                .fetch();
    }
}
