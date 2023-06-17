package com.co.kr.modyeo.api.schedule.repository.custom.impl;

import com.co.kr.modyeo.api.schedule.domain.entity.MemberScheduler;
import com.co.kr.modyeo.api.schedule.repository.custom.MemberSchedulerCustomRepository;
import com.co.kr.modyeo.common.support.Querydsl4RepositorySupport;

import static com.co.kr.modyeo.api.schedule.domain.entity.QMemberScheduler.memberScheduler;

public class MemberSchedulerRepositoryImpl extends Querydsl4RepositorySupport implements MemberSchedulerCustomRepository {
    public MemberSchedulerRepositoryImpl() {
        super(MemberScheduler.class);
    }

    @Override
    public MemberScheduler findBySchedulerIdAndMemberId(Long memberId, Long schedulerId) {
        return selectFrom(memberScheduler)
                .where(memberScheduler.member.id.eq(memberId),
                        memberScheduler.scheduler.id.eq(schedulerId))
                .fetchOne();
    }
}
