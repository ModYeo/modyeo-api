package com.co.kr.modyeo.team.repository.impl;

import com.co.kr.modyeo.team.repository.custom.TeamCategoryCustomRepository;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository
public class TeamCategoryRepositoryImpl implements TeamCategoryCustomRepository {
    private final JPAQueryFactory queryFactory;

    public TeamCategoryRepositoryImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }
}
