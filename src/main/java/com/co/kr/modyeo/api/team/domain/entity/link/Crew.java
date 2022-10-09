package com.co.kr.modyeo.api.team.domain.entity.link;

import com.co.kr.modyeo.api.team.domain.entity.enumerate.CrewLevel;
import com.co.kr.modyeo.common.entity.BaseEntity;
import com.co.kr.modyeo.api.member.domain.entity.Member;
import com.co.kr.modyeo.api.team.domain.entity.Team;
import com.co.kr.modyeo.common.enumerate.Yn;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@Table(name = "CREW")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Crew extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "crew_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "team_id")
    private Team team;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "crew_level")
    private CrewLevel crewLevel;

    @Column(name = "is_activated")
    @Enumerated(value = EnumType.STRING)
    private Yn isActivated;

    @Builder(builderClassName = "of",builderMethodName = "of")
    public Crew(Long id, Member member, Team team, CrewLevel crewLevel, Yn isActivated) {
        this.id = id;
        this.member = member;
        this.team = team;
        this.crewLevel = crewLevel;
        this.isActivated = isActivated;
    }

    @Builder(builderClassName = "createCrewBuilder", builderMethodName = "createCrewBuilder")
    public static Crew createCrew(Member member, Team team) {
        return Crew.of()
                .member(member)
                .team(team)
                .crewLevel(CrewLevel.NORMAL)
                .isActivated(Yn.Y)
                .build();
    }

    @Builder(builderClassName = "createOwnerBuilder", builderMethodName = "createOwnerBuilder")
    public static Crew createOwner(Member member,Team team){
        return Crew.of()
                .member(member)
                .team(team)
                .crewLevel(CrewLevel.OWNER)
                .isActivated(Yn.Y)
                .build();
    }

    public static boolean checkAuth(CrewLevel crewLevel) {
        return crewLevel != CrewLevel.NORMAL;
    }

    public void changeLevel(CrewLevel crewLevel){
        this.crewLevel = crewLevel;
    }

    public void inactiveCrew() {
        this.isActivated = Yn.N;
    }
}
