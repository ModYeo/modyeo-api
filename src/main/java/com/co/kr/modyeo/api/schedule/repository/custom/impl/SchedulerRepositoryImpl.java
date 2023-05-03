package com.co.kr.modyeo.api.schedule.repository.custom.impl;

import com.co.kr.modyeo.api.schedule.domain.dto.request.SchedulerSearch;
import com.co.kr.modyeo.api.schedule.domain.entity.Scheduler;
import com.co.kr.modyeo.api.schedule.repository.custom.SchedulerCustomRepository;
import com.co.kr.modyeo.common.support.Querydsl4RepositorySupport;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.Expressions;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import static com.co.kr.modyeo.api.category.domain.entity.QCategory.category;
import static com.co.kr.modyeo.api.geo.domain.entity.QEmdArea.emdArea;
import static com.co.kr.modyeo.api.schedule.domain.entity.QScheduler.scheduler;

public class SchedulerRepositoryImpl extends Querydsl4RepositorySupport implements SchedulerCustomRepository {

    public SchedulerRepositoryImpl() {
        super(Scheduler.class);
    }

    @Override
    public List<Scheduler> searchScheduler(SchedulerSearch schedulerSearch) {
        return selectFrom(scheduler)
                .innerJoin(scheduler.category, category)
                .innerJoin(scheduler.emdArea, emdArea)
                .fetchJoin()
                .where(searchDateFilter(schedulerSearch.getStartTime(),schedulerSearch.getEndTime()))
                .fetch();
    }

    private BooleanExpression searchDateFilter(LocalDate startTime, LocalDate endTime) {
        BooleanExpression isGoeStartDate = scheduler.meetingDate.goe(LocalDateTime.of(startTime, LocalTime.MIN));
        BooleanExpression isLoeEndDate = scheduler.meetingDate.loe(LocalDateTime.of(endTime,LocalTime.MAX).withNano(0));

        return Expressions.allOf(isGoeStartDate,isLoeEndDate);
    }
}
