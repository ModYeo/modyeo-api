package com.co.kr.modyeo.api.geo.domain.dto.response;

import com.co.kr.modyeo.api.geo.domain.entity.SidoArea;
import lombok.Builder;

import javax.persistence.Column;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class SidoAreaResponse {

    private Long sidoAreaId;

    private String sidoAreaCode;

    private String sidoAreaName;

    private String version;

    private List<SiggAreaResponse> siggAreaResponseList = new ArrayList<>();

    @Builder(builderClassName = "of",builderMethodName = "of")
    public SidoAreaResponse(Long sidoAreaId, String sidoAreaCode, String sidoAreaName, String version, List<SiggAreaResponse> siggAreaResponseList) {
        this.sidoAreaId = sidoAreaId;
        this.sidoAreaCode = sidoAreaCode;
        this.sidoAreaName = sidoAreaName;
        this.version = version;
        this.siggAreaResponseList = siggAreaResponseList;
    }

    public static SidoAreaResponse toDto(SidoArea sidoArea) {
        return of()
                .sidoAreaId(sidoArea.getId())
                .sidoAreaCode(sidoArea.getCode())
                .sidoAreaName(sidoArea.getName())
                .version(sidoArea.getVersion())
                .siggAreaResponseList(sidoArea.getSiggAreaList().stream()
                        .map(SiggAreaResponse::toDto)
                        .collect(Collectors.toList()))
                .build();
    }
}
