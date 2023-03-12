package com.co.kr.modyeo.api.report.domain.dto;

import com.co.kr.modyeo.api.report.domain.entity.Report;
import com.co.kr.modyeo.api.report.domain.enumuerate.ReportReason;
import com.co.kr.modyeo.api.report.domain.enumuerate.ReportStatus;
import com.co.kr.modyeo.api.report.domain.enumuerate.ReportType;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class ReportDetail {

    private Long id;

    private ReportType reportType;

    private Long targetId;

    private String title;

    private ReportReason reportReason;

    private String contents;

    private ReportStatus reportStatus;

    private LocalDateTime createdTime;

    private LocalDateTime updatedTime;

    private Long createdBy;

    private Long updatedBy;

    @Builder(builderClassName = "of",builderMethodName = "of")
    public ReportDetail(Long id, ReportType reportType, Long targetId, String title, ReportReason reportReason, String contents, ReportStatus reportStatus, LocalDateTime createdTime, LocalDateTime updatedTime, Long createdBy, Long updatedBy) {
        this.id = id;
        this.reportType = reportType;
        this.targetId = targetId;
        this.title = title;
        this.reportReason = reportReason;
        this.contents = contents;
        this.reportStatus = reportStatus;
        this.createdTime = createdTime;
        this.updatedTime = updatedTime;
        this.createdBy = createdBy;
        this.updatedBy = updatedBy;
    }

    public static ReportDetail toDto(Report report){
        return ReportDetail.of()
                .id(report.getId())
                .reportType(report.getReportType())
                .targetId(report.getTargetId())
                .title(report.getTitle())
                .reportReason(report.getReportReason())
                .contents(report.getContent())
                .reportStatus(report.getReportStatus())
                .createdTime(report.getCreatedDate())
                .updatedTime(report.getLastModifiedDate())
                .createdBy(report.getCreatedBy())
                .updatedBy(report.getUpdatedBy())
                .build();
    }
}
