package com.co.kr.modyeo.api.geo.domain.dto.response;

import com.co.kr.modyeo.api.geo.domain.entity.SiggArea;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
public class SiggAreaResponse {

    private Long siggAreaId;

    private Long sidoAreaId;

    private String siggAreaCode;

    private String siggAreaName;

    private String version;

    private List<EmdAreaResponse> emdAreaResponseList = new ArrayList<>();

    @Builder(builderMethodName = "of",builderClassName = "of")
    public SiggAreaResponse(Long siggAreaId, Long sidoAreaId, String siggAreaCode, String siggAreaName, String version, List<EmdAreaResponse> emdAreaResponseList) {
        this.siggAreaId = siggAreaId;
        this.sidoAreaId = sidoAreaId;
        this.siggAreaCode = siggAreaCode;
        this.siggAreaName = siggAreaName;
        this.version = version;
        this.emdAreaResponseList = emdAreaResponseList;
    }

    public static SiggAreaResponse toDto(SiggArea siggArea) {
        return of()
                .sidoAreaId(siggArea.getSidoArea().getId())
                .siggAreaId(siggArea.getId())
                .siggAreaCode(siggArea.getCode())
                .siggAreaName(siggArea.getName())
                .version(siggArea.getVersion())
                .emdAreaResponseList(siggArea.getEmdAreaList().stream()
                        .map(EmdAreaResponse::toDto)
                        .collect(Collectors.toList()))
                .build();
    }
}
