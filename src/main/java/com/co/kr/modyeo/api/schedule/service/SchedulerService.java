package com.co.kr.modyeo.api.schedule.service;

import com.co.kr.modyeo.api.schedule.domain.dto.request.*;
import com.co.kr.modyeo.api.schedule.domain.dto.response.SchedulerDetail;
import com.co.kr.modyeo.api.schedule.domain.dto.response.SchedulerResponse;
import com.co.kr.modyeo.api.schedule.domain.entity.enumurate.ApplicationType;
import org.springframework.data.domain.Slice;

public interface SchedulerService {
    Long createScheduler(SchedulerCreateRequest schedulerCreateRequest, Long memberId);

    Slice<SchedulerResponse> getSchedulers(SchedulerSearch schedulerSearch);

    SchedulerDetail getScheduler(Long schedulerId, Long memberId);

    void deleteScheduler(Long schedulerId, Long memberId);

    Long updateScheduler(SchedulerUpdateRequest schedulerUpdateRequest, Long memberId);

    Long updateStatus(SchedulerStatusRequest schedulerStatusRequest, Long memberId);

    Long createMemberScheduler(MemberSchedulerCreateRequest memberSchedulerCreateRequest);

    Long updateApplicationType(Long memberSchedulerId, Long hostId, ApplicationType applicationType);
}
