package com.co.kr.modyeo.member.domain.dto.request;

import com.co.kr.modyeo.member.domain.entity.Category;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

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
