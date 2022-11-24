package com.co.kr.modyeo.api.block.controller;

import com.co.kr.modyeo.api.block.domain.request.BlockCreateRequest;
import com.co.kr.modyeo.api.block.domain.request.BlockSearch;
import com.co.kr.modyeo.api.block.domain.response.BlockDetail;
import com.co.kr.modyeo.api.block.domain.response.BlockResponse;
import com.co.kr.modyeo.api.block.service.BlockService;
import com.co.kr.modyeo.common.result.ResponseHandler;
import com.co.kr.modyeo.common.util.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping("/")
    public ResponseEntity<?> getBlocks(BlockSearch blockSearch){
        String email = SecurityUtil.getCurrentEmail();
        blockSearch.setEmail(email);
        List<BlockResponse> blocks = blockService.getBlocks(blockSearch);
        return ResponseHandler.generate()
                .data(blocks)
                .status(HttpStatus.OK)
                .build();
    }
}
