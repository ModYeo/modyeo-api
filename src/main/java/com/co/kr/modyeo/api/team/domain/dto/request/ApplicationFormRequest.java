package com.co.kr.modyeo.api.team.domain.dto.request;

import com.co.kr.modyeo.api.team.domain.entity.ApplicationForm;
import com.co.kr.modyeo.api.team.domain.entity.Team;
import com.co.kr.modyeo.common.enumerate.Yn;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ApplicationFormRequest {

    private Long teamId;

    private String content;

    private String dutyNote;

    private Yn birthdayAgree;

    private Yn sexAgree;

    private Yn geoAgree;

    public static ApplicationForm toEntity(ApplicationFormRequest applicationFormRequest, Team team) {
        return ApplicationForm.createBuilder()
                .team(team)
                .content(applicationFormRequest.getContent())
                .dutyNote(applicationFormRequest.getDutyNote())
                .birthdayAgree(applicationFormRequest.getBirthdayAgree())
                .sexAgree(applicationFormRequest.getSexAgree())
                .geoAgree(applicationFormRequest.getGeoAgree())
                .build();
    }
}
