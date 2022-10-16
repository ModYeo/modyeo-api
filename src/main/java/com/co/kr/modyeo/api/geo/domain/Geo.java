package com.co.kr.modyeo.api.geo.domain;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Entity
@Table(name = "GEO")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Geo {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "geo_id")
    private Long id;
}
