package com.co.kr.modyeo.api.team.domain.dto.response;

import com.co.kr.modyeo.api.team.domain.entity.ApplicationForm;
import com.co.kr.modyeo.common.enumerate.Yn;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ApplicationFormDetail {

    private Long applicationFormId;

    private TeamResponse team;

    private String content;

    private String dutyNote;

    private Yn birthdayAgree;

    private Yn sexAgree;

    private Yn geoAgree;

    @Builder(builderClassName = "of",builderMethodName = "of")
    public ApplicationFormDetail(Long applicationFormId, TeamResponse team, String content, String dutyNote, Yn birthdayAgree, Yn sexAgree, Yn geoAgree) {
        this.applicationFormId = applicationFormId;
        this.team = team;
        this.content = content;
        this.dutyNote = dutyNote;
        this.birthdayAgree = birthdayAgree;
        this.sexAgree = sexAgree;
        this.geoAgree = geoAgree;
    }

    public static ApplicationFormDetail toDto(ApplicationForm applicationForm) {
        return of()
                .applicationFormId(applicationForm.getId())
                .team(TeamResponse.toDto(applicationForm.getTeam()))
                .content(applicationForm.getContent())
                .dutyNote(applicationForm.getDutyNote())
                .birthdayAgree(applicationForm.getBirthdayAgree())
                .sexAgree(applicationForm.getSexAgree())
                .geoAgree(applicationForm.getGeoAgree())
                .build();
    }
}
