package com.co.kr.modyeo.api.schedule.domain.entity;

import com.co.kr.modyeo.api.member.domain.entity.Member;
import com.co.kr.modyeo.api.schedule.domain.entity.enumurate.ApplicationType;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@Table(name = "MEMBER_SCHEDULER")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MemberScheduler {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @ManyToOne(fetch = FetchType.LAZY)
    @Column(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @Column(name = "scheduler_id")
    private Scheduler scheduler;

    @Enumerated(value = EnumType.STRING)
    private ApplicationType applicationType;
}
