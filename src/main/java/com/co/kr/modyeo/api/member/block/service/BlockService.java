package com.co.kr.modyeo.api.member.block.service;

import com.co.kr.modyeo.api.member.block.domain.request.BlockCreateRequest;

public interface BlockService {
    Long createBlock(BlockCreateRequest blockCreateRequest);
}
