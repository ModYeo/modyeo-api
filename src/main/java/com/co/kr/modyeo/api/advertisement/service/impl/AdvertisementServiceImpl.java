package com.co.kr.modyeo.api.advertisement.service.impl;

import com.co.kr.modyeo.api.advertisement.repository.AdvertisementRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class AdvertisementServiceImpl {

    private final AdvertisementRepository advertisementRepository;
}
