package com.co.kr.modyeo.api.category.domain.dto.request;

import com.co.kr.modyeo.api.category.domain.entity.Category;
import com.co.kr.modyeo.common.enumerate.Yn;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CategoryCreateRequest {

    private String name;

    private Yn useYn;


    @Builder(builderMethodName = "of", builderClassName = "of")
    public CategoryCreateRequest(String name, Yn useYn) {
        this.name = name;
        this.useYn = useYn;
    }

    public static Category createCategory(CategoryCreateRequest categoryCreateRequest) {
        return Category.createCategoryBuilder()
                .name(categoryCreateRequest.getName())
                .build();
    }
}
