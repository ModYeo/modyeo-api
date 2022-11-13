package com.co.kr.modyeo.api.report.domain.dto;

import com.co.kr.modyeo.api.report.domain.entity.Report;
import com.co.kr.modyeo.api.report.domain.entity.ReportReason;
import com.co.kr.modyeo.api.report.domain.enumuerate.ReportStatus;
import com.co.kr.modyeo.api.report.domain.enumuerate.ReportType;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ReportResponse {

    private Long id;

    private ReportType reportType;

    private Long targetId;

    private String title;

    private ReportReason reportReason;

    private String contents;

    private ReportStatus reportStatus;

    @Builder(builderClassName = "of", builderMethodName = "of")
    public ReportResponse(Long id, ReportType reportType, Long targetId, String title, ReportReason reportReason, String contents, ReportStatus reportStatus) {
        this.id = id;
        this.reportType = reportType;
        this.targetId = targetId;
        this.title = title;
        this.reportReason = reportReason;
        this.contents = contents;
        this.reportStatus = reportStatus;
    }

    public static ReportResponse toDto(Report report){
        return ReportResponse.of()
                .id(report.getId())
                .reportType(report.getReportType())
                .targetId(report.getTargetId())
                .title(report.getTitle())
                .reportReason(report.getReportReason())
                .contents(report.getContent())
                .reportStatus(report.getReportStatus())
                .build();
    }
}
