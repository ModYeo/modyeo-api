package com.co.kr.modyeo.api.category.domain.dto.request;

import com.co.kr.modyeo.common.enumerate.Yn;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CategoryUpdateRequest {

    private Long categoryId;

    private String name;

    private Yn useYn;

}
