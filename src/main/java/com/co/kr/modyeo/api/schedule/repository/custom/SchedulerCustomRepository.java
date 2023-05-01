package com.co.kr.modyeo.api.schedule.repository.custom;

import com.co.kr.modyeo.api.schedule.domain.dto.request.SchedulerSearch;
import com.co.kr.modyeo.api.schedule.domain.entity.Scheduler;

import java.util.List;

public interface SchedulerCustomRepository {
    List<Scheduler> searchScheduler(SchedulerSearch schedulerSearch);
}
