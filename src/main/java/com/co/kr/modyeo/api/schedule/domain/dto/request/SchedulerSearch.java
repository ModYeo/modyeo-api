package com.co.kr.modyeo.api.schedule.domain.dto.request;

import com.co.kr.modyeo.common.dto.SearchDto;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Getter;
import org.springframework.data.domain.Sort;

import java.time.LocalDate;

@Getter
public class SchedulerSearch extends SearchDto {

    private Long categoryId;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
    private LocalDate startTime;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
    private LocalDate endTime;

    @Builder(builderClassName = "of",builderMethodName = "of")
    public SchedulerSearch(Integer limit, Integer offset, String orderBy, Sort.Direction direction, Long categoryId, LocalDate startTime, LocalDate endTime) {
        super(limit, offset, "meetingDate", Sort.Direction.ASC);
        this.categoryId = categoryId;
        this.startTime = startTime;
        this.endTime = endTime;
    }
}
