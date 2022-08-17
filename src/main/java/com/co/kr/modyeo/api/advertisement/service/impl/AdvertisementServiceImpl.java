package com.co.kr.modyeo.api.advertisement.service.impl;

import com.co.kr.modyeo.api.advertisement.domain.enumerate.AdvertisementType;
import com.co.kr.modyeo.api.advertisement.domain.request.AdvertisementCreateRequest;
import com.co.kr.modyeo.api.advertisement.domain.entity.Advertisement;
import com.co.kr.modyeo.api.advertisement.domain.response.AdvertisementResponse;
import com.co.kr.modyeo.api.advertisement.repository.AdvertisementRepository;
import com.co.kr.modyeo.api.advertisement.service.AdvertisementService;
import com.co.kr.modyeo.common.enumerate.Yn;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

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

    @Override
    public List<AdvertisementResponse> getAdvertisement(AdvertisementType advertisementType) {
        return advertisementRepository.findByUseYnAndType(Yn.Y,advertisementType)
                .stream().map(AdvertisementResponse::toDto)
                .collect(Collectors.toList());
    }
}
