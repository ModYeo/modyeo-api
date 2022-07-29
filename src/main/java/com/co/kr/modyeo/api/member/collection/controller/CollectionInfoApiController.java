package com.co.kr.modyeo.api.member.collection.controller;

import com.co.kr.modyeo.api.member.collection.domain.dto.response.CollectionInfoResponse;
import com.co.kr.modyeo.api.member.collection.domain.dto.search.CollectionInfoSearch;
import com.co.kr.modyeo.api.member.collection.service.CollectionInfoService;
import com.co.kr.modyeo.common.result.JsonResultData;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/collection-info")
@RequiredArgsConstructor
public class CollectionInfoApiController {

    private final CollectionInfoService collectionInfoService;

    @GetMapping("")
    public ResponseEntity<?> getCollectionInfos(CollectionInfoSearch collectionInfoSearch){
        return ResponseEntity.ok(JsonResultData.successResultBuilder()
                .data(null)
                .build());
    }

    @GetMapping("/{collection_info_id}")
    public ResponseEntity<?> getCollectionInfo(@PathVariable(value = "collection_info_id")Long collectionInfoId){
        CollectionInfoResponse collectionInfo = collectionInfoService.getCollectionInfo(collectionInfoId);
        return ResponseEntity.ok(JsonResultData.successResultBuilder()
                .data(collectionInfo)
                .build());
    }

}
