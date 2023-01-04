package com.co.kr.modyeo.api.inquiry.domain.dto.request;

import com.co.kr.modyeo.api.inquiry.domain.entity.Answer;
import com.co.kr.modyeo.api.inquiry.domain.entity.Inquiry;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
public class AnswerCreateRequest {
    @NotNull
    private Long inquiryId;
    @NotNull
    private String content;

    @Builder(builderClassName = "of", builderMethodName = "of")
    public AnswerCreateRequest(Long inquiryId, String content){
        this.inquiryId = inquiryId;
        this.content = content;
    }

    public static Answer createAnswer(AnswerCreateRequest answerCreateRequest, Inquiry inquiry) {
        return Answer.createAnswerBuilder()
                .content(answerCreateRequest.getContent())
                .inquiry(inquiry).build();
    }
}
