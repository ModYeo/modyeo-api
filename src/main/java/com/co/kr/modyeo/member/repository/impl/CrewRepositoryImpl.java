package com.co.kr.modyeo.member.repository.impl;

import com.co.kr.modyeo.member.repository.custom.CrewCustomRepository;
import com.querydsl.jpa.impl.JPAQueryFactory;

import javax.persistence.EntityManager;

public class CrewRepositoryImpl implements CrewCustomRepository {
    private final JPAQueryFactory queryFactory;

    public CrewRepositoryImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }
}
