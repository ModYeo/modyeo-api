package com.co.kr.modyeo.api.geo.domain.dto.response;

import com.co.kr.modyeo.api.geo.domain.entity.EmdArea;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class EmdAreaDetail {

    private Long emdAreaId;

    private Long siggAreaId;

    private String siggAreaName;

    private Long sidoAreaId;

    private String sidoAreaName;

    private String emdAreaCode;

    private String emdAreaName;

    private String version;

    @Builder(builderClassName = "of", builderMethodName = "of")
    public EmdAreaDetail(Long emdAreaId, Long siggAreaId, String siggAreaName, Long sidoAreaId, String sidoAreaName, String emdAreaCode, String emdAreaName, String version) {
        this.emdAreaId = emdAreaId;
        this.siggAreaId = siggAreaId;
        this.siggAreaName = siggAreaName;
        this.sidoAreaId = sidoAreaId;
        this.sidoAreaName = sidoAreaName;
        this.emdAreaCode = emdAreaCode;
        this.emdAreaName = emdAreaName;
        this.version = version;
    }

    public static EmdAreaDetail toDto(EmdArea emdArea) {
        return of()
                .emdAreaId(emdArea.getId())
                .emdAreaName(emdArea.getName())
                .siggAreaId(emdArea.getSiggArea().getId())
                .siggAreaName(emdArea.getSiggArea().getName())
                .sidoAreaId(emdArea.getSiggArea().getSidoArea().getId())
                .sidoAreaName(emdArea.getSiggArea().getSidoArea().getName())
                .emdAreaCode(emdArea.getCode())
                .version(emdArea.getVersion())
                .build();
    }
}
