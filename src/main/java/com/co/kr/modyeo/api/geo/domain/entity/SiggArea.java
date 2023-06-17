package com.co.kr.modyeo.api.geo.domain.entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@Table(name = "SIGG_AREA")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SiggArea {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "sigg_area_id")
    private Long id;

    @Column(name = "sigg_area_code")
    private String code;

    @Column(name = "sigg_area_name")
    private String name;

    private String version;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sido_area_id")
    private SidoArea sidoArea;

    @OneToMany(mappedBy = "siggArea")
    private List<EmdArea> emdAreaList = new ArrayList<>();

    @Builder(builderClassName = "of",builderMethodName = "of")
    public SiggArea(Long id, String code, String name, String version, SidoArea sidoArea, List<EmdArea> emdAreaList) {
        this.id = id;
        this.code = code;
        this.name = name;
        this.version = version;
        this.sidoArea = sidoArea;
        this.emdAreaList = emdAreaList;
    }
}
