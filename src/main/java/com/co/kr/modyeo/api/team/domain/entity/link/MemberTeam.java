package com.co.kr.modyeo.api.team.domain.entity.link;

import com.co.kr.modyeo.api.team.domain.entity.Team;
import com.co.kr.modyeo.api.team.domain.entity.enumerate.JoinStatus;
import com.co.kr.modyeo.common.entity.BaseEntity;
import com.co.kr.modyeo.api.member.domain.entity.Member;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@Table(name = "MEMBER_TEAM")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MemberTeam extends BaseEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_team_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "team_id")
    private Team team;

    @Column(name = "join_status")
    @Enumerated(EnumType.STRING)
    private JoinStatus joinStatus;

    @Builder(builderClassName = "of",builderMethodName = "of")
    public MemberTeam(Long id, Member member, Team team, JoinStatus joinStatus) {
        this.id = id;
        this.member = member;
        this.team = team;
        this.joinStatus = joinStatus;
    }

    @Builder(builderClassName = "joinApplicationBuilder",builderMethodName = "joinApplicationBuilder")
    public static MemberTeam MemberCrew(Member member, Team team) {
        return MemberTeam.of()
                .team(team)
                .member(member)
                .joinStatus(JoinStatus.APPLICATION)
                .build();
    }

    public void chanegeDenial() {
        this.joinStatus = JoinStatus.DENIAL;
    }
}
