package com.co.kr.modyeo.api.schedule.domain.dto.request;

import com.co.kr.modyeo.common.dto.SearchDto;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.domain.Sort;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class SchedulerSearch extends SearchDto {

    private Long categoryId;

    private Long emdAreaId;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime startTime;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime endTime;

    private List<DayOfWeek> dayOfWeeks;

    @Builder(builderClassName = "of",builderMethodName = "of")
    public SchedulerSearch(Integer limit,
                           Integer offset,
                           String orderBy,
                           Sort.Direction direction,
                           Long categoryId,
                           Long emdAreaId,
                           LocalDateTime startTime,
                           LocalDateTime endTime,
                           List<DayOfWeek> dayOfWeeks) {
        super(limit, offset, orderBy, direction);
        this.categoryId = categoryId;
        this.emdAreaId = emdAreaId;
        this.startTime = startTime;
        this.endTime = endTime;
        this.dayOfWeeks = dayOfWeeks;
    }
}
