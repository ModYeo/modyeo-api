package com.co.kr.modyeo.api.geo.domain.dto.response;

import com.co.kr.modyeo.api.geo.domain.entity.EmdArea;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class EmdAreaResponse {

    private Long emdAreaId;

    private Long siggAreaId;

    private String emdAreaCode;

    private String emdAreaName;

    private String version;


    @Builder(builderMethodName = "of",builderClassName = "of")
    public EmdAreaResponse(Long emdAreaId, Long siggAreaId, String emdAreaCode, String emdAreaName, String version) {
        this.emdAreaId = emdAreaId;
        this.siggAreaId = siggAreaId;
        this.emdAreaCode = emdAreaCode;
        this.emdAreaName = emdAreaName;
        this.version = version;
    }

    public static EmdAreaResponse toDto(EmdArea emdArea) {
        return of()
                .emdAreaId(emdArea.getId())
                .siggAreaId(emdArea.getSiggArea().getId())
                .emdAreaCode(emdArea.getCode())
                .emdAreaName(emdArea.getName())
                .version(emdArea.getVersion())
                .build();
    }
}
