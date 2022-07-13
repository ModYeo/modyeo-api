package com.co.kr.modyeo.api.bbs.domain.dto.response;

import com.co.kr.modyeo.api.bbs.domain.entity.Article;
import com.co.kr.modyeo.common.enumerate.Yn;
import com.querydsl.core.annotations.QueryProjection;
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

    private String categoryName;

    private String filePath;

    private Yn isHidden;

    private Long replyCount;

    private Long hitCount;

    private String createdBy;

    private LocalDateTime createdTime;

    @QueryProjection
    @Builder(builderClassName = "of",builderMethodName = "of")
    public ArticleResponse(Long articleId, String title, String content,String categoryName,String filePath, Yn isHidden,Long hitCount,Long replyCount, String createdBy, LocalDateTime createdTime) {
        this.articleId = articleId;
        this.title = title;
        this.content = content;
        this.categoryName = categoryName;
        this.filePath = filePath;
        this.isHidden = isHidden;
        this.replyCount = replyCount;
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
