package com.co.kr.modyeo.api.block.controller;

import com.co.kr.modyeo.api.block.domain.request.BlockCreateRequest;
import com.co.kr.modyeo.api.block.domain.request.BlockSearch;
import com.co.kr.modyeo.api.block.domain.request.BlockUpdateRequest;
import com.co.kr.modyeo.api.block.domain.response.BlockDetail;
import com.co.kr.modyeo.api.block.domain.response.BlockResponse;
import com.co.kr.modyeo.api.block.service.BlockService;
import com.co.kr.modyeo.common.result.ResponseHandler;
import com.co.kr.modyeo.common.util.SecurityUtil;
import io.swagger.annotations.ApiOperation;
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

    @ApiOperation(value="차단 생성 API")
    @PostMapping("")
    public ResponseEntity<?> createBlock(@RequestBody BlockCreateRequest blockCreateRequest){
        Long blockId = blockService.createBlock(blockCreateRequest);
        return ResponseHandler.generate()
                .data(blockId)
                .status(HttpStatus.CREATED)
                .build();
    }

    @ApiOperation(value="차단 상세 조회 API")
    @GetMapping("/{block_id}")
    public ResponseEntity<?> getBlock(@PathVariable(value = "block_id")Long blockId){
        BlockDetail block = blockService.getBlock(blockId);
        return ResponseHandler.generate()
                .data(block)
                .status(HttpStatus.OK)
                .build();
    }

    @ApiOperation(value="차단 목록 조회 API")
    @GetMapping("")
    public ResponseEntity<?> getBlocks(BlockSearch blockSearch){
        Long memberId = SecurityUtil.getCurrentMemberId();
        blockSearch.setMemberId(memberId);
        List<BlockResponse> blocks = blockService.getBlocks(blockSearch);
        return ResponseHandler.generate()
                .data(blocks)
                .status(HttpStatus.OK)
                .build();
    }

    @ApiOperation(value="차단 수정 API")
    @PatchMapping("")
    public ResponseEntity<?> updateBlock(@RequestBody BlockUpdateRequest blockUpdateRequest){
        Long blockId = blockService.updateBlock(blockUpdateRequest);
        return ResponseHandler.generate()
                .data(blockId)
                .status(HttpStatus.OK)
                .build();
    }

    @ApiOperation(value="차단 삭제 API")
    @DeleteMapping("/{block_id}")
    public ResponseEntity<?> deleteBlock(@PathVariable(value = "block_id")Long blockId){
        blockService.deleteBlock(blockId);
        return ResponseHandler.generate()
                .data(blockId)
                .status(HttpStatus.OK)
                .build();
    }
}
