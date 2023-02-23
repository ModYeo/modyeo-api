package com.co.kr.modyeo.api.geo.repository.custom.impl;

import com.co.kr.modyeo.api.geo.domain.dto.response.EmdAreaDetail;
import com.co.kr.modyeo.api.geo.domain.entity.EmdArea;
import com.co.kr.modyeo.api.geo.repository.custom.EmdAreaCustomRepository;
import com.co.kr.modyeo.common.support.Querydsl4RepositorySupport;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import org.springframework.util.StringUtils;

import java.util.List;

import static com.co.kr.modyeo.api.geo.domain.entity.QEmdArea.emdArea;
import static com.co.kr.modyeo.api.geo.domain.entity.QSidoArea.sidoArea;
import static com.co.kr.modyeo.api.geo.domain.entity.QSiggArea.siggArea;

public class EmdAreaRepositoryImpl extends Querydsl4RepositorySupport implements EmdAreaCustomRepository {

    public EmdAreaRepositoryImpl() {
        super(EmdArea.class);
    }

    @Override
    public List<EmdAreaDetail> findByNameContains(String name) {
        return select(Projections.constructor(EmdAreaDetail.class,
                emdArea.id,
                siggArea.id,
                siggArea.name,
                sidoArea.id,
                sidoArea.name,
                emdArea.code,
                emdArea.name,
                emdArea.version))
                .from(emdArea)
                .innerJoin(emdArea.siggArea, siggArea)
                .innerJoin(siggArea.sidoArea, sidoArea)
                .where(containName(name))
                .fetch()
                ;
    }

    private BooleanExpression containName(String name) {
        return StringUtils.hasText(name) ? emdArea.name.contains(name) : null;
    }
}
