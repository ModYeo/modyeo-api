package com.co.kr.modyeo.api.advertisement.service.impl;

import com.co.kr.modyeo.api.advertisement.domain.entity.Advertisement;
import com.co.kr.modyeo.api.advertisement.domain.request.AdvertisementCreateRequest;
import com.co.kr.modyeo.api.advertisement.domain.request.AdvertisementSearch;
import com.co.kr.modyeo.api.advertisement.domain.request.AdvertisementUpdateRequest;
import com.co.kr.modyeo.api.advertisement.domain.response.AdvertisementDetail;
import com.co.kr.modyeo.api.advertisement.domain.response.AdvertisementResponse;
import com.co.kr.modyeo.api.advertisement.repository.AdvertisementRepository;
import com.co.kr.modyeo.api.advertisement.service.AdvertisementService;
import com.co.kr.modyeo.common.enumerate.Yn;
import com.co.kr.modyeo.common.exception.ApiException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
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
    public Long createAdvertisement(AdvertisementCreateRequest advertisementCreateRequest) {
        Advertisement advertisement = AdvertisementCreateRequest.toEntity(advertisementCreateRequest);
        return advertisementRepository.save(advertisement).getId();
    }

    @Override
    public List<AdvertisementResponse> getAdvertisements(AdvertisementSearch advertisementSearch) {
        PageRequest pageRequest = PageRequest.of(advertisementSearch.getOffset(), advertisementSearch.getLimit(), advertisementSearch.getDirection(), advertisementSearch.getOrderBy());
        return advertisementRepository.searchAdvertisement(advertisementSearch,pageRequest)
                .stream().map(AdvertisementResponse::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public AdvertisementDetail getAdvertisement(Long id) {
        return AdvertisementDetail.toDto(advertisementRepository.findById(id).orElseThrow(
                () -> ApiException.builder()
                        .status(HttpStatus.BAD_REQUEST)
                        .errorCode("NOT_FOUND_AD")
                        .errorMessage("광고를 찾지 못했습니다.")
                        .build()));
    }

    @Override
    @Transactional
    public Long updateAdvertisement(AdvertisementUpdateRequest advertisementUpdateRequest) {
        Advertisement advertisement = advertisementRepository.findById(advertisementUpdateRequest.getId()).orElseThrow(
                () -> ApiException.builder()
                        .status(HttpStatus.BAD_REQUEST)
                        .errorCode("NOT_FOUND_AD")
                        .errorMessage("광고를 찾지 못했습니다.")
                        .build());

        advertisement.updateBuilder()
                .advertisementName(advertisementUpdateRequest.getAdvertisementName())
                .advertisementType(advertisementUpdateRequest.getAdvertisementType())
                .urlLink(advertisementUpdateRequest.getUrlLink())
                .imagePath(advertisement.getImagePath())
                .build();

        return advertisement.getId();
    }

    @Override
    @Transactional
    public void deleteAdvertisement(Long id) {
        Advertisement advertisement = advertisementRepository.findById(id).orElseThrow(
                () -> ApiException.builder()
                        .status(HttpStatus.BAD_REQUEST)
                        .errorCode("NOT_FOUND_AD")
                        .errorMessage("광고를 찾지 못했습니다.")
                        .build());

        advertisement.deleteAdvertisement();
    }
}
