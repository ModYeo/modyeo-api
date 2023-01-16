package com.co.kr.modyeo.api.inquiry.domain.dto.request;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
public class InquiryUpdateRequest {
    @NotNull
    private Long id;
    @NotNull
    private String title;
    @NotNull
    private String content;
    @NotNull
    private char isHidden;

    @Builder(builderClassName = "of", builderMethodName = "of")
    public InquiryUpdateRequest(Long id, String title, String content, char isHidden){
        this.id = id;
        this.title = title;
        this.content = content;
        this.isHidden = isHidden;
    }
}
