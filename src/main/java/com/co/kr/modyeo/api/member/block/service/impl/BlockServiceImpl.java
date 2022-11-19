package com.co.kr.modyeo.api.member.block.service.impl;

import com.co.kr.modyeo.api.member.block.domain.entity.Block;
import com.co.kr.modyeo.api.member.block.domain.request.BlockCreateRequest;
import com.co.kr.modyeo.api.member.block.repository.BlockRepository;
import com.co.kr.modyeo.api.member.block.service.BlockService;
import lombok.RequiredArgsConstructor;
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
}
