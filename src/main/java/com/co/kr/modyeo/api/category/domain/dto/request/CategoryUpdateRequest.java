package com.co.kr.modyeo.api.category.domain.dto.request;

import com.co.kr.modyeo.common.enumerate.Yn;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CategoryUpdateRequest {

    private Long categoryId;

    private String name;

    private Yn useYn;

    @Builder(builderClassName = "of",builderMethodName = "of")
    public CategoryUpdateRequest(Long categoryId, String name, Yn useYn) {
        this.categoryId = categoryId;
        this.name = name;
        this.useYn = useYn;
    }
}
