package com.co.kr.modyeo.api.report.service;

import com.co.kr.modyeo.api.report.domain.dto.ReportCreateRequest;
import com.co.kr.modyeo.api.report.domain.dto.ReportDetail;

public interface ReportService {
    void createReport(ReportCreateRequest reportCreateRequest);

    ReportDetail getReport(Long reportId);
}
