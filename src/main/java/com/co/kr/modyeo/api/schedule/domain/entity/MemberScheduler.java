package com.co.kr.modyeo.api.schedule.domain.entity;

import com.co.kr.modyeo.api.member.domain.entity.Member;
import com.co.kr.modyeo.api.schedule.domain.entity.enumurate.ApplicationType;
import com.co.kr.modyeo.common.entity.BaseEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@Table(name = "MEMBER_SCHEDULER")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MemberScheduler extends BaseEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_scheduler_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "scheduler_id")
    private Scheduler scheduler;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "application_type")
    private ApplicationType applicationType;

    @Builder(builderClassName = "of",builderMethodName = "of")
    public MemberScheduler(Long id, Member member, Scheduler scheduler, ApplicationType applicationType) {
        this.id = id;
        this.member = member;
        this.scheduler = scheduler;
        this.applicationType = applicationType;
    }

    @Builder(builderClassName = "madeBuilder",builderMethodName = "madeBuilder")
    public static MemberScheduler made(Member member, Scheduler scheduler){
        return of()
                .member(member)
                .scheduler(scheduler)
                .applicationType(ApplicationType.MADE)
                .build();
    }

    @Builder(builderClassName = "waitBuilder",builderMethodName = "waitBuilder")
    public static MemberScheduler wait(Member member, Scheduler scheduler){
        return of()
                .member(member)
                .scheduler(scheduler)
                .applicationType(ApplicationType.WAIT)
                .build();
    }

    @Builder(builderClassName = "approveBuilder",builderMethodName = "approveBuilder")
    public static MemberScheduler approve(Member member,Scheduler scheduler){
        return of()
                .member(member)
                .scheduler(scheduler)
                .applicationType(ApplicationType.APPROVE)
                .build();
    }

    public void changeApplicationType(ApplicationType applicationType){
        this.applicationType = applicationType;
    }
}
