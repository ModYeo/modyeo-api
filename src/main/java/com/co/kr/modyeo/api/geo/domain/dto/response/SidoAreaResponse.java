package com.co.kr.modyeo.api.geo.domain.dto.response;

import javax.persistence.Column;
import java.util.ArrayList;
import java.util.List;

public class SidoAreaResponse {

    private Long sidoAreaId;

    private String sidoAreaCode;

    private String sidoAreaName;

    private String version;

    private List<SiggAreaResponse> siggAreaResponseList = new ArrayList<>();
}
