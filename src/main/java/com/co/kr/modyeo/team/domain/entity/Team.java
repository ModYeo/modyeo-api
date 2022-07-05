package com.co.kr.modyeo.team.domain.entity;

import com.co.kr.modyeo.common.entity.BaseEntity;
import com.co.kr.modyeo.team.domain.entity.link.Crew;
import com.co.kr.modyeo.team.domain.entity.link.TeamCategory;
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

    @OneToMany(mappedBy = "team",cascade = CascadeType.ALL)
    private List<TeamCategory> categoryList = new ArrayList<>();

    @OneToMany(mappedBy = "team",cascade = CascadeType.ALL)
    private List<Crew> crewList = new ArrayList<>();

    @Builder(builderMethodName = "of",builderClassName = "of")
    public Team(Long id, String name, List<TeamCategory> categoryList, List<Crew> crewList) {
        this.id = id;
        this.name = name;
        this.categoryList = categoryList;
        this.crewList = crewList;
    }

    public void changeTeamInfo(String name){
        this.name = name;
    }
}
