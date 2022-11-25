package com.co.kr.modyeo.api.block.service;

import com.co.kr.modyeo.api.block.domain.request.BlockCreateRequest;
import com.co.kr.modyeo.api.block.domain.request.BlockSearch;
import com.co.kr.modyeo.api.block.domain.response.BlockDetail;
import com.co.kr.modyeo.api.block.domain.response.BlockResponse;

import java.util.List;

public interface BlockService {
    Long createBlock(BlockCreateRequest blockCreateRequest);

    BlockDetail getBlock(Long blockId);

    List<BlockResponse> getBlocks(BlockSearch blockSearch);
}
