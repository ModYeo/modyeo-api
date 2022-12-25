package com.co.kr.modyeo.api.bbs.domain.dto.request;

import com.co.kr.modyeo.common.enumerate.Yn;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ArticleUpdateRequest {
    private Long articleId;

    private Long categoryId;

    private String title;

    private String content;

    private String filePath;

    private Long hitCount;

    private Yn isHidden;

    @Builder(builderClassName = "of",builderMethodName = "of")
    public ArticleUpdateRequest(Long articleId, Long categoryId, String title, String content, String filePath, Long hitCount, Yn isHidden) {
        this.articleId = articleId;
        this.categoryId = categoryId;
        this.title = title;
        this.content = content;
        this.filePath = filePath;
        this.hitCount = hitCount;
        this.isHidden = isHidden;
    }
}
