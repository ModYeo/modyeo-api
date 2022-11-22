package com.co.kr.modyeo.api.member.block.domain.response;

import com.co.kr.modyeo.api.member.block.domain.entity.Block;
import com.co.kr.modyeo.api.member.block.domain.entity.BlockType;
import com.co.kr.modyeo.common.enumerate.Yn;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class BlockDetail {
    private Long blockId;

    private Long targetId;

    private BlockType type;

    private Yn isEnable;

    private String createdBy;

    private String updatedBy;

    private LocalDateTime createdTime;

    private LocalDateTime updatedTime;

    @Builder(builderClassName = "of",builderMethodName = "of")
    public BlockDetail(Long blockId, Long targetId, BlockType type, Yn isEnable, String createdBy, String updatedBy, LocalDateTime createdTime, LocalDateTime updatedTime) {
        this.blockId = blockId;
        this.targetId = targetId;
        this.type = type;
        this.isEnable = isEnable;
        this.createdBy = createdBy;
        this.updatedBy = updatedBy;
        this.createdTime = createdTime;
        this.updatedTime = updatedTime;
    }

    public static BlockDetail toDto(Block block){
        return of()
                .blockId(block.getId())
                .targetId(block.getTargetId())
                .type(block.getType())
                .isEnable(block.getIsEnable())
                .createdBy(block.getCreatedBy())
                .updatedBy(block.getUpdatedBy())
                .createdTime(block.getCreatedDate())
                .updatedTime(block.getLastModifiedDate())
                .build();
    }
}
