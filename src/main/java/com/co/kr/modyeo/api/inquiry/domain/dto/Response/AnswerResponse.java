package com.co.kr.modyeo.api.inquiry.domain.dto.Response;

import com.co.kr.modyeo.api.inquiry.domain.entity.Answer;
import com.co.kr.modyeo.api.member.domain.enumerate.Authority;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class AnswerResponse {
    private Long answerId;
    private Long inquiryId;
    private String content;
    private Authority authority;
    private Long createdBy;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
    private LocalDateTime createdTime;

    @Builder(builderMethodName = "of", builderClassName = "of")
    public AnswerResponse(Long answerId, Long inquiryId, String content,
                          Authority authority, Long createdBy, LocalDateTime createdTime){
        this.answerId = answerId;
        this.inquiryId = inquiryId;
        this.content = content;
        this.authority = authority;
        this.createdBy = createdBy;
        this.createdTime = createdTime;
    }

    public static AnswerResponse toDto(Answer answer){
        return   AnswerResponse.of()
                .answerId(answer.getId())
                .inquiryId(answer.getInquiry().getId())
                .content(answer.getContent())
                .authority(answer.getAuthority())
                .createdBy(answer.getCreatedBy())
                .createdTime(answer.getCreatedDate())
                .build();
    }
}
