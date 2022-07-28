package com.co.kr.modyeo.api.bbs.domain.dto.response;

import com.co.kr.modyeo.api.bbs.domain.entity.Article;
import com.co.kr.modyeo.common.enumerate.Yn;
import com.fasterxml.jackson.annotation.JsonFormat;
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

    private Long categoryId;

    private String categoryName;

    private String filePath;

    private Yn isHidden;

    private Integer replyCount;

    private Integer recommendCount;

    private Long hitCount;

    private String createdBy;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
    private LocalDateTime createdTime;

    @QueryProjection
    @Builder(builderClassName = "of", builderMethodName = "of")
    public ArticleResponse(Long articleId,
                           String title,
                           String content,
                           Long categoryId,
                           String categoryName,
                           String filePath,
                           Yn isHidden,
                           Long hitCount,
                           Integer recommendCount,
                           Integer replyCount,
                           String createdBy,
                           LocalDateTime createdTime) {
        this.articleId = articleId;
        this.title = title;
        this.content = content;
        this.categoryId = categoryId;
        this.categoryName = categoryName;
        this.filePath = filePath;
        this.isHidden = isHidden;
        this.replyCount = replyCount;
        this.recommendCount = recommendCount;
        this.hitCount = hitCount;
        this.createdBy = createdBy;
        this.createdTime = createdTime;
    }

    public static ArticleResponse toDto(Article article) {
        return ArticleResponse.of()
                .articleId(article.getId())
                .categoryId(article.getCategory().getId())
                .categoryName(article.getCategory().getName())
                .filePath(article.getFilePath())
                .title(article.getTitle())
                .content(article.getContent())
                .isHidden(article.getIsHidden())
                .hitCount(article.getHitCount())
                .recommendCount((int) article.getArticleRecommendList().stream().filter(articleRecommend -> articleRecommend.getRecommendYn() == Yn.Y).count())
                .replyCount(article.getReplyList().size())
                .createdBy(article.getCreatedBy())
                .createdTime(article.getCreatedDate())
                .build();
    }
}
