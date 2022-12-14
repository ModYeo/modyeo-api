package com.co.kr.modyeo.api.category.domain.dto.search;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CategorySearch {
    private String name;

    @Builder
    public CategorySearch(String name) {
        this.name = name;
    }
}
