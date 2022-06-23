package com.co.kr.modyeo.member.domain.entity.link;

import com.co.kr.modyeo.member.domain.entity.BaseEntity;
import com.co.kr.modyeo.member.domain.entity.Crew;
import com.co.kr.modyeo.member.domain.entity.Member;
import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
@Table(name = "MEMBER_CREW")
public class MemberCrew extends BaseEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_crew_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "crew_id")
    private Crew crew;
}
