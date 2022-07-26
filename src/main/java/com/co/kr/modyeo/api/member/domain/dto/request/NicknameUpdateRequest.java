package com.co.kr.modyeo.api.member.domain.dto.request;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class NicknameUpdateRequest {

    private Long memberId;

    private String nickname;

    @Builder(builderClassName = "of",builderMethodName = "of")
    public NicknameUpdateRequest(Long memberId, String nickname) {
        this.memberId = memberId;
        this.nickname = nickname;
    }
}
