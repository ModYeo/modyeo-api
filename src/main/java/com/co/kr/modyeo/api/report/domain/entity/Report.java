package com.co.kr.modyeo.api.report.domain.entity;

import com.co.kr.modyeo.api.report.domain.enumuerate.ReportReason;
import com.co.kr.modyeo.api.report.domain.enumuerate.ReportStatus;
import com.co.kr.modyeo.api.report.domain.enumuerate.ReportType;
import com.co.kr.modyeo.common.entity.BaseEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Entity
@Table(name = "REPORT")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Report extends BaseEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "report_id")
    private Long id;

    @Column(name = "report_type")
    @Enumerated(value = EnumType.STRING)
    private ReportType reportType;

    @Column(name = "target_id")
    private Long targetId;

    private String title;

    @Column(name = "report_reason")
    @Enumerated(value = EnumType.STRING)
    private ReportReason reportReason;

    @Lob
    private String content;

    @Column(name = "report_status")
    @Enumerated(value = EnumType.STRING)
    private ReportStatus reportStatus;

    @Builder(builderClassName = "of",builderMethodName = "of")
    public Report(Long id, ReportType reportType, Long targetId, String title, ReportReason reportReason, String content, ReportStatus reportStatus) {
        this.id = id;
        this.reportType = reportType;
        this.targetId = targetId;
        this.title = title;
        this.reportReason = reportReason;
        this.content = content;
        this.reportStatus = reportStatus;
    }

    @Builder(builderClassName = "createBuilder", builderMethodName = "createBuilder")
    public Report(ReportType reportType, Long targetId, String title, ReportReason reportReason, String content) {
        this.reportType = reportType;
        this.targetId = targetId;
        this.title = title;
        this.reportReason = reportReason;
        this.content = content;
        this.reportStatus = ReportStatus.RCPT;
    }

    public void changeStatus(ReportStatus status) {
        this.reportStatus = status;
    }

    public void changeReport(String title, ReportReason reportReason, String contents) {
        if (title != null) this.title = title;
        if (reportReason != null) this.reportReason = reportReason;
        this.content = contents;
    }
}
