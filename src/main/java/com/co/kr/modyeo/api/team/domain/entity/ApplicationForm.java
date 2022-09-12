package com.co.kr.modyeo.api.team.domain.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Entity
@Table(name = "APPLICATION_FORM")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ApplicationForm {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "application_form_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "team_id")
    private Team team;

    private String content;

    @Column(name = "duty_note")
    private String dutyNote;
}
