package com.co.kr.modyeo.api.schedule.domain.factory;

import com.co.kr.modyeo.api.schedule.domain.dto.response.SchedulerDetail;
import com.co.kr.modyeo.api.schedule.domain.entity.MemberScheduler;
import com.co.kr.modyeo.api.schedule.domain.entity.Scheduler;
import com.co.kr.modyeo.api.schedule.domain.entity.enumurate.ApplicationType;

import java.util.Objects;
import java.util.Optional;

public class SchedulerFactory {
    public static SchedulerDetail makeDto(Scheduler scheduler, Long memberId) {
        Optional<MemberScheduler> memberSchedulerOptional = scheduler.getMemberSchedulerList().stream().filter(memberScheduler -> Objects.equals(memberScheduler.getMember().getId(), memberId)).findFirst();
        if (memberSchedulerOptional.isPresent()){
            MemberScheduler memberScheduler = memberSchedulerOptional.get();
            if (memberScheduler.getApplicationType() == ApplicationType.MADE) {
                return SchedulerDetail.toDtoForHost(scheduler);
            }
        }
        return SchedulerDetail.toDtoForGuest(scheduler);
    }
}
