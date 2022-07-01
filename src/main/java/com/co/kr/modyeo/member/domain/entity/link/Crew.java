package com.co.kr.modyeo.member.domain.entity.link;

import com.co.kr.modyeo.member.domain.entity.BaseEntity;
import com.co.kr.modyeo.member.domain.entity.Member;
import com.co.kr.modyeo.member.domain.entity.Team;
import com.co.kr.modyeo.member.domain.enumerate.Level;
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
    private Level level;

    @Column(name = "is_activated")
    private Boolean isActivated;

    @Builder(builderClassName = "of",builderMethodName = "of")
    public Crew(Long id, Member member, Team team, Level level, Boolean isActivated) {
        this.id = id;
        this.member = member;
        this.team = team;
        this.level = level;
        this.isActivated = isActivated;
    }

    @Builder(builderClassName = "createCrewBuilder", builderMethodName = "createCrewBuilder")
    public static Crew createCrew(Member member, Team team) {
        return Crew.of()
                .member(member)
                .team(team)
                .level(Level.NORMAL)
                .isActivated(true)
                .build();
    }

    @Builder(builderClassName = "createOwnerBuilder", builderMethodName = "createOwnerBuilder")
    public static Crew createOwner(Member member,Team team){
        return Crew.of()
                .member(member)
                .team(team)
                .level(Level.OWNER)
                .isActivated(true)
                .build();
    }

    public void changeLevel(Level level){
        this.level = level;
    }
}
