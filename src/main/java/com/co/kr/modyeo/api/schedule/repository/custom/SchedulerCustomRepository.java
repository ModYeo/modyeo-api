package com.co.kr.modyeo.api.schedule.repository.custom;

import com.co.kr.modyeo.api.schedule.domain.dto.request.SchedulerSearch;
import com.co.kr.modyeo.api.schedule.domain.entity.Scheduler;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;

import java.util.List;

public interface SchedulerCustomRepository {
    Slice<Scheduler> searchScheduler(SchedulerSearch schedulerSearch, PageRequest pageRequest);
}
