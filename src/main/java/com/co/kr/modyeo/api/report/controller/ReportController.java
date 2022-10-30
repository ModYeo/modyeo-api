package com.co.kr.modyeo.api.report.controller;

import com.co.kr.modyeo.api.report.domain.dto.ReportCreateRequest;
import com.co.kr.modyeo.api.report.domain.dto.ReportDetail;
import com.co.kr.modyeo.api.report.domain.dto.ReportResponse;
import com.co.kr.modyeo.api.report.domain.dto.ReportUpdateRequest;
import com.co.kr.modyeo.api.report.domain.enumuerate.ReportStatus;
import com.co.kr.modyeo.api.report.domain.enumuerate.ReportType;
import com.co.kr.modyeo.api.report.service.ReportService;
import com.co.kr.modyeo.common.result.JsonResultData;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/report")
@RequiredArgsConstructor
public class ReportController {

    private final ReportService reportService;

    @PostMapping("")
    public ResponseEntity<?> createReport(ReportCreateRequest reportCreateRequest) {
        Long reportId = reportService.createReport(reportCreateRequest);
        return ResponseEntity.ok(JsonResultData.successResultBuilder()
                .data(reportId)
                .build());
    }

    @GetMapping("/{reportId}")
    public ResponseEntity<?> getReport(@PathVariable Long reportId){
        ReportDetail report = reportService.getReport(reportId);
        return ResponseEntity.ok(JsonResultData.successResultBuilder()
                .data(report)
                .build());
    }

    @GetMapping("/{type}")
    public ResponseEntity<?> getReports(@PathVariable ReportType type){
        List<ReportResponse> responseList = reportService.getReports(type);
        return ResponseEntity.ok(JsonResultData.successResultBuilder()
                .data(responseList)
                .build());
    }

    @PatchMapping("/{reportId}/{status}")
    public ResponseEntity<?> updateReportStatus(
            @PathVariable Long reportId,
            @PathVariable ReportStatus status){
        reportId = reportService.updateReportStatus(reportId,status);
        return ResponseEntity.ok(JsonResultData.successResultBuilder()
                .data(reportId)
                .build());
    }

    @PatchMapping("")
    public ResponseEntity<?> updateReport(ReportUpdateRequest reportUpdateRequest){
        Long reportId = reportService.updateReport(reportUpdateRequest);
        return ResponseEntity.ok(JsonResultData.successResultBuilder()
                .data(reportId)
                .build());
    }

    @DeleteMapping("/{reportId}")
    public ResponseEntity<?> deleteReport(
            @PathVariable Long reportId
    ){
        reportService.deleteReport(reportId);
        return ResponseEntity.ok(JsonResultData.successResultBuilder()
                .data(null)
                .build());
    }
}
