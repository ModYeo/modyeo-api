package com.co.kr.modyeo.api.block.domain.entity;

import com.co.kr.modyeo.common.entity.BaseEntity;
import com.co.kr.modyeo.common.enumerate.Yn;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@Table(name = "BLOCK")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Block extends BaseEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "block_id")
    private Long id;

    private Long targetId;

    @Column(name = "block_type")
    private BlockType type;

    @Column(name = "is_enable")
    private Yn isEnable;

    @Builder(builderClassName = "of", builderMethodName = "of")
    public Block(Long id, Long targetId, BlockType type, Yn isEnable) {
        this.id = id;
        this.targetId = targetId;
        this.type = type;
        this.isEnable = isEnable;
    }

    @Builder(builderClassName = "create",builderMethodName = "create")
    public static Block createBlock(Long targetId, BlockType type, Yn isEnable) {
        return of()
                .targetId(targetId)
                .type(type)
                .isEnable(isEnable)
                .build();
    }

    public static void changeBlock(Block block, Yn isEnable) {
        block.isEnable = isEnable;
    }
}
