package com.co.kr.modyeo.api.advertisement.service;

import com.co.kr.modyeo.api.advertisement.domain.dto.request.AdvertisementCreateRequest;

public interface AdvertisementService {
    void createAdvertisement(AdvertisementCreateRequest advertisementCreateRequest);
}
