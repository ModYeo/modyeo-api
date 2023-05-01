package com.co.kr.modyeo.api.schedule.service;

import com.co.kr.modyeo.api.schedule.domain.dto.request.SchedulerCreateRequest;
import com.co.kr.modyeo.api.schedule.domain.dto.request.SchedulerSearch;
import com.co.kr.modyeo.api.schedule.domain.dto.response.SchedulerDetail;
import com.co.kr.modyeo.api.schedule.domain.dto.response.SchedulerResponse;
import org.apache.tomcat.util.http.fileupload.util.LimitedInputStream;

import java.util.List;

public interface SchedulerService {
    Long createScheduler(SchedulerCreateRequest schedulerCreateRequest, Long memberId);

    List<SchedulerResponse> getSchedulers(SchedulerSearch schedulerSearch);

    SchedulerDetail getScheduler(Long schedulerId);
}
