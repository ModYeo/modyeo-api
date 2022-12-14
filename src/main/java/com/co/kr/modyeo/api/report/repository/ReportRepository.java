package com.co.kr.modyeo.api.report.repository;

import com.co.kr.modyeo.api.report.domain.entity.Report;
import com.co.kr.modyeo.api.report.domain.enumuerate.ReportType;
import com.co.kr.modyeo.api.report.repository.custom.ReportCustomRepository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReportRepository extends JpaRepository<Report, Long>, ReportCustomRepository {
    List<Report> findByReportType(ReportType type);
}
