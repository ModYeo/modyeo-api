package com.co.kr.modyeo.api.team.domain.entity;

import com.co.kr.modyeo.api.geo.domain.entity.EmdArea;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@Table(name = "TEAM_ACTIVE_AREA")
@NoArgsConstructor
public class TeamActivArea {
    //1.활동지역아이디
    @Id
    @GeneratedValue
    @Column(name="team_active_area_id")
    private Long activeId;
    //2.팀아이디
    @ManyToOne
    @JoinColumn(name="team_id")
    private Team team;
    //3.시도행정지역아이디
    @ManyToOne
    @JoinColumn(name="emd_area_id")
    private EmdArea emdArea;
    //4.활동범위(위도, 경도 표시)
    @Column(name="limit_meters")
    private Integer limitMeters;

    @Builder(builderClassName = "of", builderMethodName = "of")
    public TeamActivArea(Long activeId, Team team, EmdArea emdArea, Integer limitMeters){
        this.activeId = activeId;
        this.team = team;
        this.emdArea = emdArea;
        this.limitMeters = limitMeters;
    }

    @Builder(builderClassName = "createTeamAreaBuilder", builderMethodName = "createTeamAreaBuilder")
    public static TeamActivArea createTeamArea(Team team, EmdArea emdArea, Integer limitMeters){
        return TeamActivArea.of().
                team(team).
                emdArea(emdArea).
                limitMeters(limitMeters).
                build();
    }

    @Builder(builderClassName = "updateTeamAreaBuilder", builderMethodName = "updateTeamAreaBuilder")
    public void updateTeamActiveArea(Integer limitMeters){
        this.limitMeters = limitMeters;
    }
}
