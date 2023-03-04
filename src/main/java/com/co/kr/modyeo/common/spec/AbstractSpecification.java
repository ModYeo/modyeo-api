package com.co.kr.modyeo.common.spec;

import com.co.kr.modyeo.common.exception.ApiException;

public abstract class AbstractSpecification<T> implements Specification<T>{
    @Override
    public abstract boolean isSatisfiedBy(T t);

    public abstract void check(T t) throws ApiException;

    @Override
    public Specification<T> and(Specification<T> specification){
        return new AndSpecification<T>(this, specification);
    }
}
