package com.co.kr.modyeo.api.geo.domain.entity;

import lombok.AccessLevel;
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

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "sido_area_id")
    private Long id;

    @Column(name = "sido_area_code")
    private String code;

    @Column(name = "sido_area_name")
    private String name;

    private String version;

    @OneToMany(mappedBy = "sidoArea")
    private List<SiggArea> siggAreaList = new ArrayList<>();
}
