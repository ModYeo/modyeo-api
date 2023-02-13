package com.co.kr.modyeo.api.member.collection.controller;

import com.co.kr.modyeo.api.member.collection.domain.dto.request.CollectionInfoRequest;
import com.co.kr.modyeo.api.member.collection.domain.dto.response.CollectionInfoResponse;
import com.co.kr.modyeo.api.member.collection.service.CollectionInfoService;
import com.co.kr.modyeo.common.result.JsonResultData;
import com.co.kr.modyeo.common.result.ResponseHandler;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/collection-info")
@RequiredArgsConstructor
public class CollectionInfoApiController {

    private final CollectionInfoService collectionInfoService;

    @ApiOperation(value = "수집정보 목록 조회 API(어드민)")
    @GetMapping("")
    public ResponseEntity<?> getCollectionInfos(){
        List<CollectionInfoResponse> collectionInfoResponseList = collectionInfoService.getCollectionInfos();
        return ResponseHandler.generate()
                .status(HttpStatus.OK)
                .data(collectionInfoResponseList)
                .build();
    }

    @ApiOperation(value = "수집정보 상세 조회 API(어드민)")
    @GetMapping("/{collection_info_id}")
    public ResponseEntity<?> getCollectionInfo(@PathVariable(value = "collection_info_id")Long collectionInfoId){
        CollectionInfoResponse collectionInfo = collectionInfoService.getCollectionInfo(collectionInfoId);
        return ResponseHandler.generate()
                .status(HttpStatus.OK)
                .data(collectionInfo)
                .build();
    }

    @ApiOperation(value = "수집정보 생성 API(어드민)")
    @PostMapping("")
    public ResponseEntity<?> createCollectionInfo(@RequestBody CollectionInfoRequest collectionInfoRequest){
        Long collectionInfoId = collectionInfoService.createCollectionInfo(collectionInfoRequest);
        return ResponseHandler.generate()
                .status(HttpStatus.CREATED)
                .data(collectionInfoId)
                .build();
    }

    @ApiOperation(value = "수집정보 수정 API(어드민)")
    @PatchMapping("")
    public ResponseEntity<?> updateCollectionInfo(@RequestBody CollectionInfoRequest collectionInfoRequest){
        Long collectionInfoId = collectionInfoService.updateCollectionInfo(collectionInfoRequest);
        return ResponseHandler.generate()
                .status(HttpStatus.OK)
                .data(collectionInfoId)
                .build();
    }

    @ApiOperation(value = "수집정보 삭제 API(어드민)")
    @DeleteMapping("/{collection_info_id}")
    public ResponseEntity<?> deleteCollectionInfo(@PathVariable(value = "collection_info_id")Long collectionInfoId){
        collectionInfoService.deleteCollectionInfo(collectionInfoId);
        return ResponseHandler.generate()
                .status(HttpStatus.NO_CONTENT)
                .data(null)
                .build();
    }
}
