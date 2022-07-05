package com.co.kr.modyeo.api.bbs.domain.dto.request;

import com.co.kr.modyeo.api.bbs.domain.entity.Article;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ArticleRequest {

    private Long id;

    private String title;

    private String content;

    private String filePath;

    private Long hitCount;

    private Boolean isHidden;

    @Builder(builderClassName = "of",builderMethodName = "of")
    public ArticleRequest(Long id, String content, String filePath, Long hitCount, Boolean isHidden) {
        this.id = id;
        this.content = content;
        this.filePath = filePath;
        this.hitCount = hitCount;
        this.isHidden = isHidden;
    }

    public static Article toEntity(ArticleRequest articleRequest){
        return Article.of()
                .id(articleRequest.id)
                .content(articleRequest.content)
                .filePath(articleRequest.filePath)
                .hitCount(articleRequest.hitCount)
                .build();
    }

    public static Article createArticle(ArticleRequest articleRequest){
        return Article.createArticleBuilder()
                .content(articleRequest.getContent())
                .title(articleRequest.getTitle())
                .filePath(articleRequest.getFilePath())
                .isHidden(articleRequest.getIsHidden())
                .build();
    }
}
