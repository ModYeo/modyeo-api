package com.co.kr.modyeo.api.bbs.domain.dto.search;

import com.co.kr.modyeo.common.dto.SearchDto;
import lombok.*;
import org.springframework.data.domain.Sort;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ArticleSearch extends SearchDto {

    private String title;

    private String content;

    private Long memberId;

    private Long categoryId;

    private Boolean isMyArticle;

    @Builder(builderMethodName = "of", builderClassName = "of")
    public ArticleSearch(String title, String content, Long categoryId, Long memberId) {
        this.title = title;
        this.categoryId = categoryId;
        this.memberId = memberId;
        this.content = content;
    }
}
