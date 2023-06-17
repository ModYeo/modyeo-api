package com.co.kr.modyeo.api.schedule.repository.custom;

import com.co.kr.modyeo.api.schedule.domain.entity.MemberScheduler;

public interface MemberSchedulerCustomRepository {
    MemberScheduler findBySchedulerIdAndMemberId(Long memberId, Long schedulerId);
}
