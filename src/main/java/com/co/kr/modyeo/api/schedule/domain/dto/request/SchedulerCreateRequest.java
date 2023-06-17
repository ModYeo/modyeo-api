package com.co.kr.modyeo.api.schedule.domain.dto.request;

import com.co.kr.modyeo.api.category.domain.entity.Category;
import com.co.kr.modyeo.api.geo.domain.entity.EmdArea;
import com.co.kr.modyeo.api.schedule.domain.entity.Scheduler;
import com.co.kr.modyeo.api.schedule.domain.entity.enumurate.SchedulerType;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class SchedulerCreateRequest {

    @NotNull
    private Long categoryId;

    @NotNull
    private Long emdAreaId;

    @NotNull
    private String title;

    private String content;

    private String imagePath;

    @NotNull
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
    private LocalDateTime meetingDate;

    private String meetingPlace;

    private SchedulerType schedulerType;

    private int recruitmentCount;

    @Builder(builderClassName = "of",builderMethodName = "of")
    public SchedulerCreateRequest(Long categoryId, Long emdAreaId, String title, String content, String imagePath, LocalDateTime meetingDate, String meetingPlace, SchedulerType schedulerType, int recruitmentCount) {
        this.categoryId = categoryId;
        this.emdAreaId = emdAreaId;
        this.title = title;
        this.content = content;
        this.imagePath = imagePath;
        this.meetingDate = meetingDate;
        this.meetingPlace = meetingPlace;
        this.schedulerType = schedulerType;
        this.recruitmentCount = recruitmentCount;
    }

    public static Scheduler toEntity(SchedulerCreateRequest schedulerCreateRequest, EmdArea emdArea, Category category) {
        return Scheduler.createBuilder()
                .title(schedulerCreateRequest.getTitle())
                .content(schedulerCreateRequest.getContent())
                .meetingDate(schedulerCreateRequest.getMeetingDate())
                .imagePath(schedulerCreateRequest.getImagePath())
                .meetingPlace(schedulerCreateRequest.getMeetingPlace())
                .schedulerType(schedulerCreateRequest.getSchedulerType())
                .recruitmentCount(schedulerCreateRequest.getRecruitmentCount())
                .emdArea(emdArea)
                .category(category)
                .build();
    }
}
