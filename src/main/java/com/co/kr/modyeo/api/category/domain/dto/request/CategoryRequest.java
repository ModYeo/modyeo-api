package com.co.kr.modyeo.api.category.domain.dto.request;

import com.co.kr.modyeo.api.category.domain.entity.Category;
import com.co.kr.modyeo.common.enumerate.Yn;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor
public class CategoryRequest {

    private Long id;

    private String name;

    private Yn useYn;


    @Builder(builderMethodName = "of",builderClassName = "of")
    public CategoryRequest(String name,Long id,Yn useYn) {
        this.name = name;
        this.id = id;
        this.useYn = useYn;
    }

    public static Category toEntity(CategoryRequest categoryRequest){
        return Category.of()
                .id(categoryRequest.getId())
                .name(categoryRequest.getName())
                .useYn(categoryRequest.getUseYn())
                .build();
    }

    public static List<Long> getCategoryIdList(List<CategoryRequest> categoryRequests){
        if (!categoryRequests.isEmpty()){
            return categoryRequests.stream().map(CategoryRequest::getId).collect(Collectors.toList());
        }else{
            return new ArrayList<>();
        }
    }
}
