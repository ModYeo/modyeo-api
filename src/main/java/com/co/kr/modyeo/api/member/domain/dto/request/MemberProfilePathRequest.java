package com.co.kr.modyeo.api.member.domain.dto.request;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
public class MemberProfilePathRequest {

    @NotNull
    private Long memberId;

    private String filePath;

    public MemberProfilePathRequest(Long memberId, String filePath) {
        this.memberId = memberId;
        this.filePath = filePath;
    }
}
