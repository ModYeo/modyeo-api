package com.co.kr.modyeo.api.schedule.domain.dto.request;

import com.co.kr.modyeo.api.category.domain.entity.Category;
import com.co.kr.modyeo.api.geo.domain.entity.EmdArea;
import com.co.kr.modyeo.api.schedule.domain.entity.Scheduler;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Getter
public class SchedulerCreateRequest {

    @NotNull
    private Long categoryId;

    @NotNull
    private Long emdAreaId;

    @NotNull
    private String title;

    private String content;

    @NotNull
    @JsonFormat
    private LocalDateTime meetingDate;

    @Builder(builderClassName = "of",builderMethodName = "of")
    public SchedulerCreateRequest(Long categoryId, Long emdAreaId, String title, String content, LocalDateTime meetingDate) {
        this.categoryId = categoryId;
        this.emdAreaId = emdAreaId;
        this.title = title;
        this.content = content;
        this.meetingDate = meetingDate;
    }


    public static Scheduler toEntity(SchedulerCreateRequest schedulerCreateRequest, EmdArea emdArea, Category category) {
        return Scheduler.createBuilder()
                .title(schedulerCreateRequest.getTitle())
                .content(schedulerCreateRequest.getContent())
                .meetingDate(schedulerCreateRequest.getMeetingDate())
                .emdArea(emdArea)
                .category(category)
                .build();
    }
}
