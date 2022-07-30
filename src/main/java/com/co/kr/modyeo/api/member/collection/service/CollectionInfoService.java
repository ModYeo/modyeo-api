package com.co.kr.modyeo.api.member.collection.service;

import com.co.kr.modyeo.api.member.collection.domain.dto.request.CollectionInfoRequest;
import com.co.kr.modyeo.api.member.collection.domain.dto.response.CollectionInfoResponse;
import com.co.kr.modyeo.api.member.collection.domain.dto.search.CollectionInfoSearch;

import java.util.List;

public interface CollectionInfoService {

    List<CollectionInfoResponse> getCollectionInfos();

    CollectionInfoResponse getCollectionInfo(Long collectionInfoId);

    void createCollectionInfo(CollectionInfoRequest collectionInfoRequest);

    void updateCollectionInfo(CollectionInfoRequest collectionInfoRequest);

    void deleteCollectionInfo(Long collectionInfoId);
}
