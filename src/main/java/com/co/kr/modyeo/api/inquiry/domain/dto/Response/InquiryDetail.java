package com.co.kr.modyeo.api.inquiry.domain.dto.Response;

import com.co.kr.modyeo.api.inquiry.domain.entity.Answer;
import com.co.kr.modyeo.api.inquiry.domain.entity.Inquiry;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class InquiryDetail {
    private Long id;
    private String title;
    private String content;
    private String createdBy;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-mm-dd HH:mm:ss", timezone = "Asia/Seoul")
    private LocalDateTime createdTime;
    private List<Answer> answerList = new ArrayList<>();

    @Builder(builderMethodName = "of", builderClassName = "of")
    public InquiryDetail(Long id, String title, String content, String createdBy
            , LocalDateTime createdTime, List<Answer> answerList) {
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
                //.answerList() TODO : 답변 리스트 있어야 함.
                .build();
    }
}
