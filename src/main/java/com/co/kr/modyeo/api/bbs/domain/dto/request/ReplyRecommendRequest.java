package com.co.kr.modyeo.api.bbs.domain.dto.request;

import com.co.kr.modyeo.common.enumerate.Yn;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
public class ReplyRecommendRequest {

    @NotNull
    private Long memberId;

    @NotNull
    private Long replyId;

    @NotNull
    private Yn recommendYn;

    @Builder(builderClassName = "of", builderMethodName = "of")
    public ReplyRecommendRequest(Long memberId, Long replyId, Yn recommendYn) {
        this.memberId = memberId;
        this.replyId = replyId;
        this.recommendYn = recommendYn;
    }
}
