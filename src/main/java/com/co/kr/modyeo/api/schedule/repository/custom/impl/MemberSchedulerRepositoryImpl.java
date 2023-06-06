package com.co.kr.modyeo.api.schedule.repository.custom.impl;

import com.co.kr.modyeo.api.schedule.domain.entity.MemberScheduler;
import com.co.kr.modyeo.api.schedule.repository.custom.MemberSchedulerCustomRepository;
import com.co.kr.modyeo.common.support.Querydsl4RepositorySupport;

public class MemberSchedulerRepositoryImpl extends Querydsl4RepositorySupport implements MemberSchedulerCustomRepository {
    public MemberSchedulerRepositoryImpl() {
        super(MemberScheduler.class);
    }
}
