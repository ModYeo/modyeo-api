package com.co.kr.modyeo.api.category.domain.dto.response;

import com.co.kr.modyeo.api.category.domain.entity.Category;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CategoryResponse {

    private Long id;

    private String name;

    private String imagePath;

    @Builder(builderClassName = "of", builderMethodName = "of")
    public CategoryResponse(Long id, String name, String imagePath) {
        this.id = id;
        this.name = name;
        this.imagePath = imagePath;
    }

    public static CategoryResponse toDto(Category category) {
        return CategoryResponse.of()
                .id(category.getId())
                .name(category.getName())
                .imagePath(category.getImagePath())
                .build();
    }
}
