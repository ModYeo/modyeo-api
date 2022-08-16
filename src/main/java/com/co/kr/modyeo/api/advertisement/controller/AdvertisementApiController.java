package com.co.kr.modyeo.api.advertisement.controller;

import com.co.kr.modyeo.api.advertisement.domain.dto.request.AdvertisementCreateRequest;
import com.co.kr.modyeo.api.advertisement.service.AdvertisementService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/advertisement")
@RequiredArgsConstructor
public class AdvertisementApiController {

    private final AdvertisementService advertisementService;

    @PostMapping("")
    public ResponseEntity<?> createAdvertisement(@RequestBody AdvertisementCreateRequest advertisementCreateRequest){
        advertisementService.createAdvertisement(advertisementCreateRequest);
        return ResponseEntity.ok(null);
    }
}
