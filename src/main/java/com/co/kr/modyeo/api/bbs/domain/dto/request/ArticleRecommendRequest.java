package com.co.kr.modyeo.api.bbs.domain.dto.request;

import com.co.kr.modyeo.common.enumerate.Yn;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
public class ArticleRecommendRequest {

    @NotNull
    private Long memberId;

    @NotNull
    private Long articleId;

    @NotNull
    private Yn recommendYn;

    @Builder(builderClassName = "of", builderMethodName = "of")
    public ArticleRecommendRequest(Long memberId, Long articleId, Yn recommendYn) {
        this.memberId = memberId;
        this.articleId = articleId;
        this.recommendYn = recommendYn;
    }
}
