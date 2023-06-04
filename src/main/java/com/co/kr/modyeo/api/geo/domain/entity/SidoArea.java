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
@Table(name = "SIDO_AREA")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SidoArea {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "sido_area_id")
    private Long id;

    @Column(name = "sido_area_code")
    private String code;

    @Column(name = "sido_area_name")
    private String name;

    private String version;

    @OneToMany(mappedBy = "sidoArea")
    private List<SiggArea> siggAreaList = new ArrayList<>();

    @Builder(builderClassName = "of", builderMethodName = "of")
    public SidoArea(Long id, String code, String name, String version, List<SiggArea> siggAreaList) {
        this.id = id;
        this.code = code;
        this.name = name;
        this.version = version;
        this.siggAreaList = siggAreaList;
    }
}
