package com.co.kr.modyeo.member.repository.impl;

import com.co.kr.modyeo.member.repository.custom.CrewCategoryCustomRepository;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository
public class CrewCategoryRepositoryImpl implements CrewCategoryCustomRepository {
    private final JPAQueryFactory queryFactory;

    public CrewCategoryRepositoryImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }
}
