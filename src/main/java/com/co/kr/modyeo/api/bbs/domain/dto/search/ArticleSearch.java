package com.co.kr.modyeo.api.bbs.domain.dto.search;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Sort;

@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ArticleSearch {

    private String title;

    private String content;

    private String createdBy;

    private Long categoryId;

    private Integer limit;

    private Integer offset;

    private String orderBy;

    private Sort.Direction direction;

    private Boolean isMyArticle;

    @Builder(builderMethodName = "of", builderClassName = "of")
    public ArticleSearch(String title, String content, Integer limit, Integer offset, String orderBy, Sort.Direction direction, Long categoryId, String createdBy) {
        this.title = title;
        this.categoryId = categoryId;
        this.createdBy = createdBy;
        this.content = content;
        this.limit = limit != null ? limit : 20;
        this.offset = offset != null ? offset : 0;
        this.orderBy = orderBy != null ? orderBy : "id";
        this.direction = direction != null ? direction : Sort.Direction.DESC;
    }
}
