package com.co.kr.modyeo.api.inquiry.domain.dto.Response;

import com.co.kr.modyeo.api.inquiry.domain.entity.Inquiry;
import com.co.kr.modyeo.api.inquiry.domain.enumerate.InquiryStatus;
import com.co.kr.modyeo.api.member.domain.enumerate.Authority;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class InquiryResponse {
    private Long inquiryId;
    private String title;
    private Authority authority;
    private InquiryStatus status;
    private Long createdBy;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
    private LocalDateTime createdTime;

    @QueryProjection // TODO : 뭔지 찾아보기
    @Builder(builderClassName = "of", builderMethodName = "of")
    public InquiryResponse(Long inquiryId, String title, Authority authority, LocalDateTime createdTime
            , InquiryStatus status, Long createdBy) {
        this.inquiryId = inquiryId;
        this.title = title;
        this.authority = authority;
        this.createdTime = createdTime;
        this.status = status;
        this.createdBy = createdBy;
    }

    public static InquiryResponse toDto(Inquiry inquiry) {
        return InquiryResponse.of()
                .inquiryId(inquiry.getId())
                .title(inquiry.getTitle())
                .authority(inquiry.getAuthority())
                .status(inquiry.getStatus())
                .createdBy(inquiry.getCreatedBy())
                .createdTime(inquiry.getCreatedDate())
                .build();
    }

}
