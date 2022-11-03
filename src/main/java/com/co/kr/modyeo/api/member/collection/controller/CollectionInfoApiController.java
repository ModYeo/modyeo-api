package com.co.kr.modyeo.api.member.collection.controller;

import com.co.kr.modyeo.api.member.collection.domain.dto.request.CollectionInfoRequest;
import com.co.kr.modyeo.api.member.collection.domain.dto.response.CollectionInfoResponse;
import com.co.kr.modyeo.api.member.collection.service.CollectionInfoService;
import com.co.kr.modyeo.common.result.JsonResultData;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/collection-info")
@RequiredArgsConstructor
public class CollectionInfoApiController {

    private final CollectionInfoService collectionInfoService;

    @GetMapping("")
    public ResponseEntity<?> getCollectionInfos(){
        List<CollectionInfoResponse> collectionInfoResponseList = collectionInfoService.getCollectionInfos();
        return ResponseEntity.ok(JsonResultData.successResultBuilder()
                .data(collectionInfoResponseList)
                .build());
    }

    @GetMapping("/{collection_info_id}")
    public ResponseEntity<?> getCollectionInfo(@PathVariable(value = "collection_info_id")Long collectionInfoId){
        CollectionInfoResponse collectionInfo = collectionInfoService.getCollectionInfo(collectionInfoId);
        return ResponseEntity.ok(JsonResultData.successResultBuilder()
                .data(collectionInfo)
                .build());
    }

    @PostMapping("")
    public ResponseEntity<?> createCollectionInfo(@RequestBody CollectionInfoRequest collectionInfoRequest){
        Long collectionInfoId = collectionInfoService.createCollectionInfo(collectionInfoRequest);
        return ResponseEntity.ok(JsonResultData.successResultBuilder()
                .data(collectionInfoId)
                .build());
    }

    @PatchMapping("")
    public ResponseEntity<?> updateCollectionInfo(@RequestBody CollectionInfoRequest collectionInfoRequest){
        Long collectionInfoId = collectionInfoService.updateCollectionInfo(collectionInfoRequest);
        return ResponseEntity.ok(JsonResultData.successResultBuilder()
                .data(collectionInfoId)
                .build());
    }

    @DeleteMapping("/{collection_info_id}")
    public ResponseEntity<?> deleteCollectionInfo(@PathVariable(value = "collection_info_id")Long collectionInfoId){
        collectionInfoService.deleteCollectionInfo(collectionInfoId);
        return ResponseEntity.ok(JsonResultData.successResultBuilder()
                .data(null)
                .build());
    }
}
