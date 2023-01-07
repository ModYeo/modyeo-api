package com.co.kr.modyeo.api.inquiry.domain.dto.request;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
public class AnswerUpdateRequest {
    @NotNull
    private Long answerId;

    @NotNull
    private String content;

    @Builder(builderClassName = "of", builderMethodName = "of")
    public AnswerUpdateRequest(Long answerId, String content){
        this.answerId = answerId;
        this.content = content;
    }
}
