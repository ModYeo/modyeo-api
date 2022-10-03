package com.co.kr.modyeo.api.report.service.impl;

import com.co.kr.modyeo.api.report.domain.dto.ReportCreateRequest;
import com.co.kr.modyeo.api.report.domain.entity.Report;
import com.co.kr.modyeo.api.report.repository.ReportRepository;
import com.co.kr.modyeo.api.report.service.ReportService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ReportServiceImpl implements ReportService {

    private final ReportRepository reportRepository;

    @Override
    @Transactional
    public void createReport(ReportCreateRequest reportCreateRequest) {
        Report report = ReportCreateRequest.toDto(reportCreateRequest);
        reportRepository.save(report);
    }
}
