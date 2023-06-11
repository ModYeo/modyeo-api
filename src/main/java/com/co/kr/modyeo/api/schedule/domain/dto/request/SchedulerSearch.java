package com.co.kr.modyeo.api.schedule.domain.dto.request;

import com.co.kr.modyeo.common.dto.SearchDto;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Getter;
import org.springframework.data.domain.Sort;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;

@Getter
public class SchedulerSearch extends SearchDto {

    private Long categoryId;

    private Long emdAreaId;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
    private LocalDate startTime;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
    private LocalDate endTime;

    private List<DayOfWeek> dayOfWeeks;

    @Builder(builderClassName = "of",builderMethodName = "of")
    public SchedulerSearch(Integer limit,
                           Integer offset,
                           String orderBy,
                           Sort.Direction direction,
                           Long categoryId,
                           Long emdAreaId,
                           LocalDate startTime,
                           LocalDate endTime,
                           List<DayOfWeek> dayOfWeeks) {
        super(limit, offset, orderBy, direction);
        this.categoryId = categoryId;
        this.emdAreaId = emdAreaId;
        this.startTime = startTime;
        this.endTime = endTime;
        this.dayOfWeeks = dayOfWeeks;
    }
}
