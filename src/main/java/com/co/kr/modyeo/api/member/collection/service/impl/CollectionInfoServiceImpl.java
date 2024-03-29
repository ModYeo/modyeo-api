package com.co.kr.modyeo.api.member.collection.service.impl;

import com.co.kr.modyeo.api.member.collection.domain.dto.request.CollectionInfoRequest;
import com.co.kr.modyeo.api.member.collection.domain.dto.response.CollectionInfoResponse;
import com.co.kr.modyeo.api.member.collection.domain.dto.search.CollectionInfoSearch;
import com.co.kr.modyeo.api.member.collection.domain.entity.CollectionInfo;
import com.co.kr.modyeo.api.member.collection.repository.CollectionInfoRepository;
import com.co.kr.modyeo.api.member.collection.service.CollectionInfoService;
import com.co.kr.modyeo.api.member.domain.entity.link.MemberCollectionInfo;
import com.co.kr.modyeo.api.member.repository.MemberCollectionInfoRepository;
import com.co.kr.modyeo.common.enumerate.Yn;
import com.co.kr.modyeo.common.exception.ApiException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CollectionInfoServiceImpl implements CollectionInfoService {

    private final CollectionInfoRepository collectionInfoRepository;

    private final MemberCollectionInfoRepository memberCollectionInfoRepository;

    @Override
    public List<CollectionInfoResponse> getCollectionInfos() {
        return collectionInfoRepository.findByUseYn(Yn.Y).stream()
                .map(CollectionInfoResponse::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public CollectionInfoResponse getCollectionInfo(Long collectionInfoId) {
        return CollectionInfoResponse.toDto(collectionInfoRepository.findById(collectionInfoId).orElseThrow(
                () -> ApiException.builder()
                        .status(HttpStatus.BAD_REQUEST)
                        .errorMessage("찾을 수 없는 수집정보 입니다.")
                        .errorCode("NOT_FOUND_COLLECTION_INFO")
                        .build()));
    }

    @Override
    @Transactional
    public Long createCollectionInfo(CollectionInfoRequest collectionInfoRequest) {
        CollectionInfo collectionInfo = CollectionInfoRequest.toEntity(collectionInfoRequest);
        return collectionInfoRepository.save(collectionInfo).getId();
    }

    @Override
    @Transactional
    public Long updateCollectionInfo(CollectionInfoRequest collectionInfoRequest) {
        CollectionInfo collectionInfo = collectionInfoRepository.findById(collectionInfoRequest.getCollectionInfoId()).orElseThrow(
                () -> ApiException.builder()
                        .status(HttpStatus.BAD_REQUEST)
                        .errorMessage("찾을 수 없는 수집정보 입니다.")
                        .errorCode("NOT_FOUND_COLLECTION_INFO")
                        .build());

        collectionInfo.updateCollectionInfoBuilder()
                .name(collectionInfoRequest.getCollectionInfoName())
                .description(collectionInfoRequest.getDescription())
                .useYn(collectionInfoRequest.getUseYn())
                .build();
        return collectionInfo.getId();
    }

    @Override
    @Transactional
    public void deleteCollectionInfo(Long collectionInfoId) {
        CollectionInfo collectionInfo = collectionInfoRepository.findById(collectionInfoId).orElseThrow(
                () -> ApiException.builder()
                        .status(HttpStatus.BAD_REQUEST)
                        .errorMessage("찾을 수 없는 수집정보 입니다.")
                        .errorCode("NOT_FOUND_COLLECTION_INFO")
                        .build());

        memberCollectionInfoRepository.deleteByCollectionInfo(collectionInfo);
        collectionInfoRepository.delete(collectionInfo);
    }
}
