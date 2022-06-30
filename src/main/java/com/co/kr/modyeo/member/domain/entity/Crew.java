package com.co.kr.modyeo.member.domain.entity;

import com.co.kr.modyeo.member.domain.entity.link.CrewCategory;
import com.co.kr.modyeo.member.domain.entity.link.MemberCrew;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@Table(name = "CREW")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Crew extends BaseEntity{

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "crew_id")
    private Long id;

    @Column(name = "crew_name")
    private String name;

    @OneToMany(mappedBy = "crew",cascade = CascadeType.ALL)
    private List<CrewCategory> categoryList = new ArrayList<>();

    @Builder(builderMethodName = "of",builderClassName = "of")
    public Crew(Long id, String name, List<CrewCategory> categoryList) {
        this.id = id;
        this.name = name;
        this.categoryList = categoryList;
    }
}
