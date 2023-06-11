package com.co.kr.modyeo.api.schedule.service;

import com.co.kr.modyeo.api.schedule.domain.dto.request.*;
import com.co.kr.modyeo.api.schedule.domain.dto.response.SchedulerDetail;
import com.co.kr.modyeo.api.schedule.domain.dto.response.SchedulerResponse;
import org.springframework.data.domain.Slice;

public interface SchedulerService {
    Long createScheduler(SchedulerCreateRequest schedulerCreateRequest, Long memberId);

    Slice<SchedulerResponse> getSchedulers(SchedulerSearch schedulerSearch);

    SchedulerDetail getScheduler(Long schedulerId);

    void deleteScheduler(Long schedulerId, Long memberId);

    Long updateScheduler(SchedulerUpdateRequest schedulerUpdateRequest, Long memberId);

    Long updateStatus(SchedulerStatusRequest schedulerStatusRequest, Long memberId);

    Long createMemberScheduler(MemberSchedulerCreateRequest memberSchedulerCreateRequest);
}
