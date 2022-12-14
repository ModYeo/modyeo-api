package com.co.kr.modyeo.api.inquiry.domain.dto.request;

import com.co.kr.modyeo.api.inquiry.domain.entity.Answer;
import com.co.kr.modyeo.api.inquiry.domain.entity.Inquiry;
import com.co.kr.modyeo.api.member.domain.enumerate.Authority;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class AnswerRequest {

    private Long answerId;
    private Long inquiryId;
    private String content;
    private Authority authority;
    private String createdBy;
    private LocalDateTime createdTime;

    @Builder(builderClassName = "of", builderMethodName = "of")
    public AnswerRequest(Long answerId, Long inquiryId, String content,
                          Authority authority, String createdBy, LocalDateTime createdTime) {
        this.answerId = answerId;
        this.inquiryId = inquiryId;
        this.content = content;
        this.authority = authority;
        this.createdBy = createdBy;
        this.createdTime = createdTime;
    }
    public static Answer toEntity(AnswerRequest answerRequest, Inquiry inquiry) {
        return Answer.of()
                .id(answerRequest.getAnswerId())
                .content(answerRequest.getContent())
                .inquiry(inquiry)
                .authority(answerRequest.getAuthority())
                .build();
    }

    public static Answer createAnswer(AnswerRequest answerRequest, Inquiry inquiry) {
        return Answer.createAnswerBuilder()
                .content(answerRequest.getContent())
                .inquiry(inquiry).build();
    }
}
