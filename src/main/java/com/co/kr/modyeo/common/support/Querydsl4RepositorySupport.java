package com.co.kr.modyeo.common.support;

import com.querydsl.core.types.EntityPath;
import com.querydsl.core.types.Expression;
import com.querydsl.core.types.dsl.PathBuilder;
import com.querydsl.jpa.JPQLQuery;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import io.jsonwebtoken.lang.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.SliceImpl;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.JpaEntityInformationSupport;
import org.springframework.data.jpa.repository.support.Querydsl;
import org.springframework.data.querydsl.SimpleEntityPathResolver;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.function.Function;

@Repository
public abstract class Querydsl4RepositorySupport {

    private final Class domainClass;

    private Querydsl querydsl;

    private EntityManager entityManager;

    private JPAQueryFactory queryFactory;

    private boolean hasNext;

    public Querydsl4RepositorySupport(Class<?> domainClass){
        Assert.notNull(domainClass,"Domain class must not be null");
        this.domainClass = domainClass;
    }

    @Autowired
    public void setEntityManager(EntityManager entityManager){
        Assert.notNull(entityManager,"EntityManager must not be null");
        JpaEntityInformation entityInformation =
                JpaEntityInformationSupport.getEntityInformation(domainClass, entityManager);
        SimpleEntityPathResolver resolver = SimpleEntityPathResolver.INSTANCE;
        EntityPath path = resolver.createPath(entityInformation.getJavaType());
        this.entityManager = entityManager;
        this.querydsl = new Querydsl(entityManager, new PathBuilder<>(path.getType(), path.getMetadata()));
        this.queryFactory = new JPAQueryFactory(entityManager);
    }

    protected JPAQueryFactory getQueryFactory() {return queryFactory;}

    protected Querydsl getQuerydsl() {return querydsl;}

    protected EntityManager getEntityManager(){return entityManager;}

    protected <T> JPAQuery<T>select(Expression<T> expression){
        return getQueryFactory().select(expression);
    }

    protected <T> JPAQuery<T> selectFrom(EntityPath<T> from){
        return getQueryFactory().selectFrom(from);
    }

    protected <T> Page<T> applyPagination(Pageable pageable, Function<JPAQueryFactory,JPAQuery> contentQuery,
                                          Function<JPAQueryFactory, JPAQuery> countQuery){
        JPAQuery jpaCountQuery = contentQuery.apply(getQueryFactory());
        List<T> content = getQuerydsl().applyPagination(pageable, jpaCountQuery).fetch();
        JPAQuery countResult = countQuery.apply(getQueryFactory());
        return PageableExecutionUtils.getPage(content, pageable, countResult::fetchCount);
    }

    public <T> JPQLQuery<T> applyPagination(Pageable pageable,JPQLQuery<T> query){
        Assert.notNull(pageable, "Pageable must not be null");
        Assert.notNull(query, "Pageable must not be null");

        if (pageable.isUnpaged()){
            return query;
        }

        query.offset(pageable.getOffset());
        query.limit(pageable.getPageSize());

        return getQuerydsl().applySorting(pageable.getSort(),query);
    }

    protected <T>Slice<T> applySlicing(Pageable pageable, Function<JPAQueryFactory,JPAQuery> contentQuery){
        JPAQuery jpaQuery = contentQuery.apply(getQueryFactory());
        jpaQuery.offset(pageable.getOffset());
        jpaQuery.limit(pageable.getPageSize() + 1);

        List<T> content = getQuerydsl().applySorting(pageable.getSort(), jpaQuery).fetch();

        hasNext = false;
        if (content.size() > pageable.getPageSize()){
            content.remove(pageable.getPageSize());
            hasNext = true;
        }

        return new SliceImpl<>(content,pageable,hasNext);
    }
}
