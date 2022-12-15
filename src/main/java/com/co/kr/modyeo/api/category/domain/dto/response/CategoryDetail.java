package com.co.kr.modyeo.api.category.domain.dto.response;

import com.co.kr.modyeo.api.category.domain.entity.Category;
import com.co.kr.modyeo.common.enumerate.Yn;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class CategoryDetail {

    private Long categoryId;

    private String categoryName;

    private Yn useYn;

    private Long createdBy;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
    private LocalDateTime createdTime;

    private Long updatedBy;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
    private LocalDateTime updatedTime;

    @Builder(builderMethodName = "of", builderClassName = "of")
    public CategoryDetail(Long categoryId, String categoryName, Yn useYn, Long createdBy, LocalDateTime createdTime, Long updatedBy, LocalDateTime updatedTime) {
        this.categoryId = categoryId;
        this.categoryName = categoryName;
        this.useYn = useYn;
        this.createdBy = createdBy;
        this.createdTime = createdTime;
        this.updatedBy = updatedBy;
        this.updatedTime = updatedTime;
    }

    public static CategoryDetail toDto(Category category) {
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
