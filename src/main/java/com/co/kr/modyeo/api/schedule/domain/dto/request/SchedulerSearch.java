package com.co.kr.modyeo.api.schedule.domain.dto.request;

import com.co.kr.modyeo.common.dto.SearchDto;
import com.fasterxml.jackson.annotation.JsonFormat;
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

    public SchedulerSearch(Integer limit, Integer offset, String orderBy, Sort.Direction direction, Long categoryId, LocalDate startTime, LocalDate endTime) {
        super(limit, offset, orderBy, direction);
        this.categoryId = categoryId;
        this.startTime = startTime;
        this.endTime = endTime;
    }
}
