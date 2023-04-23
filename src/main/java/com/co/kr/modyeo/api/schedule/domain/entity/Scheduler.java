package com.co.kr.modyeo.api.schedule.domain.entity;

import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
@Table(name = "SCHEDULER")
public class Scheduler {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "scheduler_id")
    private Long id;
}
