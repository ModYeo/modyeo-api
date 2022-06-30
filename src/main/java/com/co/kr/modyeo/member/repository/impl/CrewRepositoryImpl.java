package com.co.kr.modyeo.member.repository.impl;

import com.co.kr.modyeo.common.support.Querydsl4RepositorySupport;
import com.co.kr.modyeo.member.domain.dto.search.CrewSearch;
import com.co.kr.modyeo.member.domain.entity.Crew;
import com.co.kr.modyeo.member.repository.custom.CrewCustomRepository;
import com.querydsl.core.types.dsl.BooleanExpression;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Repository;

import static com.co.kr.modyeo.member.domain.entity.QCrew.crew;
import static com.co.kr.modyeo.member.domain.entity.link.QCrewCategory.crewCategory;

@Repository
public class CrewRepositoryImpl extends Querydsl4RepositorySupport implements CrewCustomRepository  {

    public CrewRepositoryImpl() {
        super(Crew.class);
    }

    @Override
    public Slice<Crew> searchCrew(CrewSearch crewSearch, Pageable pageable) {
        return applySlicing(pageable,contentQuery ->
            contentQuery.select(crew)
                    .from(crew)
                    .innerJoin(crew.categoryList,crewCategory)
                    .where(crewIdEq(crewSearch.getCrewId())
                            ,crewNameEq(crewSearch.getName())
                            ,categoryIdEq(crewSearch.getCategoryId())));

    }

    private BooleanExpression crewIdEq(Long id) {
        return id != null ? crew.id.eq(id) : null;
    }

    private BooleanExpression crewNameEq(String name) {
        return name != null ? crew.name.eq(name) : null;
    }

    private BooleanExpression categoryIdEq(Long id){ return id != null ? crewCategory.category.id.eq(id) : null; }
}
