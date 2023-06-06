package com.co.kr.modyeo.api.schedule.domain.dto.request;

import com.co.kr.modyeo.api.schedule.domain.entity.enumurate.SchedulerStatus;
import lombok.Getter;

@Getter
public class SchedulerStatusRequest {

    private Long schedulerId;

    private SchedulerStatus schedulerStatus;

    public SchedulerStatusRequest(Long schedulerId, SchedulerStatus schedulerStatus) {
        this.schedulerId = schedulerId;
        this.schedulerStatus = schedulerStatus;
    }
}
