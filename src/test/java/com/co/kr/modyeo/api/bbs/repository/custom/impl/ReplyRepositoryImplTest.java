package com.co.kr.modyeo.api.bbs.repository.custom.impl;

import com.co.kr.modyeo.api.bbs.domain.dto.response.ReplyResponse;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;

import javax.persistence.EntityManager;

import java.util.List;

import static com.co.kr.modyeo.api.bbs.domain.entity.QReply.reply;
import static com.co.kr.modyeo.api.member.domain.entity.QMember.member;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@ActiveProfiles("dev")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class ReplyRepositoryImplTest {

    @Autowired
    TestEntityManager testEntityManager;

    EntityManager em;

    @BeforeEach
    void init() {
        em = testEntityManager.getEntityManager();
    }

    @Test
    void findByArticleId() {
        JPAQueryFactory query = new JPAQueryFactory(em);
        List<ReplyResponse> replyResponseList = query.select(Projections.constructor(ReplyResponse.class,
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
                        reply.createdDate
                )).from(reply)
                .innerJoin(member).on(reply.createdBy.eq(member.id))
                .where(reply.article.id.eq(1L))
                .fetch();

        assertThat(replyResponseList.size()).isEqualTo(2);
    }
}