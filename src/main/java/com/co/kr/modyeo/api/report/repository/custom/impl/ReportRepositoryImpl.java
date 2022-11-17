package com.co.kr.modyeo.api.report.repository.custom.impl;

import com.co.kr.modyeo.api.report.domain.entity.Report;
import com.co.kr.modyeo.api.report.repository.custom.ReportCustomRepository;
import com.co.kr.modyeo.common.support.Querydsl4RepositorySupport;

public class ReportRepositoryImpl extends Querydsl4RepositorySupport implements ReportCustomRepository {

    public ReportRepositoryImpl() {
        super(Report.class);
    }
}
