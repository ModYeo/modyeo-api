package com.co.kr.modyeo.api.schedule.domain.dto.response;

import com.co.kr.modyeo.api.category.domain.dto.response.CategoryResponse;
import com.co.kr.modyeo.api.geo.domain.dto.response.EmdAreaDetail;
import com.co.kr.modyeo.api.schedule.domain.entity.Scheduler;
import com.co.kr.modyeo.api.schedule.domain.entity.enumurate.ApplicationType;
import com.co.kr.modyeo.api.schedule.domain.entity.enumurate.SchedulerStatus;
import com.co.kr.modyeo.api.schedule.domain.entity.enumurate.SchedulerType;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class SchedulerResponse {

    private Long schedulerId;

    private String title;

    private String content;

    private String imagePath;

    private String meetingPlace;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
    private LocalDateTime meetingDate;

    private EmdAreaDetail emdArea;

    private CategoryResponse category;

    private int applicantCount;

    private int recruitmentCount;

    private SchedulerStatus schedulerStatus;

    private SchedulerType schedulerType;

    @Builder(builderClassName = "of", builderMethodName = "of")
    public SchedulerResponse(
            Long schedulerId,
            String title,
            String content,
            String imagePath,
            String meetingPlace,
            LocalDateTime meetingDate,
            EmdAreaDetail emdArea,
            CategoryResponse category,
            int applicantCount,
            int recruitmentCount,
            SchedulerStatus schedulerStatus,
            SchedulerType schedulerType) {
        this.schedulerId = schedulerId;
        this.title = title;
        this.content = content;
        this.imagePath = imagePath;
        this.meetingPlace = meetingPlace;
        this.meetingDate = meetingDate;
        this.emdArea = emdArea;
        this.category = category;
        this.applicantCount = applicantCount;
        this.recruitmentCount = recruitmentCount;
        this.schedulerStatus = schedulerStatus;
        this.schedulerType = schedulerType;
    }

    public static SchedulerResponse toDto(Scheduler scheduler) {
        return of()
                .schedulerId(scheduler.getId())
                .title(scheduler.getTitle())
                .content(scheduler.getContent())
                .imagePath(scheduler.getImagePath())
                .meetingPlace(scheduler.getMeetingPlace())
                .meetingDate(scheduler.getMeetingDate())
                .emdArea(EmdAreaDetail.toDto(scheduler.getEmdArea()))
                .category(CategoryResponse.toDto(scheduler.getCategory()))
                .applicantCount((int) scheduler.getMemberSchedulerList()
                        .stream()
                        .filter(memberScheduler -> memberScheduler.getApplicationType().equals(ApplicationType.APPROVE) || memberScheduler.getApplicationType().equals(ApplicationType.MADE))
                        .count())
                .recruitmentCount(scheduler.getRecruitmentCount())
                .schedulerStatus(scheduler.getSchedulerStatus())
                .schedulerType(scheduler.getSchedulerType())
                .build();
    }
}
