package com.co.kr.modyeo.api.report.domain.dto;

import com.co.kr.modyeo.api.report.domain.entity.ReportReason;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ReportUpdateRequest {

    private Long reportId;

    private String title;

    private ReportReason reportReason;

    private String contents;
}


