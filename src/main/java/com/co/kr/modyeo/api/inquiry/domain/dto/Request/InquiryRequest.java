package com.co.kr.modyeo.api.inquiry.domain.dto.Request;

import com.co.kr.modyeo.api.inquiry.domain.entity.Inquiry;
import com.co.kr.modyeo.api.inquiry.domain.enumerate.InquiryStatus;
import com.co.kr.modyeo.api.member.domain.enumerate.Authority;
import com.co.kr.modyeo.common.util.SecurityUtil;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class InquiryRequest {
    private Long id;
    private String title;
    private String content;
    private Authority authority;
    private InquiryStatus status;
    private String flag;
    private String createdBy;

    @Builder(builderClassName = "of", builderMethodName = "of")
    public InquiryRequest(Long inquiryId, String title, String content,
                          Authority type, InquiryStatus status, String flag,
                          String createdBy) {
        this.id = inquiryId;
        this.title = title;
        this.content = content;
        this.authority = type;
        this.status = status;
        this.flag = flag;
        this.createdBy = createdBy;
    }

    public static Inquiry toEntity(InquiryRequest inquiryRequest) {
        return Inquiry.of()
                .id(inquiryRequest.id)
                .title(inquiryRequest.title)
                .content(inquiryRequest.content)
                .authority(inquiryRequest.authority)
                .status(inquiryRequest.status)
                .build();
    }

    public static Inquiry createInquiry(InquiryRequest inquiryRequest) {
        return Inquiry.createBuilder()
                .content(inquiryRequest.getContent())
                .title(inquiryRequest.getTitle())
                .authority(SecurityUtil.checkAuthority()) //TODO : SecurityUtil의 checkAuthority로 해결.
                .build();
    }
}
