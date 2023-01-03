package com.co.kr.modyeo.api.columncode.repository.custom.impl;

import com.co.kr.modyeo.api.columncode.domain.dto.request.ColumnCodeSearch;
import com.co.kr.modyeo.api.columncode.domain.entity.ColumnCode;
import com.co.kr.modyeo.api.columncode.repository.custom.ColumnCodeCustomRepository;
import com.co.kr.modyeo.common.support.Querydsl4RepositorySupport;
import com.querydsl.core.types.dsl.BooleanExpression;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.util.StringUtils;

import static com.co.kr.modyeo.api.columncode.domain.entity.QColumnCode.columnCode;

public class ColumnCodeRepositoryImpl extends Querydsl4RepositorySupport implements ColumnCodeCustomRepository {

    public ColumnCodeRepositoryImpl() {
        super(ColumnCode.class);
    }

    @Override
    public Page<ColumnCode> searchColumnCode(ColumnCodeSearch columnCodeSearch, Pageable pageRequest) {
        return applyPagination(pageRequest,
                contentsQuery -> selectFrom(columnCode)
                        .where(nameEq(columnCodeSearch.getName()),
                                codeEq(columnCodeSearch.getCode())),
                countQuery -> selectFrom(columnCode)
                        .where(nameEq(columnCodeSearch.getName()),
                                codeEq(columnCodeSearch.getCode())));
    }

    private BooleanExpression codeEq(String code) {
        return StringUtils.hasText(code) ? columnCode.code.eq(code) : null;
    }

    private BooleanExpression nameEq(String name) {
        return StringUtils.hasText(name) ? columnCode.name.eq(name) : null;
    }
}
