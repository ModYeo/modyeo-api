package com.co.kr.modyeo.member.domain.entity;

import com.co.kr.modyeo.member.domain.entity.Crew;
import com.co.kr.modyeo.member.domain.entity.Member;
import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
@Table(name = "MEMBER_CREW")
public class MemberCrew {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_crew_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne
    @JoinColumn(name = "crew_id")
    private Crew crew;
}
