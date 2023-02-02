package com.co.kr.modyeo.api.geo.domain.entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.geo.Point;

import javax.persistence.*;

@Getter
@Entity
@Table(name = "EMD_AREA")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class EmdArea {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "emd_area_id")
    private Long id;

    @Column(name = "emd_area_code")
    private String code;

    @Column(name = "emd_area_name")
    private String name;

    private String version;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sigg_area_id")
    private SiggArea siggArea;

    @Builder(builderClassName = "of",builderMethodName = "of")
    public EmdArea(Long id, String code, String name, String version, SiggArea siggArea) {
        this.id = id;
        this.code = code;
        this.name = name;
        this.version = version;
        this.siggArea = siggArea;
    }
}
