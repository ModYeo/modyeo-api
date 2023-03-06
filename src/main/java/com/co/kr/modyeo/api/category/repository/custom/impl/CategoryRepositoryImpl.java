package com.co.kr.modyeo.api.category.repository.custom.impl;

import com.co.kr.modyeo.api.category.domain.dto.search.CategorySearch;
import com.co.kr.modyeo.api.category.domain.entity.Category;
import com.co.kr.modyeo.api.category.repository.custom.CategoryCustomRepository;
import com.co.kr.modyeo.common.enumerate.Yn;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import javax.persistence.EntityManager;
import java.util.List;

import static com.co.kr.modyeo.api.category.domain.entity.QCategory.category;
import static com.co.kr.modyeo.api.member.domain.entity.QMember.member;
import static com.co.kr.modyeo.api.member.domain.entity.link.QMemberCategory.memberCategory;

@Repository
public class CategoryRepositoryImpl implements CategoryCustomRepository {
    private final JPAQueryFactory queryFactory;

    public CategoryRepositoryImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public List<Category> searchCategory(CategorySearch categorySearch) {
        return queryFactory
                .selectFrom(category)
                .where(categoryNameEq(categorySearch.getName()),
                        category.useYn.eq(Yn.Y))
                .fetch();
    }

    @Override
    public List<Category> findByCategoryIds(List<Long> categoryIds) {
        return queryFactory.select(category)
                .from(category)
                .where(category.id.in(categoryIds))
                .fetch();
    }

    private BooleanExpression memberIdEq(Long memberId) {
        return memberId != null && memberId > 0 ? member.id.eq(memberId) : null;
    }

    private BooleanExpression categoryIdEq(Long id) {
        return id != null ? category.id.eq(id) : null;
    }

    private BooleanExpression categoryNameEq(String name) {
        return StringUtils.hasText(name) ? category.name.eq(name) : null;
    }
}
