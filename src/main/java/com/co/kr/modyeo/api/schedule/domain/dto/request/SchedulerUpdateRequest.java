package com.co.kr.modyeo.api.schedule.domain.dto.request;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class SchedulerUpdateRequest {

    private Long schedulerId;

    private String title;

    private String content;

    private LocalDateTime meetingDate;
}
