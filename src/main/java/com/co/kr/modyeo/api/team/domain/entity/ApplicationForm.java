package com.co.kr.modyeo.api.team.domain.entity;

import com.co.kr.modyeo.common.entity.BaseEntity;
import com.co.kr.modyeo.common.enumerate.Yn;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Entity
@Table(name = "APPLICATION_FORM")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ApplicationForm extends BaseEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "application_form_id")
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "team_id")
    private Team team;

    @Lob
    private String content;

    @Lob
    @Column(name = "duty_note")
    private String dutyNote;

    @Column(name = "birthday_agree")
    private Yn birthdayAgree;

    @Column(name = "sex_agree")
    private Yn sexAgree;

    @Column(name = "geo_agree")
    private Yn geoAgree;

    @Builder(builderClassName = "of", builderMethodName = "of")
    public ApplicationForm(Long id, Team team, String content, String dutyNote, Yn birthdayAgree, Yn sexAgree, Yn geoAgree) {
        this.id = id;
        this.team = team;
        this.content = content;
        this.dutyNote = dutyNote;
        this.birthdayAgree = birthdayAgree;
        this.sexAgree = sexAgree;
        this.geoAgree = geoAgree;
    }

    @Builder(builderClassName = "createBuilder",builderMethodName = "createBuilder")
    public ApplicationForm(Team team, String content, String dutyNote, Yn birthdayAgree, Yn sexAgree, Yn geoAgree) {
        this.team = team;
        this.content = content;
        this.dutyNote = dutyNote;
        this.birthdayAgree = birthdayAgree;
        this.sexAgree = sexAgree;
        this.geoAgree = geoAgree;
    }
}
