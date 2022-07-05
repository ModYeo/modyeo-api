package com.co.kr.modyeo.api.bbs.domain.dto.response;

import com.co.kr.modyeo.api.bbs.domain.entity.Article;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class ArticleResponse {

    private Long articleId;

    private String title;

    private String content;

    private Boolean isHidden;

    private Long hitCount;

    private String createdBy;

    private LocalDateTime createdTime;

    @Builder(builderClassName = "of",builderMethodName = "of")
    public ArticleResponse(Long articleId, String title, String content, Boolean isHidden,Long hitCount, String createdBy, LocalDateTime createdTime) {
        this.articleId = articleId;
        this.title = title;
        this.content = content;
        this.isHidden = isHidden;
        this.hitCount = hitCount;
        this.createdBy = createdBy;
        this.createdTime = createdTime;
    }

    public static ArticleResponse toDto(Article article) {
        return ArticleResponse.of()
                .articleId(article.getId())
                .title(article.getTitle())
                .content(article.getContent())
                .isHidden(article.getIsHidden())
                .hitCount(article.getHitCount())
                .createdBy(article.getCreatedBy())
                .createdTime(article.getCreatedDate())
                .build();
    }
}
