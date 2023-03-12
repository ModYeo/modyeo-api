package com.co.kr.modyeo.api.report.controller;

import com.co.kr.modyeo.api.report.domain.dto.ReportCreateRequest;
import com.co.kr.modyeo.api.report.domain.dto.ReportDetail;
import com.co.kr.modyeo.api.report.domain.dto.ReportResponse;
import com.co.kr.modyeo.api.report.domain.dto.ReportUpdateRequest;
import com.co.kr.modyeo.api.report.domain.enumuerate.ReportStatus;
import com.co.kr.modyeo.api.report.domain.enumuerate.ReportType;
import com.co.kr.modyeo.api.report.service.ReportService;
import com.co.kr.modyeo.common.result.JsonResultData;
import com.co.kr.modyeo.common.result.ResponseHandler;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "01. Report 서비스", description = "신고 서비스")
@RestController
@RequestMapping("/api/report")
@RequiredArgsConstructor
public class ReportController {

    private final ReportService reportService;

    @ApiOperation(value = "신고 생성 API")
    @PostMapping("")
    public ResponseEntity<?> createReport(ReportCreateRequest reportCreateRequest) {
        Long reportId = reportService.createReport(reportCreateRequest);
        return ResponseHandler.generate()
                .status(HttpStatus.CREATED)
                .data(reportId)
                .build();
    }

    @ApiOperation(value = "신고 상세 조회 API(어드민)")
    @GetMapping("/{reportId}")
    public ResponseEntity<?> getReport(@PathVariable Long reportId){
        ReportDetail report = reportService.getReport(reportId);
        return ResponseHandler.generate()
                .status(HttpStatus.OK)
                .data(report)
                .build();
    }

    @ApiOperation(value = "신고 목록 조회 API(어드민)")
    @GetMapping("/type/{type}")
    @ApiResponse(
            responseCode = "200",
            description = "성공",
            content = @Content(
                    schema = @Schema(implementation = ReportResponse.class)
            )
    )
    public ResponseEntity<?> getReports(@PathVariable ReportType type){
        List<ReportResponse> responseList = reportService.getReports(type);
        return ResponseHandler.generate()
                .status(HttpStatus.OK)
                .data(responseList)
                .build();
    }

    @ApiOperation(value = "신고 상태 변경 API(어드민)")
    @PatchMapping("/{reportId}/{status}")
    public ResponseEntity<?> updateReportStatus(
            @PathVariable Long reportId,
            @PathVariable ReportStatus status){
        reportId = reportService.updateReportStatus(reportId,status);
        return ResponseHandler.generate()
                .status(HttpStatus.OK)
                .data(reportId)
                .build();
    }

    @ApiOperation(value = "신고 변경 API")
    @PatchMapping("")
    public ResponseEntity<?> updateReport(ReportUpdateRequest reportUpdateRequest){
        Long reportId = reportService.updateReport(reportUpdateRequest);
        return ResponseHandler.generate()
                .status(HttpStatus.OK)
                .data(reportId)
                .build();
    }

    @ApiOperation(value = "신고 삭제 API")
    @DeleteMapping("/{reportId}")
    public ResponseEntity<?> deleteReport(
            @PathVariable Long reportId
    ){
        reportService.deleteReport(reportId);
        return ResponseHandler.generate()
                .status(HttpStatus.NO_CONTENT)
                .data(null)
                .build();
    }
}
