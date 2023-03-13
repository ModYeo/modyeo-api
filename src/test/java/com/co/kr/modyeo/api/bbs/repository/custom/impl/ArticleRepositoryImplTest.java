package com.co.kr.modyeo.api.bbs.repository.custom.impl;

import com.co.kr.modyeo.api.bbs.domain.dto.response.ArticleDetail;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.cassandra.AutoConfigureDataCassandra;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;

import javax.persistence.EntityManager;

import static org.junit.jupiter.api.Assertions.*;
import static com.co.kr.modyeo.api.bbs.domain.entity.QArticle.article;
import static com.co.kr.modyeo.api.category.domain.entity.QCategory.category;
import static com.co.kr.modyeo.api.member.domain.entity.QMember.member;
import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@ActiveProfiles("dev")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class ArticleRepositoryImplTest {

    @Autowired
    TestEntityManager testEntityManager;

    EntityManager em;

    @BeforeEach
    void init(){
        em = testEntityManager.getEntityManager();
    }

    @Test
    void findArticle() {
        JPAQueryFactory query = new JPAQueryFactory(em);
        ArticleDetail articleDetail = query.select(Projections.constructor(ArticleDetail.class,
                        article.id,
                        article.filePath,
                        article.title,
                        article.content,
                        article.isHidden,
                        article.hitCount,
                        article.recommendCount,
                        article.createdBy,
                        Projections.constructor(ArticleDetail.Member.class,
                                member.id,
                                member.email,
                                member.nickname),
                        Projections.constructor(ArticleDetail.Category.class,
                                category.id,
                                category.name,
                                category.imagePath
                        ), article.createdDate))
                .from(article)
                .innerJoin(member).on(article.createdBy.eq(member.id))
                .innerJoin(article.category, category)
                .where(article.id.eq(1L))
                .fetchOne();

        assertThat(articleDetail.getMember().getNickname()).isEqualTo("안종길");
        assertThat(articleDetail.getCategory().getName()).isEqualTo("스터디");
    }
}