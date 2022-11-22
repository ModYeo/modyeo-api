package com.co.kr.modyeo.api.member.block.controller;

import com.co.kr.modyeo.api.member.block.domain.request.BlockCreateRequest;
import com.co.kr.modyeo.api.member.block.domain.response.BlockDetail;
import com.co.kr.modyeo.api.member.block.service.BlockService;
import com.co.kr.modyeo.common.result.ResponseHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/block")
@RequiredArgsConstructor
public class BlockController {

    private final BlockService blockService;

    @PostMapping("")
    public ResponseEntity<?> createBlock(BlockCreateRequest blockCreateRequest){
        Long blockId = blockService.createBlock(blockCreateRequest);
        return ResponseHandler.generate()
                .data(blockId)
                .status(HttpStatus.CREATED)
                .build();
    }

    @GetMapping("/{block_id}")
    public ResponseEntity<?> getBlock(@PathVariable(value = "block_id")Long blockId){
        BlockDetail block = blockService.getBlock(blockId);
        return ResponseHandler.generate()
                .data(block)
                .status(HttpStatus.OK)
                .build();
    }
}
