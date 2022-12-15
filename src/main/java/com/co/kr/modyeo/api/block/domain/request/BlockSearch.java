package com.co.kr.modyeo.api.block.domain.request;

import com.co.kr.modyeo.common.dto.SearchDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.domain.Sort;

@Getter
@Setter
public class BlockSearch extends SearchDto {

    private Long memberId;

    public BlockSearch(Integer limit, Integer offset, String orderBy, Sort.Direction direction) {
        super(limit, offset, orderBy, direction);
    }
}
