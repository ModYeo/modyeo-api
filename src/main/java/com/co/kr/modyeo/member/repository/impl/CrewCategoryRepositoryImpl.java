package com.co.kr.modyeo.member.repository.impl;

import com.co.kr.modyeo.member.domain.dto.search.CrewSearch;
import com.co.kr.modyeo.member.domain.entity.Crew;
import com.co.kr.modyeo.member.repository.custom.CrewCategoryCustomRepository;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

import static com.co.kr.modyeo.member.domain.entity.QCrew.crew;
import static com.co.kr.modyeo.member.domain.entity.link.QCrewCategory.crewCategory;

@Repository
public class CrewCategoryRepositoryImpl implements CrewCategoryCustomRepository {
    private final JPAQueryFactory queryFactory;

    public CrewCategoryRepositoryImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public List<Crew> searchCrew(CrewSearch crewSearch) {
        return queryFactory.select(crew)
                .from(crewCategory)
                .innerJoin(crewCategory.crew,crew)
                .where(crewIdEq(crewSearch.getCrewId())
                        ,crewNameEq(crewSearch.getName())
                        ,categoryIdEq(crewSearch.getCategoryId()))
                .fetch();
    }

    private BooleanExpression crewIdEq(Long id) {
        return id != null ? crew.id.eq(id) : null;
    }

    private BooleanExpression crewNameEq(String name) {
        return name != null ? crew.name.eq(name) : null;
    }

    private BooleanExpression categoryIdEq(Long id){ return id != null ? crewCategory.category.id.eq(id) : null; }
}
