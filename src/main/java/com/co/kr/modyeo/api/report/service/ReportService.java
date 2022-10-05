package com.co.kr.modyeo.api.report.service;

import com.co.kr.modyeo.api.report.domain.dto.ReportCreateRequest;
import com.co.kr.modyeo.api.report.domain.dto.ReportDetail;
import com.co.kr.modyeo.api.report.domain.enumuerate.ReportStatus;

public interface ReportService {
    void createReport(ReportCreateRequest reportCreateRequest);

    ReportDetail getReport(Long reportId);

    void updateReportStatus(Long reportId, ReportStatus status);

    void deleteReport(Long reportId);
}
