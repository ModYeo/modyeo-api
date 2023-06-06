package com.co.kr.modyeo.api.schedule.domain.dto.request;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class SchedulerUpdateRequest {

    private Long schedulerId;

    private String title;

    private String content;

    private Long categoryId;

    private String imagePath;

    private Integer recruitmentCount;

    public SchedulerUpdateRequest(Long schedulerId, String title, String content, Long categoryId, String imagePath, Integer recruitmentCount) {
        this.schedulerId = schedulerId;
        this.title = title;
        this.content = content;
        this.categoryId = categoryId;
        this.imagePath = imagePath;
        this.recruitmentCount = recruitmentCount;
    }
}
