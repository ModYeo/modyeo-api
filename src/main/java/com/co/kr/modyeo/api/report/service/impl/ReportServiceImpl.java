package com.co.kr.modyeo.api.report.service.impl;

import com.co.kr.modyeo.api.report.domain.dto.ReportCreateRequest;
import com.co.kr.modyeo.api.report.domain.dto.ReportDetail;
import com.co.kr.modyeo.api.report.domain.dto.ReportResponse;
import com.co.kr.modyeo.api.report.domain.dto.ReportUpdateRequest;
import com.co.kr.modyeo.api.report.domain.entity.Report;
import com.co.kr.modyeo.api.report.domain.enumuerate.ReportStatus;
import com.co.kr.modyeo.api.report.domain.enumuerate.ReportType;
import com.co.kr.modyeo.api.report.repository.ReportRepository;
import com.co.kr.modyeo.api.report.service.ReportService;
import com.co.kr.modyeo.common.exception.ApiException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ReportServiceImpl implements ReportService {

    private final ReportRepository reportRepository;

    @Override
    @Transactional
    public Long createReport(ReportCreateRequest reportCreateRequest) {
        Report report = ReportCreateRequest.toEntity(reportCreateRequest);
        return reportRepository.save(report).getId();
    }

    @Override
    public ReportDetail getReport(Long reportId) {
        return ReportDetail.toDto(reportRepository.findById(reportId).orElseThrow(
                () -> ApiException.builder()
                        .status(HttpStatus.BAD_REQUEST)
                        .errorCode("NOT_FOUND_REPORT")
                        .errorMessage("찾을 수 없는 신고입니다.")
                        .build()));
    }

    @Override
    @Transactional
    public Long updateReportStatus(Long reportId, ReportStatus status) {
        Report report = reportRepository.findById(reportId).orElseThrow(
                () -> ApiException.builder()
                        .status(HttpStatus.BAD_REQUEST)
                        .errorCode("NOT_FOUND_REPORT")
                        .errorMessage("찾을 수 없는 신고입니다.")
                        .build());

        report.changeStatus(status);
        return report.getId();
    }

    @Override
    public void deleteReport(Long reportId) {
        Report report = reportRepository.findById(reportId).orElseThrow(
                () -> ApiException.builder()
                        .status(HttpStatus.BAD_REQUEST)
                        .errorCode("NOT_FOUND_REPORT")
                        .errorMessage("찾을 수 없는 신고입니다.")
                        .build());

        reportRepository.delete(report);
    }

    @Override
    public List<ReportResponse> getReports(ReportType type) {
        return reportRepository.findByReportType(type).stream()
                .map(ReportResponse::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public Long updateReport(ReportUpdateRequest reportUpdateRequest) {
        Report report = reportRepository.findById(reportUpdateRequest.getReportId()).orElseThrow(
                () -> ApiException.builder()
                        .status(HttpStatus.BAD_REQUEST)
                        .errorCode("NOT_FOUND_REPORT")
                        .errorMessage("찾을 수 없는 신고입니다.")
                        .build());

        report.changeReport(reportUpdateRequest.getTitle(),reportUpdateRequest.getReportReason(),reportUpdateRequest.getContents());
        return report.getId();
    }
}
