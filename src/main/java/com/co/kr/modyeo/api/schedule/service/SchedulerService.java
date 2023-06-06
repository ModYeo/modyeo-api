package com.co.kr.modyeo.api.schedule.service;

import com.co.kr.modyeo.api.schedule.domain.dto.request.SchedulerCreateRequest;
import com.co.kr.modyeo.api.schedule.domain.dto.request.SchedulerSearch;
import com.co.kr.modyeo.api.schedule.domain.dto.request.SchedulerStatusRequest;
import com.co.kr.modyeo.api.schedule.domain.dto.request.SchedulerUpdateRequest;
import com.co.kr.modyeo.api.schedule.domain.dto.response.SchedulerDetail;
import com.co.kr.modyeo.api.schedule.domain.dto.response.SchedulerResponse;
import org.apache.tomcat.util.http.fileupload.util.LimitedInputStream;
import org.springframework.data.domain.Slice;

import java.util.List;

public interface SchedulerService {
    Long createScheduler(SchedulerCreateRequest schedulerCreateRequest, Long memberId);

    Slice<SchedulerResponse> getSchedulers(SchedulerSearch schedulerSearch);

    SchedulerDetail getScheduler(Long schedulerId);

    void deleteScheduler(Long schedulerId);

    Long updateScheduler(SchedulerUpdateRequest schedulerUpdateRequest);

    Long updateStatus(SchedulerStatusRequest schedulerStatusRequest);
}
