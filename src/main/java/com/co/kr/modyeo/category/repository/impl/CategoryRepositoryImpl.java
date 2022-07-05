package com.co.kr.modyeo.category.repository.impl;

import com.co.kr.modyeo.category.domain.dto.search.CategorySearch;
import com.co.kr.modyeo.category.domain.entity.Category;
import com.co.kr.modyeo.category.repository.custom.CategoryCustomRepository;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;

import javax.persistence.EntityManager;
import java.util.List;

import static com.co.kr.modyeo.member.domain.entity.QCategory.category;

public class CategoryRepositoryImpl implements CategoryCustomRepository {
    private final JPAQueryFactory queryFactory;

    public CategoryRepositoryImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public List<Category> searchCategory(CategorySearch categorySearch) {
        return queryFactory
                .selectFrom(category)
                .where(
                        categoryNameEq(categorySearch.getName())
                ).fetch();
    }

    private BooleanExpression categoryIdEq(Long id) {
        return id != null ? category.id.eq(id) : null;
    }

    private BooleanExpression categoryNameEq(String name) {
        return name != null ? category.name.eq(name) : null;
    }
}
