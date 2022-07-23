package com.co.kr.modyeo.api.bbs.domain.dto.request;

import com.co.kr.modyeo.common.enumerate.Yn;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class RecommendRequest {

    private Long articleId;

    private Yn recommendYn;

    @Builder(builderClassName = "of", builderMethodName = "of")
    public RecommendRequest(Long articleId, Yn recommendYn) {
        this.articleId = articleId;
        this.recommendYn = recommendYn;
    }
}
