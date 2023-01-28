package com.co.kr.modyeo.api.geo.domain.dto.response;

import javax.persistence.Column;
import java.util.ArrayList;
import java.util.List;

public class SiggAreaResponse {

    private Long siggAreaId;

    private Long sidoAreaId;

    private String siggAreaCode;

    private String siggAreaName;

    private String version;

    private List<EmdAreaResponse> emdAreaResponseList = new ArrayList<>();
}
