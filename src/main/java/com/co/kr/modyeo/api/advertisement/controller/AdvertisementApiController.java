package com.co.kr.modyeo.api.advertisement.controller;

import com.co.kr.modyeo.api.advertisement.domain.enumerate.AdvertisementType;
import com.co.kr.modyeo.api.advertisement.domain.request.AdvertisementCreateRequest;
import com.co.kr.modyeo.api.advertisement.domain.request.AdvertisementSearch;
import com.co.kr.modyeo.api.advertisement.domain.request.AdvertisementUpdateRequest;
import com.co.kr.modyeo.api.advertisement.domain.response.AdvertisementDetail;
import com.co.kr.modyeo.api.advertisement.domain.response.AdvertisementResponse;
import com.co.kr.modyeo.api.advertisement.service.AdvertisementService;
import com.co.kr.modyeo.common.result.ResponseHandler;
import io.swagger.annotations.ApiOperation;
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

    @ApiOperation(value="광고 생성 API(어드민)")
    @PostMapping("")
    public ResponseEntity<?> createAdvertisement(@RequestBody AdvertisementCreateRequest advertisementCreateRequest){
        Long advertisementId = advertisementService.createAdvertisement(advertisementCreateRequest);
        return ResponseHandler.generate()
                .data(advertisementId)
                .status(HttpStatus.CREATED)
                .build();
    }

    @ApiOperation(value="광고 조회 API(어드민)")
    @GetMapping("")
    public ResponseEntity<?> getAdvertisements(
            AdvertisementSearch advertisementSearch){
        List<AdvertisementResponse> advertisementResponseList = advertisementService.getAdvertisements(advertisementSearch);
        return ResponseHandler.generate()
                .data(advertisementResponseList)
                .status(HttpStatus.OK)
                .build();
    }

    @ApiOperation(value="광고 상세 조회 API(어드민)")
    @GetMapping("/{id}")
    public ResponseEntity<?> getAdvertisement(@PathVariable(value = "id") Long id){
        AdvertisementDetail advertisementDetail = advertisementService.getAdvertisement(id);
        return ResponseHandler.generate()
                .data(advertisementDetail)
                .status(HttpStatus.OK)
                .build();
    }

    @ApiOperation(value="광고 수정 API(어드민)")
    @PatchMapping("")
    public ResponseEntity<?> updateAdvertisement(@RequestBody AdvertisementUpdateRequest advertisementUpdateRequest){
        Long advertisementId = advertisementService.updateAdvertisement(advertisementUpdateRequest);
        return ResponseHandler.generate()
                .data(advertisementId)
                .status(HttpStatus.OK)
                .build();
    }

    @ApiOperation(value="광고 삭제 API(어드민)")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteAdvertisement(@PathVariable(value = "id") Long id){
        advertisementService.deleteAdvertisement(id);
        return ResponseHandler.generate()
                .status(HttpStatus.NO_CONTENT)
                .data(null)
                .build();
    }
}
