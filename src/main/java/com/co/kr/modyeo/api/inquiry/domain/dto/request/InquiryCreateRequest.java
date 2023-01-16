package com.co.kr.modyeo.api.inquiry.domain.dto.request;

import com.co.kr.modyeo.api.inquiry.domain.entity.Inquiry;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
public class InquiryCreateRequest {
    @NotNull
    private String title;

    @NotNull
    private String content;

    @Builder(builderClassName = "of", builderMethodName = "of")
    public InquiryCreateRequest(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public static Inquiry toEntity(InquiryCreateRequest InquiryCreateRequest) {
        return Inquiry.of()
                .title(InquiryCreateRequest.title)
                .content(InquiryCreateRequest.content)
                .build();
    }

    public static Inquiry createInquiry(InquiryCreateRequest InquiryCreateRequest) {
        return Inquiry.createBuilder()
                .content(InquiryCreateRequest.getContent())
                .title(InquiryCreateRequest.getTitle())
                .build();
    }
}
