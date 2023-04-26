package com.co.kr.modyeo.api.schedule.domain.entity;

import com.co.kr.modyeo.api.member.domain.entity.Member;
import com.co.kr.modyeo.api.schedule.domain.entity.enumurate.ApplicationType;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@Table(name = "MEMBER_SCHEDULER")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MemberScheduler {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @Column(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @Column(name = "scheduler_id")
    private Scheduler scheduler;

    @Enumerated(value = EnumType.STRING)
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
}
