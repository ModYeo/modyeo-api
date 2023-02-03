package com.co.kr.modyeo.api.team.domain.dto.request;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
public class TeamCreateActivAreaRequest {
    @NotNull
    private Long teamId;
    @NotNull
    private Long emdId;
    @NotNull
    private Integer limitMeters;

    @Builder(builderClassName = "of", builderMethodName = "of")
    public TeamCreateActivAreaRequest(Long teamId, Long emdId, Integer limitMeters){
        this.teamId = teamId;
        this.emdId = emdId;
        this.limitMeters = limitMeters;
    }
}
