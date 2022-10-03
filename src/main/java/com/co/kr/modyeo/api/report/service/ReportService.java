package com.co.kr.modyeo.api.report.service;

import com.co.kr.modyeo.api.report.domain.dto.ReportCreateRequest;

public interface ReportService {
    void createReport(ReportCreateRequest reportCreateRequest);
}
