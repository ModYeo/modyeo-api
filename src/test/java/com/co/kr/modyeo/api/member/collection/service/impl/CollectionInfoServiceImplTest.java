package com.co.kr.modyeo.api.member.collection.service.impl;

import com.co.kr.modyeo.api.member.collection.domain.entity.CollectionInfo;
import com.co.kr.modyeo.api.member.collection.repository.CollectionInfoRepository;
import com.co.kr.modyeo.api.member.collection.service.CollectionInfoService;
import com.co.kr.modyeo.api.member.repository.MemberCollectionInfoRepository;
import com.co.kr.modyeo.common.enumerate.Yn;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

class CollectionInfoServiceImplTest {

    private CollectionInfoService collectionInfoService;

    @Mock
    private CollectionInfoRepository collectionInfoRepository;

    @Mock
    private MemberCollectionInfoRepository memberCollectionInfoRepository;

    CollectionInfo FIXTURE_COL_INFO_01 = CollectionInfo.of()
            .id(1L)
            .name("test")
            .description("test description")
            .useYn(Yn.Y)
            .build();

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        collectionInfoService = new CollectionInfoServiceImpl(collectionInfoRepository, memberCollectionInfoRepository);
    }

    @Test
    void deleteCollectionInfo() {
        given(collectionInfoRepository.findById(any())).willReturn(Optional.of(FIXTURE_COL_INFO_01));

        collectionInfoService.deleteCollectionInfo(1L);

        then(collectionInfoRepository).should().delete(any());
        then(memberCollectionInfoRepository).should().deleteByCollectionInfo(any());
    }
}