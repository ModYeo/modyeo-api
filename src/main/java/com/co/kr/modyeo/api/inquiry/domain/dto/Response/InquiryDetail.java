package com.co.kr.modyeo.api.inquiry.domain.dto.Response;

import com.co.kr.modyeo.api.inquiry.domain.entity.Inquiry;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
public class InquiryDetail {
    private Long id;
    private String title;
    private String content;
    private String createdBy;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-mm-dd HH:mm:ss", timezone = "Asia/Seoul")
    private LocalDateTime createdTime;
    private List<AnswerResponse> answerList = new ArrayList<>();

    @Builder(builderMethodName = "of", builderClassName = "of")
    public InquiryDetail(Long id, String title, String content, String createdBy
            , LocalDateTime createdTime, List<AnswerResponse> answerList) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.createdBy = createdBy;
        this.createdTime = createdTime;
        this.answerList = answerList;
    }

    @Builder
    public static InquiryDetail toDto(Inquiry inquiry) {
        return InquiryDetail.of()
                .id(inquiry.getId())
                .title(inquiry.getTitle())
                .content(inquiry.getContent())
                .createdBy(inquiry.getCreatedBy())
                .createdTime(inquiry.getCreatedDate())
                .answerList(inquiry.getAnswerList()!=null?
                        inquiry.getAnswerList().stream().map(AnswerResponse::toDto).collect(Collectors.toList()):null)
                .build();
    }
}
