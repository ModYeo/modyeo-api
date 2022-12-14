package com.co.kr.modyeo.api.block.domain.request;

import com.co.kr.modyeo.common.dto.SearchDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class BlockSearch extends SearchDto {

    private String email;
}
