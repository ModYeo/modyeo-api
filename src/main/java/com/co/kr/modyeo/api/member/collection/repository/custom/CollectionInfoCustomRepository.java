package com.co.kr.modyeo.api.member.collection.repository.custom;

import com.co.kr.modyeo.api.member.collection.domain.dto.response.CollectionInfoResponse;
import com.co.kr.modyeo.api.member.collection.domain.dto.search.CollectionInfoSearch;

import java.util.List;

public interface CollectionInfoCustomRepository {
    List<CollectionInfoResponse> searchCollectionInfo(CollectionInfoSearch collectionInfoSearch);
}
