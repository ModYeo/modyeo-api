package com.co.kr.modyeo.api.report.controller;

import com.co.kr.modyeo.api.report.domain.dto.ReportCreateRequest;
import com.co.kr.modyeo.api.report.domain.dto.ReportDetail;
import com.co.kr.modyeo.api.report.domain.enumuerate.ReportStatus;
import com.co.kr.modyeo.api.report.service.ReportService;
import com.co.kr.modyeo.common.result.JsonResultData;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/report")
@RequiredArgsConstructor
public class ReportController {

    private final ReportService reportService;

    @PostMapping("")
    public ResponseEntity<?> createReport(ReportCreateRequest reportCreateRequest) {
        reportService.createReport(reportCreateRequest);
        return ResponseEntity.ok(JsonResultData.successResultBuilder()
                .data(null)
                .build());
    }

    @GetMapping("/{reportId}")
    public ResponseEntity<?> getReport(@PathVariable Long reportId){
        ReportDetail report = reportService.getReport(reportId);
        return ResponseEntity.ok(JsonResultData.successResultBuilder()
                .data(report)
                .build());
    }

    @PatchMapping("/{reportId}/{status}")
    public ResponseEntity<?> updateReportStatus(
            @PathVariable Long reportId,
            @PathVariable ReportStatus status){
        reportService.updateReportStatus(reportId,status);
        return ResponseEntity.ok(JsonResultData.successResultBuilder()
                .data(null)
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
