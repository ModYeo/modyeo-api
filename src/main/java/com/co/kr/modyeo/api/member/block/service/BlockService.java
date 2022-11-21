package com.co.kr.modyeo.api.member.block.service;

import com.co.kr.modyeo.api.member.block.domain.request.BlockCreateRequest;
import com.co.kr.modyeo.api.member.block.domain.response.BlockDetail;

public interface BlockService {
    Long createBlock(BlockCreateRequest blockCreateRequest);

    BlockDetail getBlock(Long blockId);
}
