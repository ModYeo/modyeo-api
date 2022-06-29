package com.co.kr.modyeo.member.repository.impl;

import com.co.kr.modyeo.member.domain.dto.search.CrewSearch;
import com.co.kr.modyeo.member.domain.entity.Crew;
import com.co.kr.modyeo.member.domain.entity.QCrew;
import com.co.kr.modyeo.member.repository.custom.CrewCustomRepository;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

import static com.co.kr.modyeo.member.domain.entity.QCrew.crew;
import static com.co.kr.modyeo.member.domain.entity.link.QCrewCategory.crewCategory;

@Repository
public class CrewRepositoryImpl implements CrewCustomRepository {
    private final JPAQueryFactory queryFactory;

    public CrewRepositoryImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }
}
