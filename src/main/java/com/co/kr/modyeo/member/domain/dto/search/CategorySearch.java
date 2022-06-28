package com.co.kr.modyeo.member.domain.dto.search;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CategorySearch {
    private Long id;
    private String name;

    @Builder
    public CategorySearch(Long id, String name) {
        this.id = id;
        this.name = name;
    }
}
