package com.co.kr.modyeo.api.schedule.service.factory;

import com.co.kr.modyeo.api.member.domain.entity.Member;
import com.co.kr.modyeo.api.schedule.domain.entity.MemberScheduler;
import com.co.kr.modyeo.api.schedule.domain.entity.Scheduler;
import com.co.kr.modyeo.common.exception.ApiException;
import org.springframework.http.HttpStatus;

public class MemberSchedulerFactory {
    public static MemberScheduler createMemberScheduler(Member member, Scheduler scheduler) {
        switch (scheduler.getSchedulerType()){
            case APPR:
                return MemberScheduler.waitBuilder()
                        .member(member)
                        .scheduler(scheduler)
                        .build();
            case FREE:
                return MemberScheduler.approveBuilder()
                        .member(member)
                        .scheduler(scheduler)
                        .build();
            default:
                throw ApiException.builder()
                        .status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .errorMessage("")
                        .errorCode("")
                        .build();
        }
    }
}
