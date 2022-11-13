package com.co.kr.modyeo.api.advertisement.controller;

import com.co.kr.modyeo.api.advertisement.domain.enumerate.AdvertisementType;
import com.co.kr.modyeo.api.advertisement.domain.request.AdvertisementCreateRequest;
import com.co.kr.modyeo.api.advertisement.domain.request.AdvertisementUpdateRequest;
import com.co.kr.modyeo.api.advertisement.domain.response.AdvertisementDetail;
import com.co.kr.modyeo.api.advertisement.domain.response.AdvertisementResponse;
import com.co.kr.modyeo.api.advertisement.service.AdvertisementService;
import com.co.kr.modyeo.common.result.ResponseHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
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
        Long advertisementId = advertisementService.createAdvertisement(advertisementCreateRequest);
        return ResponseHandler.generate()
                .data(advertisementId)
                .status(HttpStatus.OK)
                .build();
    }

    @GetMapping("")
    public ResponseEntity<?> getAdvertisements(
            @RequestParam(value = "type",name = "type",required = true)AdvertisementType advertisementType){
        List<AdvertisementResponse> advertisementResponseList = advertisementService.getAdvertisements(advertisementType);
        return ResponseHandler.generate()
                .data(advertisementResponseList)
                .status(HttpStatus.OK)
                .build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getAdvertisement(@PathVariable(value = "id") Long id){
        AdvertisementDetail advertisementDetail = advertisementService.getAdvertisement(id);
        return ResponseHandler.generate()
                .data(advertisementDetail)
                .status(HttpStatus.OK)
                .build();
    }

    @PatchMapping("")
    public ResponseEntity<?> updateAdvertisement(@RequestBody AdvertisementUpdateRequest advertisementUpdateRequest){
        Long advertisementId = advertisementService.updateAdvertisement(advertisementUpdateRequest);
        return ResponseHandler.generate()
                .data(advertisementId)
                .status(HttpStatus.OK)
                .build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteAdvertisement(@PathVariable(value = "id") Long id){
        advertisementService.deleteAdvertisement(id);
        return ResponseHandler.generate()
                .data(null)
                .status(HttpStatus.OK)
                .build();
    }
}
