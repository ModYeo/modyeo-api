package com.co.kr.modyeo.api.bbs.domain.dto;


import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Getter
@NoArgsConstructor
public class ReplyUpdateRequest {

    @NotNull
    private Long id;

    private String content;

    @Builder(builderMethodName = "of", builderClassName = "of")
    public ReplyUpdateRequest(Long id, String content) {
        this.id = id;
        this.content = content;
    }
}
