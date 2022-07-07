package com.co.kr.modyeo.api.category.domain.dto.response;

import com.co.kr.modyeo.api.category.domain.entity.Category;
import com.co.kr.modyeo.common.enumerate.Yn;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class CategoryDetail {

    private Long categoryId;

    private String categoryName;

    private Yn useYn;

    private String createdBy;

    private LocalDateTime createdTime;

    private String updatedBy;

    private LocalDateTime updatedTime;

    @Builder(builderMethodName = "of",builderClassName = "of")
    public CategoryDetail(Long categoryId, String categoryName, Yn useYn, String createdBy, LocalDateTime createdTime, String updatedBy, LocalDateTime updatedTime) {
        this.categoryId = categoryId;
        this.categoryName = categoryName;
        this.useYn = useYn;
        this.createdBy = createdBy;
        this.createdTime = createdTime;
        this.updatedBy = updatedBy;
        this.updatedTime = updatedTime;
    }

    public static CategoryDetail toDto(Category category){
        return of()
                .categoryId(category.getId())
                .categoryName(category.getName())
                .useYn(category.getUseYn())
                .createdBy(category.getCreatedBy())
                .updatedBy(category.getUpdatedBy())
                .createdTime(category.getCreatedDate())
                .updatedTime(category.getLastModifiedDate())
                .build();

    }
}
