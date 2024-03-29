package com.co.kr.modyeo.api.report.service;

import com.co.kr.modyeo.api.report.domain.dto.ReportCreateRequest;
import com.co.kr.modyeo.api.report.domain.dto.ReportDetail;
import com.co.kr.modyeo.api.report.domain.dto.ReportResponse;
import com.co.kr.modyeo.api.report.domain.dto.ReportUpdateRequest;
import com.co.kr.modyeo.api.report.domain.enumuerate.ReportStatus;
import com.co.kr.modyeo.api.report.domain.enumuerate.ReportType;

import java.util.List;

public interface ReportService {
    Long createReport(ReportCreateRequest reportCreateRequest);

    ReportDetail getReport(Long reportId);

    Long updateReportStatus(Long reportId, ReportStatus status);

    void deleteReport(Long reportId);

    List<ReportResponse> getReports(ReportType type);

    Long updateReport(ReportUpdateRequest reportUpdateRequest);
}
