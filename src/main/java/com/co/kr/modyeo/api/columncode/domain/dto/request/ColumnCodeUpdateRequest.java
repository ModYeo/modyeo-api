package com.co.kr.modyeo.api.columncode.domain.dto.request;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ColumnCodeUpdateRequest {

    private Long columnCodeId;

    private String columnCodeName;

    private String code;

    private String description;
}
