package com.co.kr.modyeo.api.team.domain.dto.request;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
public class TeamUpdateLimitMeterRequest {
    @NotNull
    Long teamId;
    @NotNull
    Long activeAreaId;
    @NotNull
    Integer limitMeters;

    @Builder(builderClassName = "of", builderMethodName = "of")
    public TeamUpdateLimitMeterRequest(Long teamId, Integer limitMeters){
        this.teamId = teamId;
        this.limitMeters = limitMeters;
    }
}
