package com.co.kr.modyeo.common.spec;

import com.co.kr.modyeo.common.exception.ApiException;

public class AndSpecification<T> extends AbstractSpecification<T>{

    private Specification<T> spec1;
    private Specification<T> spec2;

    public AndSpecification(final Specification<T> spec1, final Specification<T> spec2) {
        this.spec1 = spec1;
        this.spec2 = spec2;
    }

    @Override
    public boolean isSatisfiedBy(T t) {
        return spec1.isSatisfiedBy(t) && spec2.isSatisfiedBy(t);
    }

    @Override
    public void check(T t) throws ApiException {

    }
}
