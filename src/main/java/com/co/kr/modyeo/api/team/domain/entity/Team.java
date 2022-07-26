package com.co.kr.modyeo.api.team.domain.entity;

import com.co.kr.modyeo.api.team.domain.entity.enumerate.ScaleLevel;
import com.co.kr.modyeo.api.team.domain.entity.link.Crew;
import com.co.kr.modyeo.common.entity.BaseEntity;
import com.co.kr.modyeo.api.team.domain.entity.link.TeamCategory;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@Table(name = "TEAM")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Team extends BaseEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "team_id")
    private Long id;

    @Column(name = "team_name")
    private String name;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "scale_level")
    private ScaleLevel scaleLevel;

    @OneToMany(mappedBy = "team",cascade = CascadeType.ALL)
    private List<TeamCategory> categoryList = new ArrayList<>();

    @OneToMany(mappedBy = "team",cascade = CascadeType.ALL)
    private List<Crew> crewList = new ArrayList<>();

    @Builder(builderMethodName = "of",builderClassName = "of")
    public Team(Long id, String name,ScaleLevel scaleLevel, List<TeamCategory> categoryList, List<Crew> crewList) {
        this.id = id;
        this.name = name;
        this.scaleLevel =scaleLevel;
        this.categoryList = categoryList;
        this.crewList = crewList;
    }

    @Builder(builderClassName = "createTeamBuilder", builderMethodName = "createTeamBuilder")
    public static Team createTeam(String name){
        return Team.of()
                .name(name)
                .scaleLevel(ScaleLevel.TEMP)
                .build();
    }

    public void changeTeamInfo(String name){
        this.name = name;
    }

    public void upScaleLevel(Integer teamSize){
        if (teamSize >= 3){
            this.scaleLevel = ScaleLevel.SMALL;
        } else if (teamSize >= 20) {
            this.scaleLevel = ScaleLevel.MEDIUM;
        } else if (teamSize >= 50) {
            this.scaleLevel = ScaleLevel.BIG;
        } else if (teamSize >= 100) {
            this.scaleLevel = ScaleLevel.GIANT;
        }
    }
}
