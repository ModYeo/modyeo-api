package com.co.kr.modyeo.api.team.domain.dto.request;

import com.co.kr.modyeo.api.team.domain.entity.enumerate.CrewLevel;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CrewUpdateRequest {

    private Long crewId;

    private CrewLevel crewLevel;

    @Builder(builderClassName = "of",builderMethodName = "of")
    public CrewUpdateRequest(Long crewId, CrewLevel crewLevel) {
        this.crewId = crewId;
        this.crewLevel = crewLevel;
    }
}
