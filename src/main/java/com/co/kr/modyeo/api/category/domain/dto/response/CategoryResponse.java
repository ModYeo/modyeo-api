package com.co.kr.modyeo.api.category.domain.dto.response;

import com.co.kr.modyeo.api.category.domain.entity.Category;
import lombok.Builder;
import lombok.Getter;

@Getter
public class CategoryResponse {

    private Long id;

    private String name;

    @Builder(builderClassName = "of", builderMethodName = "of")
    public CategoryResponse(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public static CategoryResponse toRes(Category category) {
        return CategoryResponse.of()
                .id(category.getId())
                .name(category.getName())
                .build();
    }
}
