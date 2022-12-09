package com.co.kr.modyeo.api.advertisement.repository.custom.impl;

import com.co.kr.modyeo.api.advertisement.domain.entity.Advertisement;
import com.co.kr.modyeo.api.advertisement.domain.enumerate.AdvertisementType;
import com.co.kr.modyeo.api.advertisement.domain.request.AdvertisementSearch;
import com.co.kr.modyeo.api.advertisement.repository.custom.AdvertisementCustomRepository;
import com.co.kr.modyeo.common.support.Querydsl4RepositorySupport;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.BooleanExpression;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import static com.co.kr.modyeo.api.advertisement.domain.entity.QAdvertisement.advertisement;

public class AdvertisementRepositoryImpl extends Querydsl4RepositorySupport implements AdvertisementCustomRepository {

    public AdvertisementRepositoryImpl() {
        super(Advertisement.class);
    }

    @Override
    public Page<Advertisement> searchAdvertisement(AdvertisementSearch advertisementSearch, Pageable pageable) {
        return applyPagination(pageable,
                contentQuery -> contentQuery.select(advertisement)
                        .from(advertisement)
                        .where(advertisementTypeEq(advertisementSearch.getAdvertisementType())),
                countQuery -> countQuery.select(advertisement)
                        .from(advertisement)
                        .where(advertisementTypeEq(advertisementSearch.getAdvertisementType())));
    }

    private BooleanExpression advertisementTypeEq(AdvertisementType advertisementType) {
        return advertisementType != null ? advertisement.type.eq(advertisementType) : null;
    }
}
