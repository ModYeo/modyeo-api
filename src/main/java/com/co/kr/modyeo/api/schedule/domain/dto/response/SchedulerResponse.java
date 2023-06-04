package com.co.kr.modyeo.api.schedule.domain.dto.response;

import com.co.kr.modyeo.api.category.domain.dto.response.CategoryResponse;
import com.co.kr.modyeo.api.geo.domain.dto.response.EmdAreaDetail;
import com.co.kr.modyeo.api.schedule.domain.entity.Scheduler;
import com.co.kr.modyeo.api.schedule.domain.entity.enumurate.ApplicationType;
import lombok.Builder;
import lombok.Getter;

@Getter
public class SchedulerResponse {

    private Long schedulerId;

    private String title;

    private String content;

    private EmdAreaDetail emdArea;

    private CategoryResponse category;

    private int applicantCount;

    @Builder(builderClassName = "of",builderMethodName = "of")
    public SchedulerResponse(Long schedulerId, String title, String content, EmdAreaDetail emdArea, CategoryResponse category, int applicantCount) {
        this.schedulerId = schedulerId;
        this.title = title;
        this.content = content;
        this.emdArea = emdArea;
        this.category = category;
        this.applicantCount = applicantCount;
    }

    public static SchedulerResponse toDto(Scheduler scheduler){
        return of()
                .schedulerId(scheduler.getId())
                .title(scheduler.getTitle())
                .content(scheduler.getContent())
                .emdArea(EmdAreaDetail.toDto(scheduler.getEmdArea()))
                .category(CategoryResponse.toDto(scheduler.getCategory()))
                .applicantCount((int) scheduler.getMemberSchedulerList()
                        .stream()
                        .filter(memberScheduler -> memberScheduler.getApplicationType().equals(ApplicationType.APPROVE) || memberScheduler.getApplicationType().equals(ApplicationType.MADE))
                        .count())
                .build();
    }
}
