package com.co.kr.modyeo.api.bbs.domain.dto.request;

import com.co.kr.modyeo.api.bbs.domain.entity.Article;
import com.co.kr.modyeo.api.category.domain.entity.Category;
import com.co.kr.modyeo.common.enumerate.Yn;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ArticleCreateRequest {


    private Long categoryId;

    private String title;

    private String content;

    private String filePath;

    private Long hitCount;

    private Yn isHidden;

    @Builder(builderClassName = "of", builderMethodName = "of")
    public ArticleCreateRequest(String title, String content, String filePath, Long hitCount, Yn isHidden) {
        this.title = title;
        this.content = content;
        this.filePath = filePath;
        this.hitCount = hitCount;
        this.isHidden = isHidden;
    }

    public static Article toEntity(ArticleCreateRequest articleCreateRequest) {
        return Article.of()
                .title(articleCreateRequest.title)
                .content(articleCreateRequest.content)
                .filePath(articleCreateRequest.filePath)
                .hitCount(articleCreateRequest.hitCount)
                .build();
    }

    public static Article createArticle(ArticleCreateRequest articleCreateRequest, Category category) {
        return Article.createArticleBuilder()
                .content(articleCreateRequest.getContent())
                .category(category)
                .title(articleCreateRequest.getTitle())
                .filePath(articleCreateRequest.getFilePath())
                .isHidden(articleCreateRequest.getIsHidden())
                .build();
    }
}
