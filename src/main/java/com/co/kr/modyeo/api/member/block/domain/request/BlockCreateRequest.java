package com.co.kr.modyeo.api.member.block.domain.request;

import com.co.kr.modyeo.api.member.block.domain.entity.Block;
import com.co.kr.modyeo.api.member.block.domain.entity.BlockType;
import com.co.kr.modyeo.api.member.domain.entity.Member;
import com.co.kr.modyeo.common.enumerate.Yn;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
public class BlockCreateRequest {

    private Long targetId;

    private BlockType type;

    private Yn isEnable;

    public static Block toEntity(BlockCreateRequest blockCreateRequest){
        return Block.create()
                .targetId(blockCreateRequest.getTargetId())
                .type(blockCreateRequest.getType())
                .isEnable(blockCreateRequest.getIsEnable())
                .build();
    }
}
