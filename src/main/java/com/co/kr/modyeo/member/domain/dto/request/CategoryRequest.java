package com.co.kr.modyeo.member.domain.dto.request;

import com.co.kr.modyeo.member.domain.entity.Category;
import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.NotNull;

@Getter
public class CategoryRequest {

    @NotNull
    private String name;

    @Builder(builderMethodName = "of",builderClassName = "of")
    public CategoryRequest(String name) {
        this.name = name;
    }

    public Category toEntity(){
        return Category.of()
                .name(this.name)
                .build();
    }
}
