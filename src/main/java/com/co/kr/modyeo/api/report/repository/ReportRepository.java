package com.co.kr.modyeo.api.report.repository;

import com.co.kr.modyeo.api.report.domain.entity.Report;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReportRepository extends JpaRepository<Report, Long> {
}
