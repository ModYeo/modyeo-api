package com.co.kr.modyeo.api.columncode.domain.dto.request;

import com.co.kr.modyeo.api.columncode.domain.entity.ColumnCode;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ColumnCodeCreateRequest {

    private String columnCodeName;

    private String code;

    private String description;

    public static ColumnCode toDto(ColumnCodeCreateRequest columnCodeCreateRequest) {
        return ColumnCode
                .createBuilder()
                .name(columnCodeCreateRequest.getColumnCodeName())
                .code(columnCodeCreateRequest.getCode())
                .description(columnCodeCreateRequest.getDescription())
                .build();
    }
}
