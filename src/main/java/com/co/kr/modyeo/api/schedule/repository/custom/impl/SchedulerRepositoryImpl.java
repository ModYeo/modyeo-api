package com.co.kr.modyeo.api.schedule.repository.custom.impl;

import com.co.kr.modyeo.api.schedule.domain.dto.request.SchedulerSearch;
import com.co.kr.modyeo.api.schedule.domain.entity.Scheduler;
import com.co.kr.modyeo.api.schedule.repository.custom.SchedulerCustomRepository;
import com.co.kr.modyeo.common.support.Querydsl4RepositorySupport;

import java.util.List;

public class SchedulerRepositoryImpl extends Querydsl4RepositorySupport implements SchedulerCustomRepository {

    public SchedulerRepositoryImpl() {
        super(Scheduler.class);
    }

    @Override
    public List<Scheduler> searchScheduler(SchedulerSearch schedulerSearch) {
        return null;
    }
}
