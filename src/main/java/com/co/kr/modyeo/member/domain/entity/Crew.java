package com.co.kr.modyeo.member.domain.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Entity
@Table(name = "CREW")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Crew {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "crew_id")
    private Long id;

    @Column(name = "crew_name")
    private String name;
}
