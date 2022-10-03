package com.co.kr.modyeo.api.report.domain.dto;

import com.co.kr.modyeo.api.report.domain.entity.Report;
import com.co.kr.modyeo.api.report.domain.entity.ReportReason;
import com.co.kr.modyeo.api.report.domain.enumuerate.ReportStatus;
import com.co.kr.modyeo.api.report.domain.enumuerate.ReportType;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;

@Data
@NoArgsConstructor
public class ReportCreateRequest {

    private ReportType reportType;

    private Long targetId;

    private String title;

    private ReportReason reportReason;

    private String contents;

    private ReportStatus reportStatus;

    public ReportCreateRequest(ReportType reportType, Long targetId, String title, ReportReason reportReason, String contents, ReportStatus reportStatus) {
        this.reportType = reportType;
        this.targetId = targetId;
        this.title = title;
        this.reportReason = reportReason;
        this.contents = contents;
        this.reportStatus = reportStatus;
    }

    public static Report toDto(ReportCreateRequest reportCreateRequest){
        return Report.createBuilder()
                .reportType(reportCreateRequest.getReportType())
                .targetId(reportCreateRequest.getTargetId())
                .title(reportCreateRequest.getTitle())
                .reportReason(reportCreateRequest.getReportReason())
                .contents(reportCreateRequest.getContents())
                .build();
    }
}
