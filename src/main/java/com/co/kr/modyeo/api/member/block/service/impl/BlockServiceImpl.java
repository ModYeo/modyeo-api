package com.co.kr.modyeo.api.member.block.service.impl;

import com.co.kr.modyeo.api.member.block.domain.entity.Block;
import com.co.kr.modyeo.api.member.block.domain.request.BlockCreateRequest;
import com.co.kr.modyeo.api.member.block.domain.response.BlockDetail;
import com.co.kr.modyeo.api.member.block.repository.BlockRepository;
import com.co.kr.modyeo.api.member.block.service.BlockService;
import com.co.kr.modyeo.common.exception.ApiException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

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
}
