package com.co.kr.modyeo.api.block.service.impl;

import com.co.kr.modyeo.api.block.domain.request.BlockCreateRequest;
import com.co.kr.modyeo.api.block.domain.request.BlockSearch;
import com.co.kr.modyeo.api.block.domain.request.BlockUpdateRequest;
import com.co.kr.modyeo.api.block.domain.response.BlockDetail;
import com.co.kr.modyeo.api.block.domain.entity.Block;
import com.co.kr.modyeo.api.block.domain.response.BlockResponse;
import com.co.kr.modyeo.api.block.repository.BlockRepository;
import com.co.kr.modyeo.api.block.service.BlockService;
import com.co.kr.modyeo.common.exception.ApiException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BlockServiceImpl implements BlockService {

    private final BlockRepository blockRepository;
    @Override
    public Long createBlock(BlockCreateRequest blockCreateRequest) {
        Block block = BlockCreateRequest.toEntity(blockCreateRequest);
        return blockRepository.save(block).getId();
    }

    @Override
    public BlockDetail getBlock(Long blockId) {
        Block block = blockRepository.findById(blockId)
                .orElseThrow(() -> ApiException.builder()
                        .status(HttpStatus.BAD_REQUEST)
                        .errorCode("NOT_FOUND_BLOCK")
                        .errorMessage("차단된 컨텐츠를 찾지 못했습니다.")
                        .build());

        return BlockDetail.toDto(block);
    }

    @Override
    public List<BlockResponse> getBlocks(BlockSearch blockSearch) {
       return null;
    }

    @Override
    public Long updateBlock(BlockUpdateRequest blockUpdateRequest) {
        Block block = blockRepository.findById(blockUpdateRequest.getBlockId())
                .orElseThrow(() -> ApiException.builder()
                        .status(HttpStatus.BAD_REQUEST)
                        .errorCode("NOT_FOUND_BLOCK")
                        .errorMessage("차단된 컨텐츠를 찾지 못했습니다.")
                        .build());

        Block.changeBlock(block, blockUpdateRequest.getIsEnable());
        return block.getId();
    }
}
