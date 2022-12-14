package com.co.kr.modyeo.api.block.domain.request;

import com.co.kr.modyeo.api.block.domain.entity.Block;
import com.co.kr.modyeo.common.enumerate.Yn;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class BlockUpdateRequest {

    private Long blockId;

    private Yn isEnable;
}
