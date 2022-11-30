package com.co.kr.modyeo.api.block.repository.custom.impl;

import com.co.kr.modyeo.api.block.domain.entity.Block;
import com.co.kr.modyeo.api.block.domain.request.BlockSearch;
import com.co.kr.modyeo.api.block.domain.response.BlockResponse;
import com.co.kr.modyeo.api.block.repository.custom.BlockCustomRepository;
import com.co.kr.modyeo.common.support.Querydsl4RepositorySupport;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.BooleanExpression;
import org.springframework.util.StringUtils;

import java.util.List;

import static com.co.kr.modyeo.api.block.domain.entity.QBlock.block;

public class BlockRepositoryImpl extends Querydsl4RepositorySupport implements BlockCustomRepository {

    public BlockRepositoryImpl() {
        super(Block.class);
    }

    @Override
    public List<Block> searchBlocks(BlockSearch blockSearch) {
        return select(block)
                .from(block)
                .where(createdByEq(blockSearch.getEmail()))
                .fetch();
    }

    private BooleanExpression createdByEq(String email) {
        return StringUtils.hasText(email) ? block.createdBy.eq(email) : null;
    }
}
