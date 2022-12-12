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
public class AnswerDetail {
    private Long answerId;
    private Long inquiryId;
    private String content;
    private String createdBy;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-mm-dd HH:mm:ss", timezone = "Asia/Seoul")
    private LocalDateTime createdTime;
    private Authority authority;

    @Builder(builderClassName = "of", builderMethodName = "of")
    public AnswerDetail(Long answerId, Long inquiryId, String content,
                        String createdBy, LocalDateTime createdTime, Authority authority){
        this.answerId = answerId;
        this.inquiryId = inquiryId;
        this.content = content;
        this.createdBy = createdBy;
        this.createdTime = createdTime;
        this.authority = authority;
    }

    @Builder
    public static AnswerDetail toDto(Answer answer){
        return   AnswerDetail.of()
                .answerId(answer.getId())
                .inquiryId(answer.getInquiry().getId())
                .content(answer.getContent())
                .createdBy(answer.getCreatedBy())
                .createdTime(answer.getCreatedDate())
                .authority(answer.getAuthority())
                .build();
    }
}
