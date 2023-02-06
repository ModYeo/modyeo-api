package com.co.kr.modyeo.api.member.domain.dto.request;

import lombok.Data;

@Data
public class LimitMetersUpdateRequest {

    private Long memberActiveAreaId;

    private Integer limitMeters;
}
