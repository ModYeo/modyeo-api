package com.co.kr.modyeo.api.bbs.domain.dto.response;

import com.co.kr.modyeo.api.bbs.domain.entity.Article;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
public class ArticleDetail {

    private Long articleId;

    private String title;

    private String content;

    private Boolean isHidden;

    private Long hitCount;

    private String createdBy;

    private LocalDateTime createdTime;

    private List<ReplyResponse> replyResponses = new ArrayList<>();


    @Builder(builderMethodName = "of",builderClassName = "of")
    public ArticleDetail(Long articleId,
                         String title,
                         String content,
                         Boolean isHidden,
                         String createdBy,
                         LocalDateTime createdTime,
                         Long hitCount,
                         List<ReplyResponse> replyResponses) {
        this.articleId = articleId;
        this.title = title;
        this.content = content;
        this.isHidden = isHidden;
        this.hitCount = hitCount;
        this.createdBy = createdBy;
        this.createdTime = createdTime;
        this.replyResponses = replyResponses;
    }

    public static ArticleDetail toDto(Article article){
        return ArticleDetail.of()
                .articleId(article.getId())
                .title(article.getTitle())
                .content(article.getContent())
                .isHidden(article.getIsHidden())
                .hitCount(article.getHitCount())
                .createdBy(article.getCreatedBy())
                .createdTime(article.getCreatedDate())
                .replyResponses(article.getReplyList().stream().map(ReplyResponse::toDto).collect(Collectors.toList()))
                .build();
    }
}
