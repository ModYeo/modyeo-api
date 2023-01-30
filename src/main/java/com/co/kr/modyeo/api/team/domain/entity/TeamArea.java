package com.co.kr.modyeo.api.team.domain.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@Table
@NoArgsConstructor
public class TeamArea {
    //1.활동지역아이디
    @Id
    @GeneratedValue
    @Column(name="team_active_area_id")
    private Long activeId;
    //2.팀아이디
    @ManyToOne
    @Column(name="team_id")
    private Team team;
    //3.시도행정지역아이디
    @Column(name="emd_area_id")
    private Long emdId;
    //4.활동범위(위도, 경도 표시)
    @Column(name="limit_meters")
    private Long limitMeters;

    @Builder(builderClassName = "of", builderMethodName = "of")
    public TeamArea(Long activeId, Team team, Long emdId, Long limitMeters){
        this.activeId = activeId;
        this.team = team;
        this.emdId = emdId;
        this.limitMeters = limitMeters;
    }

    @Builder(builderClassName = "createTeamAreaBuilder", builderMethodName = "createTeamAreaBuilder")
    public static TeamArea createTeamArea(Team team, Long emdId, Long limitMeters){
        return TeamArea.of().
                team(team).
                emdId(emdId).
                limitMeters(limitMeters).
                build();
    }

    @Builder(builderClassName = "changeTeamAreaBuilder", builderMethodName = "changeTeamAreaBuilder")
    public void changeTeamActiveArea(Long emdId, Long limitMeters){
        this.emdId = emdId;
        this.limitMeters = limitMeters;
    }
}
