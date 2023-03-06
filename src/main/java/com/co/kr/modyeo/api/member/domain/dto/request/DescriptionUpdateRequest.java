package com.co.kr.modyeo.api.member.domain.dto.request;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class DescriptionUpdateRequest {
    private Long memberId;

    private String description;

    @Builder(builderClassName = "of", builderMethodName = "of")
    public DescriptionUpdateRequest(Long memberId, String description) {
        this.memberId = memberId;
        this.description = description;
    }
}
