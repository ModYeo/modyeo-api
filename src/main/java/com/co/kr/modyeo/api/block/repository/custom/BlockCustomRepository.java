package com.co.kr.modyeo.api.block.repository.custom;

import com.co.kr.modyeo.api.block.domain.entity.Block;
import com.co.kr.modyeo.api.block.domain.request.BlockSearch;
import com.co.kr.modyeo.api.block.domain.response.BlockResponse;

import java.util.List;

public interface BlockCustomRepository {
    List<Block> searchBlocks(BlockSearch blockSearch);
}
