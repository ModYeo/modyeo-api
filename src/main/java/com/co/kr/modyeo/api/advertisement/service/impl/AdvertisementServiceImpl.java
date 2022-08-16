package com.co.kr.modyeo.api.advertisement.service.impl;

import com.co.kr.modyeo.api.advertisement.domain.dto.request.AdvertisementCreateRequest;
import com.co.kr.modyeo.api.advertisement.domain.entity.Advertisement;
import com.co.kr.modyeo.api.advertisement.repository.AdvertisementRepository;
import com.co.kr.modyeo.api.advertisement.service.AdvertisementService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class AdvertisementServiceImpl implements AdvertisementService {

    private final AdvertisementRepository advertisementRepository;

    @Override
    @Transactional
    public void createAdvertisement(AdvertisementCreateRequest advertisementCreateRequest) {
        Advertisement advertisement = AdvertisementCreateRequest.toEntity(advertisementCreateRequest);
        advertisementRepository.save(advertisement);
    }
}
