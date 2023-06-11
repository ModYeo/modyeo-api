package com.co.kr.modyeo.api.schedule.repository.custom.impl;

import com.co.kr.modyeo.api.schedule.domain.dto.request.SchedulerSearch;
import com.co.kr.modyeo.api.schedule.domain.entity.Scheduler;
import com.co.kr.modyeo.api.schedule.repository.custom.SchedulerCustomRepository;
import com.co.kr.modyeo.common.support.Querydsl4RepositorySupport;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.Expressions;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

import static com.co.kr.modyeo.api.category.domain.entity.QCategory.category;
import static com.co.kr.modyeo.api.geo.domain.entity.QEmdArea.emdArea;
import static com.co.kr.modyeo.api.schedule.domain.entity.QScheduler.scheduler;

public class SchedulerRepositoryImpl extends Querydsl4RepositorySupport implements SchedulerCustomRepository {

    public SchedulerRepositoryImpl() {
        super(Scheduler.class);
    }

    @Override
    public Slice<Scheduler> searchScheduler(SchedulerSearch schedulerSearch, PageRequest pageRequest) {
        return applySlicing(pageRequest, contentQuery -> contentQuery.selectFrom(scheduler)
                        .innerJoin(scheduler.category, category)
                        .innerJoin(scheduler.emdArea, emdArea)
                        .fetchJoin()
                        .where(
                                searchDateFilter(schedulerSearch.getStartTime(), schedulerSearch.getEndTime()),
                                categoryIdEq(schedulerSearch.getCategoryId()),
                                emdAreaIdEq(schedulerSearch.getEmdAreaId()),
                                dayOfWeekIn(schedulerSearch.getDayOfWeeks())));
    }

    private BooleanExpression dayOfWeekIn(List<DayOfWeek> dayOfWeeks) {
        return scheduler.meetingDate.dayOfWeek().in(dayOfWeeks.stream().map(DayOfWeek::getValue).collect(Collectors.toList()));
    }

    private BooleanExpression emdAreaIdEq(Long emdAreaId) {
        return emdAreaId != null? emdArea.id.eq(emdAreaId) : null;
    }

    private BooleanExpression categoryIdEq(Long categoryId) {
        return categoryId != null? category.id.eq(categoryId) : null;
    }

    @Override
    public Integer findRecruitmentCountBySchedulerId(Long schedulerId) {
        return select(scheduler.recruitmentCount)
                .from(scheduler)
                .where(scheduler.id.eq(schedulerId))
                .fetchOne();
    }

    private BooleanExpression searchDateFilter(LocalDateTime startTime, LocalDateTime endTime) {
        BooleanExpression isGoeStartDate = scheduler.meetingDate.goe(startTime);
        BooleanExpression isLoeEndDate = scheduler.meetingDate.loe(endTime);

        return Expressions.allOf(isGoeStartDate, isLoeEndDate);
    }
}
