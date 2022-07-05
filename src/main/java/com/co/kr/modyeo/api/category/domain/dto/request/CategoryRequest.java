package com.co.kr.modyeo.api.category.domain.dto.request;

import com.co.kr.modyeo.api.category.domain.entity.Category;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CategoryRequest {

    private Long id;

    private String name;

    @Builder(builderMethodName = "of",builderClassName = "of")
    public CategoryRequest(String name,Long id) {
        this.name = name;
        this.id = id;
    }

    public Category toEntity(){
        return Category.of()
                .id(id)
                .name(this.name)
                .build();
    }
}
