package com.co.kr.modyeo.api.schedule.service;

import com.co.kr.modyeo.api.schedule.domain.dto.request.SchedulerCreateRequest;

public interface SchedulerService {
    Long createScheduler(SchedulerCreateRequest schedulerCreateRequest);
}
