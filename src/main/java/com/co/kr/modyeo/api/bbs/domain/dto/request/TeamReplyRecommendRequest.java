package com.co.kr.modyeo.api.bbs.domain.dto.request;

import com.co.kr.modyeo.common.enumerate.Yn;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
public class TeamReplyRecommendRequest {

    @NotNull
    private Long memberId;

    @NotNull
    private Long teamReplyId;

    @NotNull
    private Yn recommendYn;

    @Builder(builderClassName = "of",builderMethodName = "of")
    public TeamReplyRecommendRequest(Long memberId, Long teamReplyId, Yn recommendYn) {
        this.memberId = memberId;
        this.teamReplyId = teamReplyId;
        this.recommendYn = recommendYn;
    }
}
