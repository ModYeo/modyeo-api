package com.co.kr.modyeo.api.bbs.domain.dto.response;

import com.co.kr.modyeo.api.bbs.domain.entity.Article;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class ArticleResponse {

    private Long articleId;
    private String title;
    private String content;


    @Builder(builderClassName = "of",builderMethodName = "of")
    public ArticleResponse(Long articleId, String title, String content) {
        this.articleId = articleId;
        this.title = title;
        this.content = content;
    }

    public static ArticleResponse toDto(Article article) {
        return ArticleResponse.of()
                .articleId(article.getId())
                .title(article.getTitle())
                .content(article.getContent())
                .build();
    }

    static class Detail{
        List<ReplyResponse> replyResponses = new ArrayList<>();
    }
}
