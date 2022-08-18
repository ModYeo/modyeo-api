package com.co.kr.modyeo.api.advertisement.controller;

import com.co.kr.modyeo.api.advertisement.domain.enumerate.AdvertisementType;
import com.co.kr.modyeo.api.advertisement.domain.request.AdvertisementCreateRequest;
import com.co.kr.modyeo.api.advertisement.domain.response.AdvertisementDetail;
import com.co.kr.modyeo.api.advertisement.domain.response.AdvertisementResponse;
import com.co.kr.modyeo.api.advertisement.service.AdvertisementService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping("")
    public ResponseEntity<?> getAdvertisements(
            @RequestParam(value = "type",name = "type",required = true)AdvertisementType advertisementType){
        List<AdvertisementResponse> advertisementResponseList = advertisementService.getAdvertisements(advertisementType);
        return ResponseEntity.ok(advertisementResponseList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getAdvertisement(@PathVariable(value = "id") Long id){
        AdvertisementDetail advertisementDetail = advertisementService.getAdvertisement(id);
        return ResponseEntity.ok(null);
    }
}
