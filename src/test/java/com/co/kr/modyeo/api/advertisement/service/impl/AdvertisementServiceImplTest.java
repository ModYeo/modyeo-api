package com.co.kr.modyeo.api.advertisement.service.impl;

import com.co.kr.modyeo.api.advertisement.repository.AdvertisementRepository;
import com.co.kr.modyeo.api.advertisement.service.AdvertisementService;
import com.co.kr.modyeo.api.bbs.service.impl.BoardServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;

class AdvertisementServiceImplTest {

    private AdvertisementService advertisementService;

    @Mock
    private AdvertisementRepository advertisementRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        advertisementService = new AdvertisementServiceImpl(advertisementRepository);
    }

    @Test
    void createAdvertisement() {
        System.out.println(org.hibernate.Version.getVersionString());
    }

    @Test
    void getAdvertisements() {
    }

    @Test
    void getAdvertisement() {
    }

    @Test
    void updateAdvertisement() {
    }

    @Test
    void deleteAdvertisement() {
    }
}