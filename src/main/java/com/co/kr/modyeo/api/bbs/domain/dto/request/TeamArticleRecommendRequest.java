package com.co.kr.modyeo.api.bbs.domain.dto.request;

import com.co.kr.modyeo.common.enumerate.Yn;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
public class TeamArticleRecommendRequest {

    @NotNull
    private Long memberId;

    @NotNull
    private Long teamArticleId;

    @NotNull
    private Yn recommendYn;

    @Builder(builderClassName = "of",builderMethodName = "of")
    public TeamArticleRecommendRequest(Long memberId, Long teamArticleId, Yn recommendYn) {
        this.memberId = memberId;
        this.teamArticleId = teamArticleId;
        this.recommendYn = recommendYn;
    }
}
