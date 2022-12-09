package com.co.kr.modyeo.api.advertisement.repository.custom;

import com.co.kr.modyeo.api.advertisement.domain.entity.Advertisement;
import com.co.kr.modyeo.api.advertisement.domain.request.AdvertisementSearch;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface AdvertisementCustomRepository {
    Page<Advertisement> searchAdvertisement(AdvertisementSearch advertisementSearch, Pageable pageable);
}
